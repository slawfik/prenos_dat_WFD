package sk.fri.uniza.openweathermap;

import javax.persistence.*;

@Entity
@Table(name="Weather_Table")
public class LiteWeatherOBJ {
    private String name;
    private Double temp;
    private Integer pressure;
    private Integer humidity;
    private Integer all;
    private Double speed;

    private Long id;

    public LiteWeatherOBJ(OpenWeatherOBJ obj)   {
        temp = obj.getMain().getTemp();
        pressure = obj.getMain().getPressure();
        humidity = obj.getMain().getHumidity();
        all = obj.getClouds().getAll();
        name = obj.getName();
        speed = obj.getWind().getSpeed();
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
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

    @Column(name="Clouds")
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
