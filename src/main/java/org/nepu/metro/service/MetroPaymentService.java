package org.nepu.metro.service;

import org.nepu.metro.constant.WeekDay;
import org.nepu.metro.exception.JourneyInvalidInputException;
import org.nepu.metro.manager.MetroPaymentManager;
import org.nepu.metro.model.Journey;
import org.nepu.metro.model.JourneyCalculatedFare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By Sunil Kumar Pulugula on date 02/08/21
 **/
@Service
public class MetroPaymentService {

    @Autowired
    private MetroPaymentManager metroPaymentManager;

    public JourneyCalculatedFare calculateDailyFare(List<Journey> journeys){
        validate(journeys);
        validateDay(journeys);
        return metroPaymentManager.calculateDailyFare(journeys);
    }

    public JourneyCalculatedFare calculateWeeklyFare(List<Journey> journeys){
        validate(journeys);
        return metroPaymentManager.calculateWeeklyFare(journeys);
    }

    private void validateDay(List<Journey> journeys) {
        WeekDay firstWeekDay = journeys.get(0).getDay();
        for(Journey journey : journeys){
            if(!firstWeekDay.equals(journey.getDay()) ){
                throw new JourneyInvalidInputException("Please check journey day, it should be same in daily journey list");
            }
        }
    }

    private void validate(List<Journey> journeys) {
        if(journeys == null || journeys.size() == 0)
        {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }
    }

}
