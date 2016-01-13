package org.ubp.ent.backend.core.exceptions.model;

/**
 * Created by Anthony on 13/01/2016.
 */
public class BadFormattedEmailAddress extends RuntimeException {
    private static final long serialVersionUID = -2869530471866393157L;

    public BadFormattedEmailAddress() {
    }

    public BadFormattedEmailAddress(String message) {
        super(message);
    }

    public BadFormattedEmailAddress(String message, Throwable cause) {
        super(message, cause);
    }

    public BadFormattedEmailAddress(Throwable cause) {
        super(cause);
    }

    public BadFormattedEmailAddress(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
