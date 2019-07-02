package sk.fri.uniza.views;

import sk.fri.uniza.api.LiteWeatherOBJ;
import sk.fri.uniza.api.Person;
import sk.fri.uniza.core.User;

import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

public class WeatherView extends MaterializePage<MaterializeHeader, MaterializeFooter> {
    private final User loginUser;
    private final String toastMsg;
    private LiteWeatherOBJ Wobj;
    private List<LiteWeatherOBJ> w;

    public WeatherView(UriInfo uriInfo, User loginUser, LiteWeatherOBJ Wobj, String toastMsg) {
        super("weatherr.ftl", uriInfo, new MaterializeHeader(loginUser, "Weather", true), new MaterializeFooter());
        this.loginUser = loginUser;
        this.Wobj = Wobj;
        this.toastMsg = toastMsg;
        w = new ArrayList<LiteWeatherOBJ>();
        w.add(Wobj);

    }

    public String getToastMsg() {
        return toastMsg;
    }

    public User getLoginUser() {
        return loginUser;
    }

    public List<LiteWeatherOBJ> getWobj() {
        return w;
    }
}
