package org.nepu.metro.manager;

import org.nepu.metro.builder.MetroConfigurationBuilder;
import org.nepu.metro.config.MetroConfiguration;
import org.nepu.metro.constant.WeekDay;
import org.nepu.metro.constant.Zone;
import org.nepu.metro.exception.MetroRouteNotAllowed;
import org.nepu.metro.model.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created By Sunil Kumar Pulugula on date 02/08/21
 **/
@Service
public class MetroPaymentManager implements InitializingBean {

    @Autowired
    private MetroConfigurationBuilder metroConfigurationBuilder;

    private MetroConfiguration configuration = null;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (configuration == null) {
            configuration = metroConfigurationBuilder.build();
        }
    }

    public JourneyCalculatedFare calculateDailyFare(List<Journey> journeys) {
        List<JourneyFare> journeyFares = new ArrayList<>();
        int totalFare = 0;
        journeys.sort(new JourneyComparator());
        int farthestCap = getFarthestDailyCap(journeys);
        for (Journey journey : journeys) {
            Boolean isPeakHour = isPeakHour(journey.getDay(), journey.getTime());
            JourneyFare journeyFare = new JourneyFare(journey.getDay(), journey.getTime(), journey.getFromZone(), journey.getToZone());
            int fare = getFare(journey, isPeakHour);
            if (totalFare + fare < farthestCap) {
                journeyFare.setCalculatedFare(fare);
                journeyFare.setExplanation(isPeakHour ? "Peak hours Single fare" : "Off-peak single fare");
            } else if (totalFare + fare > farthestCap) {
                if (totalFare >= farthestCap) {
                    journeyFare.setCalculatedFare(0);
                    journeyFare.setExplanation("The Daily cap reached " + farthestCap + " for zone " + journey.getFromZone().getZone() + " " + journey.getToZone().getZone() + "Charged 0 instead of " + fare);
                } else {
                    int value = farthestCap - totalFare;
                    journeyFare.setCalculatedFare(value);
                    journeyFare.setExplanation("The Daily cap reached " + farthestCap + " for zone " + journey.getFromZone().getZone() + " - " + journey.getToZone().getZone() + ", Charged " + value + " instead of " + fare);
                }
            }
            totalFare = totalFare + fare;
            journeyFares.add(journeyFare);
        }
        return new JourneyCalculatedFare(journeyFares, totalFare > farthestCap ? farthestCap : totalFare);

    }

    public JourneyCalculatedFare calculateWeeklyFare(List<Journey> journeys) {
        List<JourneyFare> journeyFares = new ArrayList<>();
        int totalFare = 0;
        int farthestCap = getFarthestWeeklyCap(journeys);
        List<List<Journey>> journeyWeeks = buildJourneyWeeks(journeys);
        for (List<Journey> journeyWeek : journeyWeeks) {
            int weeklyFare = 0;
            for (Journey journey : journeyWeek) {
                JourneyFare journeyFare = new JourneyFare(journey.getDay(), journey.getTime(), journey.getFromZone(), journey.getToZone());
                int weeklyDayFare = getWeeklyDayFare(journey.getFromZone(), journey.getToZone());
                if (weeklyFare + weeklyDayFare < farthestCap) {
                    totalFare = totalFare + weeklyDayFare;
                    journeyFare.setCalculatedFare(weeklyDayFare);
                    journeyFare.setExplanation("Daily cap reached");
                } else if (weeklyFare + weeklyDayFare > farthestCap) {
                    if (weeklyFare >= farthestCap) {
                        journeyFare.setCalculatedFare(0);
                        journeyFare.setExplanation("A weekly cap of " + farthestCap + " reached");
                    } else {
                        int value = farthestCap - weeklyFare;
                        totalFare = totalFare + value;
                        journeyFare.setCalculatedFare(value);
                        journeyFare.setExplanation("A weekly cap of " + farthestCap + " reached before the daily cap of " + value);
                    }
                }
                weeklyFare = weeklyFare + weeklyDayFare;
                journeyFares.add(journeyFare);
            }
        }
        return new JourneyCalculatedFare(journeyFares, totalFare);
    }

    private int getWeeklyDayFare(Zone fromZone, Zone toZone) {
        for (FareCap fareCap : configuration.getFareCaps()) {
            if (fareCap.getFromZone().equals(fromZone) && fareCap.getToZone().equals(toZone)) {
                return fareCap.getDailyCap();
            }
        }
        throw new MetroRouteNotAllowed("This route is not allowed from " + fromZone.getZone() + " to " + toZone.getZone());

    }

    private int getFare(Journey journey, Boolean isPeakHour) {
        List<MetroFare> metroFares = configuration.getMetroFares();
        for (MetroFare metroFare : metroFares) {
            if (journey.getFromZone().equals(metroFare.getFromZone()) && journey.getToZone().equals(metroFare.getToZone())) {
                if (isPeakHour) {
                    return metroFare.getPeakHourFare();
                } else {
                    return metroFare.getOffPeakHourFare();
                }
            }
        }
        throw new MetroRouteNotAllowed("This route is not allowed from " + journey.getFromZone().getZone() + " to " + journey.getToZone().getZone());
    }

    private int getFarthestDailyCap(List<Journey> journeys) {
        int highestCap = 0;
        for (Journey journey : journeys) {
            for (FareCap fareCap : configuration.getFareCaps()) {
                if (journey.getFromZone().equals(fareCap.getFromZone()) && journey.getToZone().equals(fareCap.getToZone()) && highestCap < fareCap.getDailyCap()) {
                    highestCap = fareCap.getDailyCap();
                }
            }
        }
        return highestCap;
    }

    private int getFarthestWeeklyCap(List<Journey> journeys) {
        int highestCap = 0;
        for (Journey journey : journeys) {
            for (FareCap fareCap : configuration.getFareCaps()) {
                if (journey.getFromZone().equals(fareCap.getFromZone()) && journey.getToZone().equals(fareCap.getToZone()) && highestCap < fareCap.getWeeklyCap()) {
                    highestCap = fareCap.getWeeklyCap();
                }
            }
        }
        return highestCap;
    }

    private boolean isPeakHour(WeekDay day, LocalTime time) {
        for (PeakHour peakHour : configuration.getPeakHours()) {
            if (peakHour.getDay().equals(day)) {
                int fromValue = time.compareTo(peakHour.getFromTime());
                int toValue = time.compareTo(peakHour.getToTime());
                if (fromValue >= 0 && toValue <= 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private List<List<Journey>> buildJourneyWeeks(List<Journey> journeys) {
        List<List<Journey>> journeyWeeks = new ArrayList<>();
        List<Journey> journeyWeek = null;
        for (Journey journey : journeys) {
            if (journeyWeek == null) {
                journeyWeek = new ArrayList<>();
                journeyWeeks.add(journeyWeek);
            }
            if (containsJourney(journeyWeek, journey)) {
                journeyWeek = new ArrayList<>();
                journeyWeeks.add(journeyWeek);
                journeyWeek.add(journey);
            } else {
                journeyWeek.add(journey);
            }
        }

        return journeyWeeks;
    }

    private boolean containsJourney(List<Journey> journeyWeek, Journey currentJourney) {
        int highestOrder = -1;
        for (Journey journey : journeyWeek) {
            if (journey.getDay().equals(currentJourney.getDay())) {
                return true;
            }
            if (highestOrder < journey.getDay().getOrder()) {
                highestOrder = journey.getDay().getOrder();
            }
        }

        if (highestOrder < currentJourney.getDay().getOrder()) {
            return false;
        } else {
            return true;
        }
    }


}
