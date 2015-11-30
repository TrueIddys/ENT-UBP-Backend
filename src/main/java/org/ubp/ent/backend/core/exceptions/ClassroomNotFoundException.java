package org.ubp.ent.backend.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Anthony on 29/11/2015.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No classroom found.")
public class ClassroomNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 4895920532385203593L;

    public ClassroomNotFoundException() {
    }

    public ClassroomNotFoundException(String message) {
        super(message);
    }

    public ClassroomNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClassroomNotFoundException(Throwable cause) {
        super(cause);
    }

    public ClassroomNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
