package org.nepu.metro.model;

import org.nepu.metro.constant.WeekDay;
import org.nepu.metro.constant.Zone;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created By Sunil Kumar Pulugula on date 02/08/21
 **/
public class Journey {

    private WeekDay day;
    private LocalTime time;
    private Zone fromZone;
    private Zone toZone;

    public Journey(WeekDay day, LocalTime time, Zone fromZone, Zone toZone) {
        this.day = day;
        this.time = time;
        this.fromZone = fromZone;
        this.toZone = toZone;
    }

    public WeekDay getDay() {
        return day;
    }

    public Journey setDay(WeekDay day) {
        this.day = day;
        return this;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Zone getFromZone() {
        return fromZone;
    }

    public Journey setFromZone(Zone fromZone) {
        this.fromZone = fromZone;
        return this;
    }

    public Zone getToZone() {
        return toZone;
    }

    public Journey setToZone(Zone toZone) {
        this.toZone = toZone;
        return this;
    }


}
