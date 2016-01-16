package org.ubp.ent.backend.utils;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import org.ubp.ent.backend.config.conditional.condition.TestProfileCondition;
import org.ubp.ent.backend.core.dao.manager.teacher.OutsiderTeacherManager;
import org.ubp.ent.backend.core.dao.manager.teacher.UniversityTeacherManager;
import org.ubp.ent.backend.core.dao.manager.teacher.contact.address.AddressTypeManager;
import org.ubp.ent.backend.core.dao.manager.teacher.contact.email.EmailTypeManager;
import org.ubp.ent.backend.core.dao.manager.teacher.contact.phone.PhoneTypeManager;
import org.ubp.ent.backend.core.model.teacher.OutsiderTeacher;
import org.ubp.ent.backend.core.model.teacher.OutsiderTeacherTest;
import org.ubp.ent.backend.core.model.teacher.UniversityTeacher;
import org.ubp.ent.backend.core.model.teacher.UniversityTeacherTest;
import org.ubp.ent.backend.core.model.teacher.contact.address.Address;
import org.ubp.ent.backend.core.model.teacher.contact.address.AddressType;
import org.ubp.ent.backend.core.model.teacher.contact.address.AddressTypeTest;
import org.ubp.ent.backend.core.model.teacher.contact.email.Email;
import org.ubp.ent.backend.core.model.teacher.contact.email.EmailType;
import org.ubp.ent.backend.core.model.teacher.contact.email.EmailTypeTest;
import org.ubp.ent.backend.core.model.teacher.contact.phone.Phone;
import org.ubp.ent.backend.core.model.teacher.contact.phone.PhoneType;
import org.ubp.ent.backend.core.model.teacher.contact.phone.PhoneTypeTest;

import javax.inject.Inject;

/**
 * Created by Anthony on 15/01/2016.
 */
@Component
@Conditional(value = {TestProfileCondition.class})
public class TestScenarioHelper {

    @Inject
    private UniversityTeacherManager universityTeacherM;
    @Inject
    private OutsiderTeacherManager outsiderTeacherM;

    @Inject
    private AddressTypeManager addressTypeM;
    @Inject
    private EmailTypeManager emailTypeM;
    @Inject
    private PhoneTypeManager phoneTypeM;

    /*
     * UniversityTeacher
     */
    public UniversityTeacher createUniversityTeacher() {
        return createUniversityTeacher(UniversityTeacherTest.createOne());
    }
    public UniversityTeacher createUniversityTeacher(UniversityTeacher model) {
        model.getContact().getAddresses().stream()
                .map(Address::getType)
                .forEach(addressTypeM::create);
        model.getContact().getEmails().stream()
                .map(Email::getType)
                .forEach(emailTypeM::create);
        model.getContact().getPhones().stream()
                .map(Phone::getType)
                .forEach(phoneTypeM::create);

        return universityTeacherM.create(model);
    }
    public UniversityTeacher createEmptyUniversityTeacher() {
        return createUniversityTeacher(UniversityTeacherTest.createOneEmpty());
    }

    /*
     * OutsiderTeacher
     */
    public OutsiderTeacher createOutsiderTeacher() {
        return createOutsiderTeacher(OutsiderTeacherTest.createOne());
    }
    public OutsiderTeacher createOutsiderTeacher(OutsiderTeacher model) {
        model.getContact().getAddresses().stream()
                .map(Address::getType)
                .forEach(addressTypeM::create);
        model.getContact().getEmails().stream()
                .map(Email::getType)
                .forEach(emailTypeM::create);
        model.getContact().getPhones().stream()
                .map(Phone::getType)
                .forEach(phoneTypeM::create);

        return outsiderTeacherM.create(model);
    }
    public OutsiderTeacher createEmptyOutsiderTeacher() {
        return createOutsiderTeacher(OutsiderTeacherTest.createOneEmpty());
    }

    /*
     * AddressType
     */
    public AddressType createAddressType() {
        return createAddressType(AddressTypeTest.createOne());
    }
    public AddressType createAddressType(AddressType model) {
        return addressTypeM.create(model);
    }

    /*
     * EmailType
     */
    public EmailType createEmailType() {
        return createEmailType(EmailTypeTest.createOne());
    }
    public EmailType createEmailType(EmailType model) {
        return emailTypeM.create(model);
    }

    /*
     * PhoneType
     */
    public PhoneType createPhoneType() {
        return createPhoneType(PhoneTypeTest.createOne());
    }
    public PhoneType createPhoneType(PhoneType model) {
        return phoneTypeM.create(model);
    }


}
