package sk.fri.uniza.resources;

import retrofit2.Call;
import retrofit2.http.POST;
import javax.ws.rs.core.Response;

public interface Retrofit_client {
    @POST("get")
    Call<Response> post_new_data();
}
