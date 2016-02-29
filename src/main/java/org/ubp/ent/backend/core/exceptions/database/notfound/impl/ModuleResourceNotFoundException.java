package org.ubp.ent.backend.core.exceptions.database.notfound.impl;

import org.ubp.ent.backend.core.exceptions.database.notfound.ResourceNotFoundException;

/**
 * Created by Maxime on 28/02/2016.
 */
public class ModuleResourceNotFoundException extends ResourceNotFoundException {

    private static final long serialVersionUID = -619660243957232384L;

    public ModuleResourceNotFoundException() {
    }

    public ModuleResourceNotFoundException(String message) {
        super(message);
    }

    public ModuleResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModuleResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public ModuleResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
