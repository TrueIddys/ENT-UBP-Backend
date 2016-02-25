package org.ubp.ent.backend.core.model.formation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;
import org.ubp.ent.backend.core.model.course.Course;
import org.ubp.ent.backend.core.model.student.Student;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Anthony on 25/02/2016.
 */
public class FormationLeaf implements FormationComponent {

    private Long id;
    private final String name;
    private final Set<Course> courses;
    private final Set<Student> students;

    @JsonCreator
    public FormationLeaf(@JsonProperty("name") final String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a name");
        }
        this.name = name;
        courses = new HashSet<>();
        students = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public Boolean isLeaf() {
        return Boolean.TRUE;
    }

    @Override
    public Set<Course> getCourses() {
        return this.courses;
    }

    public void addCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Cannot add a null " + Course.class.getName() + " to a " + getClass().getName());
        }
        this.courses.add(course);
    }

    @Override
    public Set<Student> getStudents() {
        return this.students;
    }

    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Cannot add a null " + Course.class.getName() + " to a " + getClass().getName());
        }
        this.students.add(student);
    }

}
