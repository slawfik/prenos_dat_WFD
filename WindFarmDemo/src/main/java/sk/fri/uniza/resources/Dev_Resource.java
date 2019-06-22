package sk.fri.uniza.resources;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import sk.fri.uniza.WindFarmDemoApplication;
import sk.fri.uniza.api.Device;
import sk.fri.uniza.db.DevicesDao;


import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/authDevice")
@Produces(MediaType.APPLICATION_JSON)
public class Dev_Resource {
    private DevicesDao dao_device;

    public Dev_Resource(DevicesDao dao_device)  {
        this.dao_device = dao_device;
    }

    @PermitAll
    @GET
    public Response getEmployees(@Auth Device devices) {
        return Response.ok(dao_device.getArrayL_Devices()).build();
    }

    @RolesAllowed({ "default" })
    @GET
    @Path("/{id}")
    public Response getDevice_WithID(@PathParam("id") Integer id, @Auth Device devices) {
        Device device = dao_device.getDeviceFromLocalDB_WithID(id);
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
                        @QueryParam("pass") String passwor) {
        Device aa = new Device(_name,locate,passwor);
        dao_device.save(aa);
        return aa;
    }

    @GET
    @Path("/selectDev")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Device select(@QueryParam("id") Integer _int,@QueryParam("name") String _name) {
        Device aa = dao_device.findDeviceWithName(_name);
        return aa;
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
