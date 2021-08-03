package org.nepu.metro.exception;

/**
 * Created By Sunil Kumar Pulugula on date 02/08/21
 **/
public class MetroRouteNotAllowed extends RuntimeException {

    public MetroRouteNotAllowed(String msg, Throwable t) {
        super(msg, t);
    }

    public MetroRouteNotAllowed(String msg) {
        super(msg);
    }
}
