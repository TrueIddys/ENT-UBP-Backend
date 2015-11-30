package org.ubp.ent.backend.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Anthony on 30/11/2015.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No EquipmentType found.")
public class EquipmentTypeNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -5305833544895773050L;

    public EquipmentTypeNotFoundException() {
    }

    public EquipmentTypeNotFoundException(String message) {
        super(message);
    }

    public EquipmentTypeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EquipmentTypeNotFoundException(Throwable cause) {
        super(cause);
    }

    public EquipmentTypeNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
