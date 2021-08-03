package org.nepu.metro.model;

import org.nepu.metro.constant.WeekDay;

import java.time.LocalTime;

/**
 * Created By Sunil Kumar Pulugula on date 02/08/21
 **/
public class PeakHour {

    private WeekDay day;
    private LocalTime fromTime;
    private LocalTime toTime;

    public PeakHour(WeekDay day, LocalTime fromTime, LocalTime toTime) {
        this.day = day;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    public WeekDay getDay() {
        return day;
    }

    public void setDay(WeekDay day) {
        this.day = day;
    }

    public LocalTime getFromTime() {
        return fromTime;
    }

    public void setFromTime(LocalTime fromTime) {
        this.fromTime = fromTime;
    }

    public LocalTime getToTime() {
        return toTime;
    }

    public void setToTime(LocalTime toTime) {
        this.toTime = toTime;
    }
}
