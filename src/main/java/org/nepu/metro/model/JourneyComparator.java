package org.nepu.metro.model;

import java.util.Comparator;

/**
 * Created By Sunil Kumar Pulugula on date 02/08/21
 **/
public class JourneyComparator implements Comparator<Journey> {

    @Override
    public int compare(Journey o1, Journey o2) {
        return o1.getTime().compareTo(o2.getTime());
    }
}
