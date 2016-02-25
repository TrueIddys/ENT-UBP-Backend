package org.ubp.ent.backend.core.model.formation;

import org.ubp.ent.backend.core.model.course.Course;
import org.ubp.ent.backend.core.model.student.Student;

import java.util.List;
import java.util.Set;

/**
 * Created by Anthony on 25/02/2016.
 */
public interface FormationComponent {
    Set<Student> getStudents();
    Set<Course> getCourses();
    Boolean isLeaf();
}
