package org.nepu.metro.constant;

/**
 * Created By Sunil Kumar Pulugula on date 02/08/21
 **/
public enum Zone {
    ZONE1("z1"),
    ZONE2("z2");

    private String zone;

    Zone(String zone) {
        this.zone = zone;
    }

    public String getZone() {
        return this.zone;
    }
}
