package org.ubp.ent.backend.model.classroom;

/**
 * Created by Anthony on 20/11/2015.
 */
public class RoomCapacity {

    private final Integer maxCapacity;

    public RoomCapacity(Integer maxCapacity) {
        if (maxCapacity < 1) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " with a maxCapacity equal or less than 0.");
        }

        this.maxCapacity = maxCapacity;
    }

}
