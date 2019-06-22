package sk.uniza.WFD_dev.openweathermap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface Retrof_client {

    @POST("weather")
    Call<OpenWeatherOBJ> get_DataFromSourceServer(@QueryMap Map<String, String> param);

}
