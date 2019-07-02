package sk.uniza.WFD_dev.Resource;

import org.glassfish.jersey.internal.util.Base64;
import retrofit2.Call;
import sk.uniza.WFD_dev.Api.Device;
import sk.uniza.WFD_dev.App;
import sk.uniza.WFD_dev.openweathermap.OpenWeatherOBJ;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
public class Dev_Resource {


    public Dev_Resource()  {

    }

    @POST
    @Path("/openwather")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response get_data_from_sourceServeer() {
        Map<String, String> codeRequest = new HashMap<>();
        codeRequest.put("q", "Poprad,sk");
        codeRequest.put("appid", "9c868f5b882f62636aae7d9037ae5b63");
            try {               //App.getRequest.... vráti retrofitClienta
                retrofit2.Response<OpenWeatherOBJ> weatherResp = App.getRequest_client_weather().get_DataFromSourceServer(codeRequest).execute();
                if (weatherResp.isSuccessful()) {
                    OpenWeatherOBJ new_weater_obj = weatherResp.body();
                    return Response.ok(new_weater_obj).build();
                }
            } catch (IOException e) {
                OpenWeatherOBJ obj = new OpenWeatherOBJ();
                obj.setName("Exception");
                return Response.status(Response.Status.EXPECTATION_FAILED).build();
            }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Path("/devLogin")
    @Produces(MediaType.APPLICATION_JSON)                                               //@BeanParam Device device
    public Response add_dev() {//@QueryParam("name") String _name,@QueryParam("id") Integer id
        String base = "Tomas" + ":" + "device123";
        String authHeader = "Basic " + Base64.encodeAsString(base); //Basic VG9tYXM6ZGV2aWNlMTIz
        try {
            Call<Device> cal = App.getRequest_client_server().initDevice(authHeader);
            //System.out.println("____________"+cal.request().headers().toString());
            retrofit2.Response<Device> auth_dev = cal.execute();
            if (auth_dev.isSuccessful())    {
                App.setThisDevice(auth_dev.body());
                return Response.ok(auth_dev.body()).build();
            }   else {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        } catch (IOException e) {
            Device obj = new Device();
            obj.setName(e.getLocalizedMessage());
            return Response.ok(obj).build();
        }
    }

    @POST
    @Path("get")
    public Response posli_data_na_BackEnd()  {
        Map<String, String> codeRequest = new HashMap<>();
        codeRequest.put("q", "Poprad,sk");
        codeRequest.put("appid", "9c868f5b882f62636aae7d9037ae5b63");
        String base = "Tomas" + ":" + "device123";
        String authHeader = "Basic " + Base64.encodeAsString(base);
        try {               //App.getRequest.... vráti retrofitClienta
            retrofit2.Response<OpenWeatherOBJ> weatherResp = App.getRequest_client_weather().get_DataFromSourceServer(codeRequest).execute();
            if (weatherResp.isSuccessful()) {
                OpenWeatherOBJ new_weater_obj = weatherResp.body();

                //send on API
                Call<OpenWeatherOBJ> call = App.getRequest_client_server().sendData(new_weater_obj,authHeader);
                retrofit2.Response<OpenWeatherOBJ> send_Data = call.execute();
                System.out.println(call.request().url().toString());
                send_Data.isSuccessful();
                if (send_Data.isSuccessful()) {
                    return Response.ok(send_Data.body()).build();
                }
            }
        } catch (IOException e) {
                return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }
}
