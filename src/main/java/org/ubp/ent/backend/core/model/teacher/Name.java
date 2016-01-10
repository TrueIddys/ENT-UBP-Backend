package org.ubp.ent.backend.core.model.teacher;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

/**
 * Created by Anthony on 09/01/2016.
 */
public class Name {

    private String firstName;
    private String lastName;

    public Name(String firstName, String lastName) {
        if (StringUtils.isBlank(firstName)) {
            throw new IllegalArgumentException("Cannot create a " + getClass().getName() + " without a firstName : '" + firstName + "' given");
        }
        if (StringUtils.isBlank(lastName)) {
            throw new IllegalArgumentException("Cannot create a " + getClass().getName() + " without a lastName : '" + lastName + "' given");
        }

        this.firstName = formatFirstName(firstName);
        this.lastName = formatLastName(lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    private String formatFirstName(String firstName) {
        firstName = firstName.trim();
        firstName = firstName.toLowerCase();
        return WordUtils.capitalize(firstName, ' ', '_', '-');
    }

    private String formatLastName(String lastName) {
        lastName = lastName.trim();
        return lastName.toUpperCase();
    }

}
