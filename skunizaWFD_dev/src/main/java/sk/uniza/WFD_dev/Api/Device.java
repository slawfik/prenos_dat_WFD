package sk.uniza.WFD_dev.Api;

import javax.ws.rs.QueryParam;

public class Device {
    @QueryParam("name")
    private String name;

    @QueryParam("id")
    private Integer id;

    public Device(String name, Integer id) {
        this.name = name;
        this.id = id;
    }
    @QueryParam("id")
    public void setId(Integer id) {
        this.id = id;
    }
    @QueryParam("id")
    public Integer getId() {
        return id;
    }
    @QueryParam("name")
    public void setName(String name) {
        this.name = name;
    }
    @QueryParam("name")
    public String getName() {
        return name;
    }

}
