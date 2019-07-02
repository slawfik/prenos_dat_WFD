package sk.fri.uniza.resources;

import io.dropwizard.auth.Auth;
import retrofit2.Response;
import sk.fri.uniza.auth.Role;
import sk.fri.uniza.auth.Session;
import sk.fri.uniza.core.User;
import sk.fri.uniza.views.WeatherView;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;

@Path("/weather")
public class WeatherResource {

    @GET
    @Path("Poprad")
    @Produces(MediaType.TEXT_HTML)
    @RolesAllowed({Role.USER_READ_ONLY, Role.ADMIN})
    public WeatherView getPersonInfo(@Auth User user, @Context UriInfo uriInfo, @Context HttpHeaders headers) {

        /*if (personID == null) personID = user.getId();

        if (!user.getRoles().contains(Role.ADMIN) && user.getId() != personID)
            throw new WebApplicationException(javax.ws.rs.core.Response.Status.UNAUTHORIZED);

        Session session = sessionDao.getSession(headers);

        Response<Person> personResponse;
        try {

            personResponse = WindFarmDemoApplication.getWindFarmServis().getPerson(session.getBearerToken(), personID).execute();
            if (personResponse.isSuccessful()) {
                Person personLoggedIn = personResponse.body();
                return new PersonView(uriInfo, user, personLoggedIn, null);
            }
            throw new WebApplicationException(personResponse.code());
        } catch (IOException e) {
            e.printStackTrace();
            throw new WebApplicationException(e);
        }*/
        return null;

    }
}
