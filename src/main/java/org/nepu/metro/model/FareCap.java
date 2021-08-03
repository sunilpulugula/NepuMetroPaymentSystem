package org.nepu.metro.model;

import org.nepu.metro.constant.Zone;

/**
 * Created By Sunil Kumar Pulugula on date 02/08/21
 **/
public class FareCap {

    private Zone fromZone;
    private Zone toZone;
    private int dailyCap;
    private int weeklyCap;

    public FareCap(Zone fromZone, Zone toZone, int dailyCap, int weeklyCap) {
        this.fromZone = fromZone;
        this.toZone = toZone;
        this.dailyCap = dailyCap;
        this.weeklyCap = weeklyCap;
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

    public int getDailyCap() {
        return dailyCap;
    }

    public void setDailyCap(int dailyCap) {
        this.dailyCap = dailyCap;
    }

    public int getWeeklyCap() {
        return weeklyCap;
    }

    public void setWeeklyCap(int weeklyCap) {
        this.weeklyCap = weeklyCap;
    }
}
