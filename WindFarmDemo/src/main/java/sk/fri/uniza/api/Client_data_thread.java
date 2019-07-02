package sk.fri.uniza.api;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import sk.fri.uniza.resources.Retrofit_client;

import javax.ws.rs.core.Response;
import java.util.concurrent.TimeUnit;

public class Client_data_thread extends Thread{
    private Retrofit retrofit;
    private Retrofit_client retrofit_client;

    public Client_data_thread() {}

    public Client_data_thread(String base_url)  {
        retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        retrofit_client = retrofit.create(Retrofit_client.class);
    }

    @Override
    public void run(){
        boolean aa = true;
        try {
            while(aa) {
                retrofit2.Response<Response> post_data_flag = retrofit_client.post_new_data().execute();
                if (post_data_flag.isSuccessful()) {
                    //zapis do databazy
                    System.out.println("sucesful");
                }
                TimeUnit.SECONDS.sleep(20);
                System.out.println("MyThread running");
            }
        } catch (Exception e)   {
            System.out.println(e.getMessage());
        }
    }
}
