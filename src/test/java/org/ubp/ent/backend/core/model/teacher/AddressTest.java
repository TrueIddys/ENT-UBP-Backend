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

    public static Address createOne() {
        return createOne(VALID_STREET_NUMBER, VALID_STREET, VALID_ZIP, VALID_CITY);
    }

    public static Address createOne(String streetNumber, String street, String zip, String city) {
        return new Address(streetNumber, street, zip, city);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithEmptyZip() {
        createOne(VALID_STREET_NUMBER, VALID_STREET, " ", VALID_CITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullZip() {
        createOne(VALID_STREET_NUMBER, VALID_STREET, null, VALID_CITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithEmptyCity() {
        createOne(VALID_STREET_NUMBER, VALID_STREET, VALID_ZIP, " ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullCity() {
        createOne(VALID_STREET_NUMBER, VALID_STREET, VALID_ZIP, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithEmptyStreetNumber() {
        createOne(" ", VALID_STREET, VALID_ZIP, VALID_CITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullStreetNumber() {
        createOne(null, VALID_STREET, VALID_ZIP, VALID_CITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithEmptyStreet() {
        createOne(VALID_STREET_NUMBER, " ", VALID_ZIP, VALID_CITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullStreet() {
        createOne(VALID_STREET_NUMBER, null, VALID_ZIP, VALID_CITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNonNumberZip() {
        createOne(VALID_STREET_NUMBER, VALID_STREET, "abcde", VALID_CITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNonFiveDigitNumberZip() {
        createOne(VALID_STREET_NUMBER, VALID_STREET, "512", VALID_CITY);
    }

    @Test
    public void shouldAcceptSimpleDigitAndDigitPlusComplementaryStringAsStreetNumber() {
        Address address = createOne("6", VALID_STREET, VALID_ZIP, VALID_CITY);;
        assertThat(address.getStreetNumber()).isEqualTo("6");

        address = createOne("63", VALID_STREET, VALID_ZIP, VALID_CITY);
        assertThat(address.getStreetNumber()).isEqualTo("63");

        address = createOne("6 bis", VALID_STREET, VALID_ZIP, VALID_CITY);
        assertThat(address.getStreetNumber()).isEqualTo("6 Bis");

        address = createOne("63 bis", VALID_STREET, VALID_ZIP, VALID_CITY);
        assertThat(address.getStreetNumber()).isEqualTo("63 Bis");

        address = createOne("6bis", VALID_STREET, VALID_ZIP, VALID_CITY);
        assertThat(address.getStreetNumber()).isEqualTo("6 Bis");

        address = createOne("6bis.", VALID_STREET, VALID_ZIP, VALID_CITY);
        assertThat(address.getStreetNumber()).isEqualTo("6 Bis.");
    }

    @Test
    public void shouldCapitalizeStreet() {
        Address address = createOne(VALID_STREET_NUMBER, "clermont ferrand", VALID_ZIP, VALID_CITY);
        assertThat(address.getStreet()).isEqualTo("Clermont Ferrand");

        address = createOne(VALID_STREET_NUMBER, "clermont-ferrand", VALID_ZIP, VALID_CITY);
        assertThat(address.getStreet()).isEqualTo("Clermont-Ferrand");
    }

    @Test
    public void shouldInstantiate() {
        Address address = createOne();
        assertThat(address.getStreetNumber()).isEqualTo(VALID_STREET_NUMBER);
        assertThat(address.getStreet()).isEqualTo(VALID_STREET);
        assertThat(address.getZip()).isEqualTo(VALID_ZIP);
        assertThat(address.getCity()).isEqualTo(VALID_CITY);
    }

    @Test
    public void shouldCapitalizeAndTrim() {
        String streetNumber = " " + VALID_STREET_NUMBER.toLowerCase().replaceAll("\\s","") + " ";
        String street = " " + VALID_STREET.toLowerCase() + " ";
        String zip = " " + VALID_ZIP.toLowerCase() + " ";
        String city = " " + VALID_CITY.toLowerCase() + " ";

        Address address = createOne(streetNumber, street, zip, city);
        assertThat(address.getStreetNumber()).isEqualTo(VALID_STREET_NUMBER);
        assertThat(address.getStreet()).isEqualTo(VALID_STREET);
        assertThat(address.getZip()).isEqualTo(VALID_ZIP);
        assertThat(address.getCity()).isEqualTo(VALID_CITY);
    }

}