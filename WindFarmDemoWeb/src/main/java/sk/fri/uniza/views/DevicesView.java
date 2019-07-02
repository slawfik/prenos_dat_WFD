package sk.fri.uniza.views;

import sk.fri.uniza.api.Device;
import sk.fri.uniza.api.LiteWeatherOBJ;
import sk.fri.uniza.core.User;

import javax.ws.rs.core.UriInfo;
import java.util.List;

public class DevicesView extends MaterializePage<MaterializeHeader, MaterializeFooter> {
    private final User loginUser;
    private final String toastMsg;
    private List<Device> w;

    public DevicesView(UriInfo uriInfo, User loginUser, List<Device> Wobj, String toastMsg) {
        super("devices_table.ftl", uriInfo, new MaterializeHeader(loginUser, "Vytvorené zariadenia", true), new MaterializeFooter());
        this.loginUser = loginUser;
        //this.Wobj = Wobj;
        this.toastMsg = toastMsg;
        /*w = new ArrayList<LiteWeatherOBJ>();
        w.add(Wobj);*/
        this.w = Wobj;

    }

    public String getToastMsg() {
        return toastMsg;
    }

    public User getLoginUser() {
        return loginUser;
    }

    public List<Device> getDList() {
        return w;
    }
}
