package sk.uniza.WFD_dev.Resource;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import sk.uniza.WFD_dev.Api.Device;
import sk.uniza.WFD_dev.openweathermap.OpenWeatherOBJ;

import java.util.Map;

public interface Retrof_client_weather {

    @POST("weather")
    Call<OpenWeatherOBJ> get_DataFromSourceServer(@QueryMap Map<String, String> param);

}
