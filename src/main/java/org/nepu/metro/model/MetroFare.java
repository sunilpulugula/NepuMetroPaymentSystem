package org.nepu.metro.model;

import org.nepu.metro.constant.Zone;

/**
 * Created By Sunil Kumar Pulugula on date 02/08/21
 **/
public class MetroFare {

    private Zone fromZone;
    private Zone toZone;
    private int peakHourFare;
    private int offPeakHourFare;

    public MetroFare(Zone fromZone, Zone toZone, int peakHourFare, int offPeakHourFare) {
        this.fromZone = fromZone;
        this.toZone = toZone;
        this.peakHourFare = peakHourFare;
        this.offPeakHourFare = offPeakHourFare;
    }

    public Zone getFromZone() {
        return fromZone;
    }

    public void setFromZone(Zone fromZone) {
        this.fromZone = fromZone;
    }

    public Zone getToZone() {
        return toZone;
    }

    public void setToZone(Zone toZone) {
        this.toZone = toZone;
    }

    public int getPeakHourFare() {
        return peakHourFare;
    }

    public void setPeakHourFare(int peakHourFare) {
        this.peakHourFare = peakHourFare;
    }

    public int getOffPeakHourFare() {
        return offPeakHourFare;
    }

    public void setOffPeakHourFare(int offPeakHourFare) {
        this.offPeakHourFare = offPeakHourFare;
    }
}
