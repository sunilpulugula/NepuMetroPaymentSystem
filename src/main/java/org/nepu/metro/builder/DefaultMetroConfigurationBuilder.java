package org.nepu.metro.builder;

import org.nepu.metro.config.MetroConfiguration;
import org.nepu.metro.constant.WeekDay;
import org.nepu.metro.constant.Zone;
import org.nepu.metro.model.FareCap;
import org.nepu.metro.model.MetroFare;
import org.nepu.metro.model.PeakHour;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created By Sunil Kumar Pulugula on date 02/08/21
 **/
@Service
public class DefaultMetroConfigurationBuilder implements MetroConfigurationBuilder {

    private MetroConfiguration configurationBuilder = null;

    private List<PeakHour> peakHours;
    private List<MetroFare> metroFare;
    private List<FareCap> fareCap;

    @Override
    public MetroConfiguration build() {
        if (configurationBuilder == null) {
            configurationBuilder = new MetroConfiguration(buildPeakHours(), buildMetroFares(), buildFareCaps());
        }
        return configurationBuilder;
    }

    public List<PeakHour> getPeakHours() {
        return peakHours;
    }

    public void setPeakHours(List<PeakHour> peakHours) {
        this.peakHours = peakHours;
    }

    public List<MetroFare> getMetroFare() {
        return metroFare;
    }

    public void setMetroFare(List<MetroFare> metroFare) {
        this.metroFare = metroFare;
    }

    public List<FareCap> getFareCap() {
        return fareCap;
    }

    public void setFareCap(List<FareCap> fareCap) {
        this.fareCap = fareCap;
    }

    private static List<FareCap> buildFareCaps() {
        List<FareCap> fareCaps = new ArrayList<>();
        fareCaps.add(new FareCap(Zone.ZONE1,Zone.ZONE1,100, 500));
        fareCaps.add(new FareCap(Zone.ZONE1,Zone.ZONE2,120, 600));
        fareCaps.add(new FareCap(Zone.ZONE2,Zone.ZONE1,120, 600));
        fareCaps.add(new FareCap(Zone.ZONE2,Zone.ZONE2,80, 400));
        return fareCaps;
    }

    private static List<MetroFare> buildMetroFares() {
        List<MetroFare> metroFares = new ArrayList<>();
        metroFares.add(new MetroFare(Zone.ZONE1,Zone.ZONE1,30,25));
        metroFares.add(new MetroFare(Zone.ZONE1,Zone.ZONE2,35,30));
        metroFares.add(new MetroFare(Zone.ZONE2,Zone.ZONE1,35,30));
        metroFares.add(new MetroFare(Zone.ZONE2,Zone.ZONE2,25,20));
        return metroFares;
    }

    private static List<PeakHour> buildPeakHours() {
        List<PeakHour> peakHours = new ArrayList<>();
        peakHours.add(new PeakHour(WeekDay.MONDAY, LocalTime.of(07,00,00),  LocalTime.of(10,30,00)));
        peakHours.add(new PeakHour(WeekDay.MONDAY, LocalTime.of(17,00,00),  LocalTime.of(20,00,00)));
        peakHours.add(new PeakHour(WeekDay.TUESDAY, LocalTime.of(07,00,00),  LocalTime.of(10,30,00)));
        peakHours.add(new PeakHour(WeekDay.TUESDAY, LocalTime.of(17,00,00),  LocalTime.of(20,00,00)));
        peakHours.add(new PeakHour(WeekDay.WEDNESDAY, LocalTime.of(07,00,00),  LocalTime.of(10,30,00)));
        peakHours.add(new PeakHour(WeekDay.WEDNESDAY, LocalTime.of(17,00,00),  LocalTime.of(20,00,00)));
        peakHours.add(new PeakHour(WeekDay.THURSDAY, LocalTime.of(07,00,00),  LocalTime.of(10,30,00)));
        peakHours.add(new PeakHour(WeekDay.THURSDAY, LocalTime.of(17,00,00),  LocalTime.of(20,00,00)));
        peakHours.add(new PeakHour(WeekDay.FRIDAY, LocalTime.of(07,00,00),  LocalTime.of(10,30,00)));
        peakHours.add(new PeakHour(WeekDay.FRIDAY, LocalTime.of(17,00,00),  LocalTime.of(20,00,00)));
        peakHours.add(new PeakHour(WeekDay.SATURDAY, LocalTime.of(9,00,00),  LocalTime.of(11,00,00)));
        peakHours.add(new PeakHour(WeekDay.SATURDAY, LocalTime.of(18,00,00),  LocalTime.of(22,00,00)));
        peakHours.add(new PeakHour(WeekDay.SUNDAY, LocalTime.of(9,00,00),  LocalTime.of(11,00,00)));
        peakHours.add(new PeakHour(WeekDay.SUNDAY, LocalTime.of(18,00,00),  LocalTime.of(22,00,00)));
        return peakHours;
    }
}
