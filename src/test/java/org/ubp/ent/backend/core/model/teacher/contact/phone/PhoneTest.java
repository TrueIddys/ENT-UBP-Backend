package org.ubp.ent.backend.core.model.teacher.contact.phone;

import org.junit.Test;
import org.ubp.ent.backend.core.exceptions.model.BadFormattedPhoneNumber;
import org.ubp.ent.backend.core.model.teacher.contact.phone.Phone;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 12/01/2016.
 */
public class PhoneTest {
    private static final String VALID_REGULAR_NUMBER = "06 11 11 11 11";
    private static final String VALID_NATIONAL_NUMBER = "+336 11 11 11 11";
    private static final String VALID_FULL_NATIONAL_NUMBER = "00336 11 11 11 11";

    public Phone createOne() {
        return new Phone(VALID_REGULAR_NUMBER);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullNumber() {
        new Phone(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithEmptyNumber() {
        new Phone(" ");
    }

    @Test
    public void shouldInstantiate() {
        Phone model = new Phone(VALID_REGULAR_NUMBER);
        assertThat(model.getNumber()).isEqualTo(VALID_NATIONAL_NUMBER);
    }

    @Test(expected = BadFormattedPhoneNumber.class)
    public void shouldRejectNonPhoneNumber() {
        new Phone("abcdefghij");
    }

    /*
    * Regular number : 06 11 11 11 11 (starts with 0X, followed by 8 more digits)
    * National number : +336 11 11 11 11 (starts with +336, followed by 8 more digits)
    * Full National number : 0033 11 11 11 11 (starts with 0X, followed by 8 more digits)
     */
    @Test(expected = BadFormattedPhoneNumber.class)
    public void shouldRejectTooShortRegularNumber() {
        new Phone("06 12 13");
    }

    @Test(expected = BadFormattedPhoneNumber.class)
    public void shouldRejectTooLongRegularNumber() {
        new Phone(VALID_REGULAR_NUMBER + " 11");
    }

    @Test(expected = BadFormattedPhoneNumber.class)
    public void shouldRejectTooShortNationalNumber() {
        new Phone("+336 12 13");
    }

    @Test(expected = BadFormattedPhoneNumber.class)
    public void shouldRejectTooLongNationalNumber() {
        new Phone(VALID_NATIONAL_NUMBER + " 11");
    }

    @Test(expected = BadFormattedPhoneNumber.class)
    public void shouldRejectTooShortFullNationalNumber() {
        new Phone("00336 12 13");
    }

    @Test(expected = BadFormattedPhoneNumber.class)
    public void shouldRejectTooLongFullNationalNumber() {
        new Phone(VALID_FULL_NATIONAL_NUMBER + "11");
    }

    @Test
    public void shouldFormatProperly() {
        // regular
        Phone phone = new Phone("06 11 12 13 14");
        assertThat(phone.getNumber()).isEqualTo("+336 11 12 13 14");
        phone = new Phone("0611121314");
        assertThat(phone.getNumber()).isEqualTo("+336 11 12 13 14");
        phone = new Phone("06.11.12.13.14");
        assertThat(phone.getNumber()).isEqualTo("+336 11 12 13 14");

        // national
        phone = new Phone("+336 11 12 13 14");
        assertThat(phone.getNumber()).isEqualTo("+336 11 12 13 14");
        phone = new Phone("+33611121314");
        assertThat(phone.getNumber()).isEqualTo("+336 11 12 13 14");
        phone = new Phone("+336.11.12.13.14");
        assertThat(phone.getNumber()).isEqualTo("+336 11 12 13 14");

        //full national
        phone = new Phone("00336 11 12 13 14");
        assertThat(phone.getNumber()).isEqualTo("+336 11 12 13 14");
        phone = new Phone("0033611121314");
        assertThat(phone.getNumber()).isEqualTo("+336 11 12 13 14");
        phone = new Phone("00336.11.12.13.14");
        assertThat(phone.getNumber()).isEqualTo("+336 11 12 13 14");
    }

    @Test
    public void shouldBeEqualByNumber() {
        assertThat(createOne()).isEqualTo(createOne());
    }

    @Test
    public void shouldNotBeEqualWithDifferentNumbers() {
        assertThat(createOne()).isNotEqualTo(new Phone("04 99 98 97 96"));
    }


}