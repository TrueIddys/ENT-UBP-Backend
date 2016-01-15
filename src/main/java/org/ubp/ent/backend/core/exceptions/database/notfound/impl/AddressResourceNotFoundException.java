package org.ubp.ent.backend.core.exceptions.database.notfound.impl;

import org.ubp.ent.backend.core.exceptions.database.notfound.ResourceNotFoundException;

/**
 * Created by Anthony on 15/01/2016.
 */
public class AddressResourceNotFoundException extends ResourceNotFoundException {
    private static final long serialVersionUID = 7470450639076750887L;

    public AddressResourceNotFoundException() {
    }

    public AddressResourceNotFoundException(String message) {
        super(message);
    }

    public AddressResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public AddressResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
