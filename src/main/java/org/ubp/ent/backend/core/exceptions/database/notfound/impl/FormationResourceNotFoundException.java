package org.ubp.ent.backend.core.exceptions.database.notfound.impl;

import org.ubp.ent.backend.core.exceptions.database.notfound.ResourceNotFoundException;

/**
 * Created by Anthony on 26/02/2016.
 */
public class FormationResourceNotFoundException extends ResourceNotFoundException {
    private static final long serialVersionUID = -6192949307818963963L;

    public FormationResourceNotFoundException() {
    }

    public FormationResourceNotFoundException(String message) {
        super(message);
    }

    public FormationResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FormationResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public FormationResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
