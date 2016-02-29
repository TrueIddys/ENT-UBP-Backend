package org.ubp.ent.backend.core.exceptions.database.notfound.impl;

import org.ubp.ent.backend.core.exceptions.database.notfound.ResourceNotFoundException;

/**
 * Created by Maxime on 29/02/2016.
 */
public class TeachingUnitResourceNotFoundException extends ResourceNotFoundException{
    private static final long serialVersionUID = 5854781512835772237L;

    public TeachingUnitResourceNotFoundException() {
    }

    public TeachingUnitResourceNotFoundException(String message) {
        super(message);
    }

    public TeachingUnitResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TeachingUnitResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public TeachingUnitResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
