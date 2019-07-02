package sk.fri.uniza.api;

import java.security.Principal;

public class LiteWeatherOBJ implements Principal {
    private String name;
    private Double temp;
    private Integer pressure;
    private Integer humidity;
    private Integer all;
    private Double speed;

    private Long id;

    public LiteWeatherOBJ() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Double getTemp() {
        return temp;
    }

    public Integer getPressure() {
        return pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public Integer getAll() {
        return all;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }
}
