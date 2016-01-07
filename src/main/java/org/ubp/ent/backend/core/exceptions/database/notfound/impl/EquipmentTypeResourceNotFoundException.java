package org.ubp.ent.backend.core.exceptions.database.notfound.impl;

import org.ubp.ent.backend.core.exceptions.database.notfound.ResourceNotFoundException;

/**
 * Created by Anthony on 30/11/2015.
 */
public class EquipmentTypeResourceNotFoundException extends ResourceNotFoundException {
    private static final long serialVersionUID = -5305833544895773050L;

    public EquipmentTypeResourceNotFoundException() {
    }

    public EquipmentTypeResourceNotFoundException(String message) {
        super(message);
    }

    public EquipmentTypeResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EquipmentTypeResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public EquipmentTypeResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
