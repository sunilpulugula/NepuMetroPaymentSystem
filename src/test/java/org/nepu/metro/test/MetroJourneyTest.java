package org.nepu.metro.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nepu.metro.constant.WeekDay;
import org.nepu.metro.constant.Zone;
import org.nepu.metro.model.Journey;
import org.nepu.metro.model.JourneyCalculatedFare;
import org.nepu.metro.model.JourneyFare;
import org.nepu.metro.service.MetroPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.GenericXmlWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created By Sunil Kumar Pulugula on date 03/08/21
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = GenericXmlWebContextLoader.class,
        locations = "classpath:metro-test-spring.xml")
@WebAppConfiguration
public class MetroJourneyTest {

    @Autowired
    private MetroPaymentService metroPaymentService;

    @Test
    public void dailyJourneyTotalFareTest(){
        JourneyCalculatedFare journeyCalculatedFare = metroPaymentService.calculateDailyFare(getDailyJourneys());
        Assert.assertEquals("Total journey cost is not correct ", 120,journeyCalculatedFare.getTotalFare());
    }

    @Test
    public void weeklyJourneyTotalFareTest(){
        JourneyCalculatedFare journeyCalculatedFare = metroPaymentService.calculateWeeklyFare(getWeeklyJourneys());
        Assert.assertEquals("Total journey cost is not correct ", 720,journeyCalculatedFare.getTotalFare());
    }

    @Test
    public void dailyJourneyCalculatedFareTest(){
        JourneyCalculatedFare journeyCalculatedFare = metroPaymentService.calculateDailyFare(getDailyJourneys());
        List<JourneyFare> journeyFares = journeyCalculatedFare.getJourneyFares();
        Assert.assertEquals("Journey total number of records are not correct ",5, journeyFares.size());
        Assert.assertEquals("Journey calculated fare is not correct ",35, journeyFares.get(0).getCalculatedFare());
        Assert.assertEquals("Journey calculated fare is not correct ",25, journeyFares.get(1).getCalculatedFare());
        Assert.assertEquals("Journey calculated fare is not correct ",25, journeyFares.get(2).getCalculatedFare());
        Assert.assertEquals("Journey calculated fare is not correct ",30, journeyFares.get(3).getCalculatedFare());
        Assert.assertEquals("Journey calculated fare is not correct ",5, journeyFares.get(4).getCalculatedFare());
    }

    @Test
    public void weeklyJourneyCalculatedFareTest(){
        JourneyCalculatedFare journeyCalculatedFare = metroPaymentService.calculateWeeklyFare(getWeeklyJourneys());
        List<JourneyFare> journeyFares = journeyCalculatedFare.getJourneyFares();
        Assert.assertEquals("Journey total number of records are not correct ",8, journeyFares.size());
        Assert.assertEquals("Journey calculated fare is not correct ",120, journeyFares.get(0).getCalculatedFare());
        Assert.assertEquals("Journey calculated fare is not correct ",120, journeyFares.get(1).getCalculatedFare());
        Assert.assertEquals("Journey calculated fare is not correct ",120, journeyFares.get(2).getCalculatedFare());
        Assert.assertEquals("Journey calculated fare is not correct ",120, journeyFares.get(3).getCalculatedFare());
        Assert.assertEquals("Journey calculated fare is not correct ",100, journeyFares.get(4).getCalculatedFare());
        Assert.assertEquals("Journey calculated fare is not correct ",20, journeyFares.get(5).getCalculatedFare());
        Assert.assertEquals("Journey calculated fare is not correct ",0, journeyFares.get(6).getCalculatedFare());
        Assert.assertEquals("Journey calculated fare is not correct ",120, journeyFares.get(7).getCalculatedFare());
    }

    private static List<Journey> getDailyJourneys(){
        List<Journey> journeys = new ArrayList<>();
        journeys.add(new Journey(WeekDay.MONDAY, LocalTime.parse("10:20:21.629"), Zone.ZONE2,Zone.ZONE1));
        journeys.add(new Journey(WeekDay.MONDAY, LocalTime.parse("10:45:21.629"), Zone.ZONE1,Zone.ZONE1));
        journeys.add(new Journey(WeekDay.MONDAY, LocalTime.parse("16:15:21.629"), Zone.ZONE1,Zone.ZONE1));
        journeys.add(new Journey(WeekDay.MONDAY, LocalTime.parse("18:15:21.629"), Zone.ZONE1,Zone.ZONE1));
        journeys.add(new Journey(WeekDay.MONDAY, LocalTime.parse("19:00:21.629"), Zone.ZONE1,Zone.ZONE2));
        return journeys;
    }

    private static List<Journey> getWeeklyJourneys(){
        List<Journey> journeys = new ArrayList<>();
        journeys.add(new Journey(WeekDay.MONDAY, null, Zone.ZONE1,Zone.ZONE2));
        journeys.add(new Journey(WeekDay.TUESDAY, null, Zone.ZONE1,Zone.ZONE2));
        journeys.add(new Journey(WeekDay.WEDNESDAY, null, Zone.ZONE1,Zone.ZONE2));
        journeys.add(new Journey(WeekDay.THURSDAY, null, Zone.ZONE1,Zone.ZONE2));
        journeys.add(new Journey(WeekDay.FRIDAY, null, Zone.ZONE1,Zone.ZONE1));
        journeys.add(new Journey(WeekDay.SATURDAY, null, Zone.ZONE1,Zone.ZONE2));
        journeys.add(new Journey(WeekDay.SUNDAY, null, Zone.ZONE1,Zone.ZONE2));
        journeys.add(new Journey(WeekDay.MONDAY, null, Zone.ZONE1,Zone.ZONE2));
        return journeys;
    }
}
