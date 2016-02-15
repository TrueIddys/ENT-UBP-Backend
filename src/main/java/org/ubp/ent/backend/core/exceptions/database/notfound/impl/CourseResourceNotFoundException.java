package org.ubp.ent.backend.core.exceptions.database.notfound.impl;

import org.ubp.ent.backend.core.exceptions.database.notfound.ResourceNotFoundException;

/**
 * Created by Maxime on 15/02/2016.
 */
public class CourseResourceNotFoundException extends ResourceNotFoundException {
    private static final long serialVersionUID = 3995920532385203593L;

    public CourseResourceNotFoundException() {
    }

    public CourseResourceNotFoundException(String message) {
        super(message);
    }

    public CourseResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CourseResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public CourseResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
