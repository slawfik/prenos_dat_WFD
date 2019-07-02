package sk.uniza.WFD_dev.Resource;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import sk.uniza.WFD_dev.Api.Device;
import sk.uniza.WFD_dev.openweathermap.OpenWeatherOBJ;

import retrofit2.http.POST;

import javax.ws.rs.core.Response;

public interface Retrof_client_server {

    @GET("1")
    Call<Device> initDevice(@Header("Authorization") String head);

    @POST("weather")
    Call<OpenWeatherOBJ> sendData(@Body OpenWeatherOBJ weatherOBJ,@Header("Authorization") String head);//@Header("Authorization") String head
}
