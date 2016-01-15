package org.ubp.ent.backend.utils;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import org.ubp.ent.backend.config.conditional.condition.TestProfileCondition;
import org.ubp.ent.backend.core.dao.manager.teacher.TeacherManager;
import org.ubp.ent.backend.core.dao.manager.teacher.contact.address.AddressTypeManager;
import org.ubp.ent.backend.core.dao.manager.teacher.contact.email.EmailTypeManager;
import org.ubp.ent.backend.core.dao.manager.teacher.contact.phone.PhoneTypeManager;
import org.ubp.ent.backend.core.model.teacher.Teacher;
import org.ubp.ent.backend.core.model.teacher.TeacherTest;
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
    private TeacherManager teacherM;

    @Inject
    private AddressTypeManager addressTypeM;
    @Inject
    private EmailTypeManager emailTypeM;
    @Inject
    private PhoneTypeManager phoneTypeM;

    public Teacher createTeacher() {
        return createTeacher(TeacherTest.createOne());
    }
    public Teacher createEmptyTeacher() {
        return createTeacher(TeacherTest.createOneEmpty());
    }
    public Teacher createTeacher(Teacher model) {
        model.getContact().getAddresses().stream()
                .map(Address::getType)
                .forEach(addressTypeM::create);
        model.getContact().getEmails().stream()
                .map(Email::getType)
                .forEach(emailTypeM::create);
        model.getContact().getPhones().stream()
                .map(Phone::getType)
                .forEach(phoneTypeM::create);

        return teacherM.create(model);
    }

    public AddressType createAddressType() {
        return createAddressType(AddressTypeTest.createOne());
    }
    public AddressType createAddressType(AddressType model) {
        return addressTypeM.create(model);
    }

    public EmailType createEmailType() {
        return createEmailType(EmailTypeTest.createOne());
    }
    public EmailType createEmailType(EmailType model) {
        return emailTypeM.create(model);
    }

    public PhoneType createPhoneType() {
        return createPhoneType(PhoneTypeTest.createOne());
    }
    public PhoneType createPhoneType(PhoneType model) {
        return phoneTypeM.create(model);
    }


}
