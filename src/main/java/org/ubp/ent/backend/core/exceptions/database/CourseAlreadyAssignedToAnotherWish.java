package org.ubp.ent.backend.core.exceptions.database;

/**
 * Created by Anthony on 23/02/2016.
 */
public class CourseAlreadyAssignedToAnotherWish extends RuntimeException {
    private static final long serialVersionUID = 1779692998751417373L;

    public CourseAlreadyAssignedToAnotherWish() {
    }

    public CourseAlreadyAssignedToAnotherWish(String message) {
        super(message);
    }

    public CourseAlreadyAssignedToAnotherWish(String message, Throwable cause) {
        super(message, cause);
    }

    public CourseAlreadyAssignedToAnotherWish(Throwable cause) {
        super(cause);
    }

    public CourseAlreadyAssignedToAnotherWish(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
