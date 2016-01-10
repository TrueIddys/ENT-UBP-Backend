package org.ubp.ent.backend.core.model.teacher;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 09/01/2016.
 */
public class AddressTest {
    private static final String VALID_ZIP = "63000";
    private static final String VALID_CITY = "Clermont-Ferrand";
    private static final String VALID_STREET_NUMBER = "6 Bis";
    private static final String VALID_STREET = "Rue Jacques Essebag";

<<<<<<< Updated upstream
=======
    public static Address createOne() {
        return new Address(VALID_ZIP, VALID_CITY, VALID_STREET_NUMBER, VALID_STREET);
    }

>>>>>>> Stashed changes
    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithEmptyZip() {
        new Address(" ", VALID_CITY, VALID_STREET_NUMBER, VALID_STREET);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullZip() {
        new Address(null, VALID_CITY, VALID_STREET_NUMBER, VALID_STREET);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNonNumberZip() {
        new Address("abcde", VALID_CITY, VALID_STREET_NUMBER, VALID_STREET);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNonFiveDigitNumberZip() {
        new Address("512", VALID_CITY, VALID_STREET_NUMBER, VALID_STREET);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithEmptyCity() {
        new Address(VALID_ZIP, " ", VALID_STREET_NUMBER, VALID_STREET);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullCity() {
        new Address(VALID_ZIP, null, VALID_STREET_NUMBER, VALID_STREET);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithEmptyStreetNumber() {
        new Address(VALID_ZIP, VALID_CITY, " ", VALID_STREET);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullStreetNumber() {
        new Address(VALID_ZIP, VALID_CITY, null, VALID_STREET);
    }

    @Test
    public void shouldAcceptSimpleDigitAndDigitPlusComplementaryStringAsStreetNumber() {
        Address address = new Address(VALID_ZIP, VALID_CITY, "6", VALID_STREET);
        assertThat(address.getStreetNumber()).isEqualTo("6");

        address = new Address(VALID_ZIP, VALID_CITY, "63", VALID_STREET);
        assertThat(address.getStreetNumber()).isEqualTo("63");

        address = new Address(VALID_ZIP, VALID_CITY, "6 Bis", VALID_STREET);
        assertThat(address.getStreetNumber()).isEqualTo("6 Bis");

        address = new Address(VALID_ZIP, VALID_CITY, "63 Bis", VALID_STREET);
        assertThat(address.getStreetNumber()).isEqualTo("63 Bis");

        address = new Address(VALID_ZIP, VALID_CITY, "6bis", VALID_STREET);
        assertThat(address.getStreetNumber()).isEqualTo("6 Bis");

        address = new Address(VALID_ZIP, VALID_CITY, "6bis.", VALID_STREET);
        assertThat(address.getStreetNumber()).isEqualTo("6 Bis.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithEmptyStreet() {
        new Address(VALID_ZIP, VALID_CITY, VALID_STREET_NUMBER, " ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullStreet() {
        new Address(VALID_ZIP, VALID_CITY, VALID_STREET_NUMBER, null);
    }

    @Test
    public void shouldCapitalizeStreet() {
        Address address = new Address(VALID_ZIP, VALID_CITY, VALID_STREET_NUMBER, "clermont ferrand");
        assertThat(address.getStreet()).isEqualTo("Clermont Ferrand");

        address = new Address(VALID_ZIP, VALID_CITY, VALID_STREET_NUMBER, "clermont-ferrand");
        assertThat(address.getStreet()).isEqualTo("Clermont-Ferrand");
    }

    @Test
    public void shouldInstantiate() {
        Address address = new Address(VALID_ZIP, VALID_CITY, VALID_STREET_NUMBER, VALID_STREET);
        assertThat(address.getZip()).isEqualTo(VALID_ZIP);
        assertThat(address.getCity()).isEqualTo(VALID_CITY);
        assertThat(address.getStreetNumber()).isEqualTo(VALID_STREET_NUMBER);
        assertThat(address.getStreet()).isEqualTo(VALID_STREET);
    }

    @Test
    public void shouldCapitalizeAndTrim() {
        String zip = " " + VALID_ZIP.toLowerCase() + " ";
        String city = " " + VALID_CITY.toLowerCase() + " ";
        String streetNumber = " " + VALID_STREET_NUMBER.toLowerCase().replaceAll("\\s","") + " ";
        String street = " " + VALID_STREET.toLowerCase() + " ";

        Address address = new Address(zip, city, streetNumber, street);
        assertThat(address.getZip()).isEqualTo(VALID_ZIP);
        assertThat(address.getCity()).isEqualTo(VALID_CITY);
        assertThat(address.getStreetNumber()).isEqualTo(VALID_STREET_NUMBER);
        assertThat(address.getStreet()).isEqualTo(VALID_STREET);
    }

}