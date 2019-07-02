package sk.fri.uniza.resources;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import retrofit2.http.Body;
import sk.fri.uniza.api.Device;
import sk.fri.uniza.db.DevicesDao;
import sk.fri.uniza.db.LiteWeatherObjDao;
import sk.fri.uniza.openweathermap.LiteWeatherOBJ;
import sk.fri.uniza.openweathermap.OpenWeatherOBJ;


import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/authDevice")
@Produces(MediaType.APPLICATION_JSON)
public class Dev_Resource {
    private DevicesDao dao_device;
    LiteWeatherObjDao weather_Dao;

    public Dev_Resource(DevicesDao dao_device, LiteWeatherObjDao weather_Dao)  {
        this.dao_device = dao_device;
        this.weather_Dao = weather_Dao;
    }

    @PermitAll
    @GET
    @UnitOfWork
    public Response getEmployees(@Auth Device devices) {
        return Response.ok().build();
    }

    @RolesAllowed( "default" )
    @GET
    @Path("/{id}")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDevice_WithID(@PathParam("id") Integer id, @Auth Device devices) {
        Device device = dao_device.getDeviceWithId_DB(id);
        if (device != null)
            return Response.ok(device).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/addDev")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Device write(@QueryParam("name") String _name,
                        @QueryParam("location") String locate,
                        @QueryParam("pass") String password) {
        Device aa = new Device(_name,locate,password,"url");
        dao_device.save(aa);
        return aa;
    }

    @GET
    @Path("/getWeather")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public LiteWeatherOBJ select() {
        LiteWeatherOBJ aa = weather_Dao.findDeviceWithName_DB("Poprad");
        //aa.setName(Device.s_generateHashSecrete(aa.getSalt(),"device123").toString());
        //aa.setLocation(Device.s_generateHashSecrete(aa.getSalt(),"device123").toString());
        return aa;
    }

    @GET
    @Path("/selectDev")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Device select(@QueryParam("id") Integer _int,@QueryParam("name") String _name) {
        Device aa = dao_device.findDeviceWithName_DB(_name);
        //aa.setName(Device.s_generateHashSecrete(aa.getSalt(),"device123").toString());
        //aa.setLocation(Device.s_generateHashSecrete(aa.getSalt(),"device123").toString());
        return aa;
    }




    @POST
    @Path("weather")
    @RolesAllowed( "default" )
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public OpenWeatherOBJ newWeather(@Body OpenWeatherOBJ obj)  {
        LiteWeatherOBJ newData = new LiteWeatherOBJ(obj);
        weather_Dao.save(newData);
        System.out.println("newData"+newData.getName()+" temp:"+newData.getTemp());
        return obj;
    }

 /*   @POST
    @Path("/openwather")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public OpenWeatherOBJ get_data_from_sourceServeer() {
        Map<String, String> codeRequest = new HashMap<>();
        codeRequest.put("q", "Poprad,sk");
        codeRequest.put("appid", "9c868f5b882f62636aae7d9037ae5b63");
        try {
            retrofit2.Response<OpenWeatherOBJ> weatherResp = WindFarmDemoApplication.getRequest_client().get_DataFromSourceServer(codeRequest).execute();
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
    }*/
}
