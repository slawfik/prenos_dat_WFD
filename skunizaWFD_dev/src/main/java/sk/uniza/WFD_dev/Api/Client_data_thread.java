package sk.uniza.WFD_dev.Api;

import org.glassfish.jersey.internal.util.Base64;
import retrofit2.Retrofit;
import sk.uniza.WFD_dev.App;
import sk.uniza.WFD_dev.Config;
import sk.uniza.WFD_dev.Resource.Retrof_client_server;
import sk.uniza.WFD_dev.openweathermap.OpenWeatherOBJ;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Client_data_thread extends Thread{
    private Retrofit retrofit;
    private Retrof_client_server retrofit_client;

    public Client_data_thread() {}

    public Client_data_thread(Retrof_client_server retrofit_client)  {

        this.retrofit_client = retrofit_client;
    }

    @Override
    public void run(){
        boolean aa = true;
        Map<String, String> codeRequest = new HashMap<>();
        codeRequest.put("q", "Poprad,sk");
        codeRequest.put("appid", Config.APPI_ID);
        String base = Config.USERNAME + ":" + Config.PASSWORD;
        String authHeader = "Basic " + Base64.encodeAsString(base);
        try {
            while(aa) {
                retrofit2.Response<OpenWeatherOBJ> weatherResp = App.getRequest_client_weather().get_DataFromSourceServer(codeRequest).execute();
                if (weatherResp.isSuccessful()) {
                    OpenWeatherOBJ new_weater_obj = weatherResp.body();

                    retrofit2.Response<OpenWeatherOBJ> sendDataRespone = retrofit_client.sendData(new_weater_obj, authHeader).execute();
                    if (sendDataRespone.isSuccessful()) {
                        //zapis do databazy
                        System.out.println("sucesful");
                    }
                    TimeUnit.SECONDS.sleep(40);
                    System.out.println("Wait");
                }
            }
        } catch (Exception e)   {
            System.out.println(e.getMessage());
        }
    }
}
