package org.ubp.ent.backend.core.exceptions.database.notfound.impl;

import org.ubp.ent.backend.core.exceptions.database.notfound.ResourceNotFoundException;

/**
 * Created by Anthony on 15/01/2016.
 */
public class PhoneResourceNotFoundException extends ResourceNotFoundException {
    private static final long serialVersionUID = 7011577432875236619L;

    public PhoneResourceNotFoundException() {
    }

    public PhoneResourceNotFoundException(String message) {
        super(message);
    }

    public PhoneResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhoneResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public PhoneResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
