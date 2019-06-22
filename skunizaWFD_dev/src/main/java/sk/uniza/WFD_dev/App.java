package sk.uniza.WFD_dev;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import sk.uniza.WFD_dev.Resource.Dev_Resource;
import sk.uniza.WFD_dev.openweathermap.OpenWeatherOBJ;
import sk.uniza.WFD_dev.openweathermap.Retrof_client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */


public class App extends Application<Configuration>
{
    public static Retrof_client request_client;
    public static OpenWeatherOBJ weater_data;
    private static Retrof_client retrof_client_req;

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    @Override
    public void initialize(Bootstrap<Configuration> b) {
    }

    public static Retrof_client getRequest_client(){
        return retrof_client_req;
    }

    @Override
    public void run(Configuration c, Environment enviroment) throws Exception {
        LOGGER.info("Registering REST resources");

        final Dev_Resource dev_res = new Dev_Resource();
        enviroment.jersey().register(dev_res);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        retrof_client_req = retrofit.create(Retrof_client.class);
    }

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

}
