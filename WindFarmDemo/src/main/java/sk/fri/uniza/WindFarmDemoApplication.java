package sk.fri.uniza;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthFilter;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.auth.chained.ChainedAuthFilter;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.jersey.errors.ErrorEntityWriter;
import io.dropwizard.jersey.errors.ErrorMessage;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.View;
import io.dropwizard.views.ViewBundle;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import jersey.repackaged.com.google.common.collect.Lists;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.hibernate.SessionFactory;
import sk.fri.uniza.api.Device;
import sk.fri.uniza.api.Person;
import sk.fri.uniza.api.Phone;
import sk.fri.uniza.auth.*;
import sk.fri.uniza.config.WindFarmDemoConfiguration;
import sk.fri.uniza.core.User;
import sk.fri.uniza.db.DevicesDao;
import sk.fri.uniza.db.LiteWeatherObjDao;
import sk.fri.uniza.db.PersonDao;
import sk.fri.uniza.db.UsersDao;
import sk.fri.uniza.health.TemplateHealthCheck;
import sk.fri.uniza.openweathermap.LiteWeatherOBJ;
import sk.fri.uniza.resources.*;
import sk.fri.uniza.views.ErrorView;

import javax.ws.rs.core.MediaType;
import java.security.Key;
import java.security.KeyPair;
import java.util.*;

public class WindFarmDemoApplication extends Application<WindFarmDemoConfiguration> {
    //private List<Client_data_thread> thread= new ArrayList<Client_data_thread>();

    public static void main(final String[] args) throws Exception {
        new WindFarmDemoApplication().run(args);
    }

    @Override
    public String getName() {
        return "WindFarmDemo";
    }


    /**
     * Initialization of Hibernate ORM bundle.
     * Note: Add class that need to be mapped by Hibernate
     */
    private final HibernateBundle<WindFarmDemoConfiguration> hibernate = new HibernateBundle<WindFarmDemoConfiguration>(User.class, Person.class, Phone.class, Device.class, LiteWeatherOBJ.class) {

        @Override
        public PooledDataSourceFactory getDataSourceFactory(WindFarmDemoConfiguration windFarmDemoConfiguration) {
            return windFarmDemoConfiguration.getDataSourceFactory();
        }

        @Override
        public SessionFactory getSessionFactory() {
            return super.getSessionFactory();
        }
    };


    @Override
    public void initialize(final Bootstrap<WindFarmDemoConfiguration> bootstrap) {


        bootstrap.addBundle(new AssetsBundle("/assets/", "/"));

        // Add View html bundle
        bootstrap.addBundle(new ViewBundle<WindFarmDemoConfiguration>() {
            @Override
            public Map<String, Map<String, String>> getViewConfiguration(WindFarmDemoConfiguration configuration) {
                return configuration.getViewRendererConfiguration();
            }
        });

        // Add ORM Hibernate bundle
        bootstrap.addBundle(hibernate);

        // Swagger documentation available on http://localhost:<your_port>/swagger
        bootstrap.addBundle(new SwaggerBundle<WindFarmDemoConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(WindFarmDemoConfiguration configuration) {
                return configuration.swaggerBundleConfiguration;
            }
        });
    }

    @Override
    public void run(final WindFarmDemoConfiguration configuration,
                    final Environment environment) {


        //Add HealthChecks
        final TemplateHealthCheck templateHealthCheck = new TemplateHealthCheck(configuration.getTemplate());

        environment.healthChecks().register("templateHealthCheck", templateHealthCheck);

        // Register Resources
        registerResources(configuration, environment);

        // Setup user auth
        registerUserAuth(configuration, environment);

        // Register new error page handler
        environment.jersey().register(new ErrorEntityWriter<ErrorMessage, View>(MediaType.TEXT_HTML_TYPE, View.class) {
            @Override
            protected View getRepresentation(ErrorMessage errorMessage) {
                return new ErrorView(errorMessage);
            }
        });

       /* Client_data_thread aa = new Client_data_thread("http://localhost:8090/");
        aa.run();*/
        //start_receiving_dataFromDevices();
    }



    private void registerUserAuth(WindFarmDemoConfiguration configuration, Environment environment) {

        // Load key that is used to sign the JWT token
        KeyPair secreteKey = configuration.getOAuth2Configuration().getSecreteKey(false);

        // UsersDAO
        final UsersDao usersDao = UsersDao.createUsersDao(hibernate.getSessionFactory());
        final DevicesDao dev_dao = DevicesDao.createDevDao(hibernate.getSessionFactory());

        //initialize oauth2
        OAuth2Authenticator oAuth2Authenticator = new UnitOfWorkAwareProxyFactory(hibernate).create(OAuth2Authenticator.class, new Class[]{UsersDao.class, Key.class}, new Object[]{usersDao, secreteKey.getPublic()});
        AuthFilter oauth_filter = new OAuthCredentialAuthFilter.Builder<User>()
                .setAuthenticator(oAuth2Authenticator)
                .setAuthorizer(new OAuth2Authorizer())
                .setPrefix("Bearer")
                .buildAuthFilter();

        //initialize device basic authentification
        BasicDev_Authentificator basicAuth= new UnitOfWorkAwareProxyFactory(hibernate).create(BasicDev_Authentificator.class, new Class[]{DevicesDao.class}, new Object[]{dev_dao});
        AuthFilter basicCredentialAuthFilter = new BasicCredentialAuthFilter.Builder<Device>()
                .setAuthenticator(basicAuth)
                .setAuthorizer(new BasicDev_Authorizer())
                .setRealm("SUPER SECRET STUFF")
                .buildAuthFilter();

        List<AuthFilter> filters = Lists.newArrayList(basicCredentialAuthFilter, oauth_filter);
        environment.jersey().register(new AuthDynamicFeature(new ChainedAuthFilter(filters)));

        // Generate fake users & devices
        oAuth2Authenticator.generateUsers();
        basicAuth.generateDevices();

        // Enable the resource protection annotations: @RolesAllowed, @PermitAll & @DenyAll
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        // Enable the @Auth annotation for binding authenticated users to resource method parameters
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(Device.class));
    }

    private void registerResources(WindFarmDemoConfiguration configuration, Environment environment) {

        final HelloWorldResource helloWorldResource = new HelloWorldResource(configuration.getTemplate(), configuration.getDefaultName());
        final DevicesDao dev_dao = DevicesDao.createDevDao(hibernate.getSessionFactory());
        final LiteWeatherObjDao weather_Dao= LiteWeatherObjDao.createWeatherDao(hibernate.getSessionFactory());

        // Create Dao access objects
        final UsersDao usersDao = UsersDao.createUsersDao(hibernate.getSessionFactory());
        final PersonDao personDao = new PersonDao(hibernate.getSessionFactory());

        KeyPair secreteKey = configuration.getOAuth2Configuration().getSecreteKey(false);
        final LoginResource loginResource = new LoginResource(secreteKey, usersDao, OAuth2Clients.getInstance());
        final UsersResource usersResource = new UsersResource(usersDao);
        final PersonResource personResource = new PersonResource(personDao);
        final Dev_Resource dev_res = new Dev_Resource(dev_dao,weather_Dao);

        environment.jersey().register(helloWorldResource);
        environment.jersey().register(loginResource);
        environment.jersey().register(usersResource);
        environment.jersey().register(personResource);
        environment.jersey().register(dev_res);
    }

}

