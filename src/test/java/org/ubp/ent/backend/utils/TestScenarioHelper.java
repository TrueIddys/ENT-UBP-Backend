package org.ubp.ent.backend.utils;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import org.ubp.ent.backend.config.conditional.condition.TestProfileCondition;
import org.ubp.ent.backend.core.dao.manager.teacher.OutsiderTeacherManager;
import org.ubp.ent.backend.core.dao.manager.teacher.UniversityTeacherManager;
import org.ubp.ent.backend.core.dao.manager.teacher.contact.address.AddressTypeManager;
import org.ubp.ent.backend.core.model.teacher.OutsiderTeacher;
import org.ubp.ent.backend.core.model.teacher.OutsiderTeacherTest;
import org.ubp.ent.backend.core.model.teacher.UniversityTeacher;
import org.ubp.ent.backend.core.model.teacher.UniversityTeacherTest;
import org.ubp.ent.backend.core.model.teacher.contact.address.Address;
import org.ubp.ent.backend.core.model.teacher.contact.address.AddressType;
import org.ubp.ent.backend.core.model.teacher.contact.address.AddressTypeTest;

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

}
