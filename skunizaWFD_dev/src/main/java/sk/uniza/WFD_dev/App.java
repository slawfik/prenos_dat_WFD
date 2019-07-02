package sk.uniza.WFD_dev;

import org.glassfish.jersey.internal.util.Base64;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import sk.uniza.WFD_dev.Api.Device;
import sk.uniza.WFD_dev.Resource.Dev_Resource;
import sk.uniza.WFD_dev.Resource.Retrof_client_server;
import sk.uniza.WFD_dev.openweathermap.OpenWeatherOBJ;
import sk.uniza.WFD_dev.Resource.Retrof_client_weather;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Hello world!
 *
 */


public class App extends Application<Configuration>
{
    private static Retrof_client_weather retrof_client_Weather;
    private static Retrof_client_server retrof_client_Server;
    private static Device thisDevice;

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    @Override
    public void initialize(Bootstrap<Configuration> b) {
    }

    public static Retrof_client_weather getRequest_client_weather(){
        return retrof_client_Weather;
    }

    public static Device getThisDevice()   {
        return thisDevice;
    }

    public static void setThisDevice (Device pa_dev) {
        thisDevice = pa_dev;
    }

    public static Retrof_client_server getRequest_client_server(){
        return retrof_client_Server;
    }

    @Override
    public void run(Configuration c, Environment enviroment) throws Exception {
        LOGGER.info("Registering REST resources");

        final Dev_Resource dev_res = new Dev_Resource();
        enviroment.jersey().register(dev_res);

        Retrofit ret_OWServer = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        Retrofit ret_server = new Retrofit.Builder()
                .baseUrl("http://localhost:8085/api/authDevice/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        retrof_client_Weather = ret_OWServer.create(Retrof_client_weather.class);
        retrof_client_Server = ret_server.create(Retrof_client_server.class);
        thisDevice = authentificateThisDevice();
    }

    public Device authentificateThisDevice() {
        String base = "Tomas" + ":" + "device123";
        String authHeader = "Basic " + Base64.encodeAsString(base); //Basic VG9tYXM6ZGV2aWNlMTIz
        try {
            Call<Device> cal = App.getRequest_client_server().initDevice(authHeader);
            retrofit2.Response<Device> auth_dev = cal.execute();
            if (auth_dev.isSuccessful()) {
                return auth_dev.body();
            } else {
                return null;
            }
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

}
