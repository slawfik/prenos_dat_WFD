package sk.uniza.WFD_dev.Resource;

import sk.uniza.WFD_dev.Api.Device;
import sk.uniza.WFD_dev.App;
import sk.uniza.WFD_dev.openweathermap.OpenWeatherOBJ;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
    public OpenWeatherOBJ get_data_from_sourceServeer() {
        Map<String, String> codeRequest = new HashMap<>();
        codeRequest.put("q", "Poprad,sk");
        codeRequest.put("appid", "9c868f5b882f62636aae7d9037ae5b63");
        try {               //App.getRequest.... vráti retrofitClienta
            retrofit2.Response<OpenWeatherOBJ> weatherResp = App.getRequest_client().get_DataFromSourceServer(codeRequest).execute();
            if (weatherResp.isSuccessful()) {
                OpenWeatherOBJ new_weater_obj = weatherResp.body();
                return new_weater_obj;
            } else {
                OpenWeatherOBJ obj = new OpenWeatherOBJ();
                obj.setName(weatherResp.toString());
                return obj;
            }

        } catch (IOException e) {
            OpenWeatherOBJ obj = new OpenWeatherOBJ();
            obj.setName("Exception");
            return null;
        }
    }

    @GET
    @Path("/autorizeDev")
    @Produces(MediaType.APPLICATION_JSON)                                               //@BeanParam Device device
    public Device add_dev(@QueryParam("name") String _name,@QueryParam("id") Integer i) {//@QueryParam("name") String _name,@QueryParam("id") Integer id
        Device newdev = new Device(_name,i);

        return newdev;
    }
}
