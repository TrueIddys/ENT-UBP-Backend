package org.ubp.ent.backend.model.classroom;

/**
 * Created by Anthony on 20/11/2015.
 */
public class Capacity {

    private int maxCapacity;

    public Capacity(int maxCapacity) {
        if (maxCapacity < 1) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " with a maxCapacity equal or less than 0.");
        }

        this.maxCapacity = maxCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
}
