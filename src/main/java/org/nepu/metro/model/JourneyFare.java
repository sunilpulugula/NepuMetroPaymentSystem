package org.nepu.metro.model;

import org.nepu.metro.constant.WeekDay;
import org.nepu.metro.constant.Zone;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created By Sunil Kumar Pulugula on date 02/08/21
 **/
public class JourneyFare extends Journey{

    private int calculatedFare;
    private String explanation;

    public JourneyFare(WeekDay day, LocalTime time, Zone fromZone, Zone toZone) {
        super(day, time, fromZone, toZone);
    }

    public int getCalculatedFare() {
        return calculatedFare;
    }

    public JourneyFare setCalculatedFare(int calculatedFare) {
        this.calculatedFare = calculatedFare;
        return this;
    }

    public String getExplanation() {
        return explanation;
    }

    public JourneyFare setExplanation(String explanation) {
        this.explanation = explanation;
        return this;
    }

    @Override
    public String toString() {
        return super.getDay()+"     "+super.getTime()+"     "+super.getFromZone() +"     "+super.getToZone()+"     "+ this.calculatedFare+"     "+this.explanation ;
    }
}
