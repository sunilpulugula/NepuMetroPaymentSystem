package org.nepu.metro.constant;

/**
 * Created By Sunil Kumar Pulugula on date 02/08/21
 **/
public enum WeekDay {

    MONDAY("Monday",0),
    TUESDAY("Tuesday", 1),
    WEDNESDAY("Wednesday", 2),
    THURSDAY("Thursday", 3),
    FRIDAY("Friday",4),
    SATURDAY("Saturday", 5),
    SUNDAY("Sunday",6);


    private String day;
    private int order;

    WeekDay(String day, int order) {
        this.day = day;
        this.order = order;
    }

    public String getDay() {
        return this.day;
    }

    public int getOrder() {
        return order;
    }
}
