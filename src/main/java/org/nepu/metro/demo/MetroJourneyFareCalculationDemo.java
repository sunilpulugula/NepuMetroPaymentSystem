package org.nepu.metro.demo;

import org.nepu.metro.constant.WeekDay;
import org.nepu.metro.constant.Zone;
import org.nepu.metro.model.*;
import org.nepu.metro.service.MetroPaymentService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created By Sunil Kumar Pulugula on date 02/08/21
 **/
public class MetroJourneyFareCalculationDemo {

    public static void main( String[] args )
    {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("metro-spring.xml");
        MetroPaymentService metroPaymentService = (MetroPaymentService)ac.getBean(MetroPaymentService.class);

        System.out.println("-----  Daily Journey ------");
        JourneyCalculatedFare journeyCalculatedFare = metroPaymentService.calculateDailyFare(getDailyJourneys());
        displayDailyFare(journeyCalculatedFare);
        System.out.println("Total Fare = " + journeyCalculatedFare.getTotalFare());

        System.out.println("");
        System.out.println("-----  Weekly Journey ------");
        JourneyCalculatedFare journeyCalculatedWeeklyFare = metroPaymentService.calculateWeeklyFare(getWeeklyJourneys());
        displayWeeklyFare(journeyCalculatedWeeklyFare);
        System.out.println("Total Fare = " + journeyCalculatedWeeklyFare.getTotalFare());
    }

    private static void displayDailyFare(JourneyCalculatedFare journeyCalculatedFare) {
        System.out.println("Day          Time        FromZone     toZone   CalculatedFare      Explanation");
        System.out.println("-------------------------------------------------------------------------------");
        for(JourneyFare journeyFare : journeyCalculatedFare.getJourneyFares()){

            System.out.println(journeyFare.getDay()+"     "+journeyFare.getTime()+"     "+journeyFare.getFromZone() +"     "+journeyFare.getToZone()+"     "+ journeyFare.getCalculatedFare()+"            "+journeyFare.getExplanation());
        }
    }

    private static void displayWeeklyFare(JourneyCalculatedFare journeyCalculatedWeeklyFare) {
        System.out.println("Day           Time     FromZone   toZone   CalculatedFare        Explanation");
        System.out.println("-------------------------------------------------------------------------------");
        for(JourneyFare journeyFare : journeyCalculatedWeeklyFare.getJourneyFares()){
            String day = journeyFare.getDay().getDay();
            int space = 9 - day.length();
            for(int i=0;i<space;i++){
                day = day +" ";
            }
            System.out.println(day+"     "+journeyFare.getTime()+"     "+journeyFare.getFromZone() +"     "+journeyFare.getToZone()+"           "+ journeyFare.getCalculatedFare()+"          "+journeyFare.getExplanation());
        }
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
