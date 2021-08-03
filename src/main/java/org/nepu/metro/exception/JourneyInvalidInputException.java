package org.nepu.metro.exception;

/**
 * Created By Sunil Kumar Pulugula on date 02/08/21
 **/
public class JourneyInvalidInputException extends RuntimeException {

    public JourneyInvalidInputException(String msg, Throwable t) {
        super(msg, t);
    }

    public JourneyInvalidInputException(String msg) {
        super(msg);
    }
}
