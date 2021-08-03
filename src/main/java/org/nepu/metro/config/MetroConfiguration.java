package org.nepu.metro.config;

import org.nepu.metro.model.FareCap;
import org.nepu.metro.model.MetroFare;
import org.nepu.metro.model.PeakHour;

import java.util.List;

/**
 * Created By Sunil Kumar Pulugula on date 02/08/21
 **/
public class MetroConfiguration {

    private List<PeakHour> peakHours;
    private List<MetroFare> metroFares;
    private List<FareCap> fareCaps;

    public MetroConfiguration(List<PeakHour> peakHours, List<MetroFare> metroFares, List<FareCap> fareCaps) {
        this.peakHours = peakHours;
        this.metroFares = metroFares;
        this.fareCaps = fareCaps;
    }

    public List<PeakHour> getPeakHours() {
        return peakHours;
    }

    public void setPeakHours(List<PeakHour> peakHours) {
        this.peakHours = peakHours;
    }

    public List<MetroFare> getMetroFares() {
        return metroFares;
    }

    public void setMetroFares(List<MetroFare> metroFares) {
        this.metroFares = metroFares;
    }

    public List<FareCap> getFareCaps() {
        return fareCaps;
    }

    public void setFareCaps(List<FareCap> fareCaps) {
        this.fareCaps = fareCaps;
    }
}
