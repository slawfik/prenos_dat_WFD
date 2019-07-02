package sk.fri.uniza.openweathermap;

import javax.persistence.*;
import java.security.Principal;
import java.util.Date;
import java.text.SimpleDateFormat;

@Entity
@Table(name="Weather_Table")
public class LiteWeatherOBJ implements Principal {
    private String name;
    private Double temp;
    private Integer pressure;
    private Integer humidity;
    private Integer all;
    private Double speed;

    private String time;
    private String date;

    private Long id;

    public LiteWeatherOBJ() {

    }

    public LiteWeatherOBJ(OpenWeatherOBJ obj)   {
        Date date = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
        this.time = formatTime.format(date);
        this.date = formatDate.format(date);

        temp = obj.getMain().getCelsiusTemp();
        pressure = obj.getMain().getPressure();
        humidity = obj.getMain().getHumidity();
        all = obj.getClouds().getAll();
        name = obj.getName();
        speed = obj.getWind().getSpeed();
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
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

    public void setTime(String time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
