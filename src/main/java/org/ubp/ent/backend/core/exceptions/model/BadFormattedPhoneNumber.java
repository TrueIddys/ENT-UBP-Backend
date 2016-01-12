package org.ubp.ent.backend.core.exceptions.model;

/**
 * Created by Anthony on 12/01/2016.
 */
public class BadFormattedPhoneNumber extends RuntimeException {
    private static final long serialVersionUID = -3765913060727719390L;

    public BadFormattedPhoneNumber() {
    }

    public BadFormattedPhoneNumber(String message) {
        super(message);
    }

    public BadFormattedPhoneNumber(String message, Throwable cause) {
        super(message, cause);
    }

    public BadFormattedPhoneNumber(Throwable cause) {
        super(cause);
    }

    public BadFormattedPhoneNumber(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
