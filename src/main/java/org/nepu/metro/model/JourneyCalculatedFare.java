package org.nepu.metro.model;

import java.util.List;

/**
 * Created By Sunil Kumar Pulugula on date 02/08/21
 **/
public class JourneyCalculatedFare {

    private List<JourneyFare> journeyFares;
    private int totalFare;

    public JourneyCalculatedFare(List<JourneyFare> journeyFares, int totalFare) {
        this.journeyFares = journeyFares;
        this.totalFare = totalFare;
    }

    public List<JourneyFare> getJourneyFares() {
        return journeyFares;
    }

    public void setJourneyFares(List<JourneyFare> journeyFares) {
        this.journeyFares = journeyFares;
    }

    public int getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(int totalFare) {
        this.totalFare = totalFare;
    }

    @Override
    public String toString() {
        return "" + journeyFares;
    }
}
