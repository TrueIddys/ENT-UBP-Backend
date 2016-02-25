package org.ubp.ent.backend.core.domains.student;

import org.ubp.ent.backend.core.domains.ModelTransformable;
import org.ubp.ent.backend.core.model.student.Student;

/**
 * FIXME: THIS IS A MOCKED CLASS, awaiting for correct implementation
 */
public class StudentDomain implements ModelTransformable<Student> {

    @Override
    public Student toModel() {
        return new Student();
    }

}
