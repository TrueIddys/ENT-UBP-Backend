package org.ubp.ent.backend.core.model.formation;

import org.apache.commons.lang3.StringUtils;
import org.ubp.ent.backend.core.exceptions.database.ModelConstraintViolationException;
import org.ubp.ent.backend.core.model.course.Course;
import org.ubp.ent.backend.core.model.student.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Anthony on 25/02/2016.
 */
public class FormationComposite implements FormationComponent {

    private Long id;
    private final String name;
    private final List<FormationComponent> formations;
    private Boolean isLeafContainer;

    public FormationComposite(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a name");
        }
        this.isLeafContainer = Boolean.TRUE;
        this.name = name;
        this.formations = new ArrayList<>();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public Boolean isLeaf() {
        return Boolean.FALSE;
    }

    @Override
    public Set<Student> getStudents() {
        return formations.stream().flatMap(fc -> fc.getStudents().stream()).collect(Collectors.toSet());
    }

    @Override
    public Set<Course> getCourses() {
        return formations.stream().flatMap(fc -> fc.getCourses().stream()).collect(Collectors.toSet());
    }

    public void addFormation(FormationComponent formation) {
        if (formation == null) {
            throw new IllegalArgumentException("Cannot add a null " + FormationComponent.class.getName() + " to a " + getClass().getName());
        }
        // TODO: refactor this messy code
        if (formation.isLeaf()) {
            this.addElement((FormationLeaf) formation);
        } else {
            this.addElement((FormationComposite) formation);
        }

        this.formations.add(formation);
    }

    private void addElement(FormationComposite formation) {
        if (this.formations.isEmpty() || !this.isLeafContainer) {
            this.isLeafContainer = Boolean.FALSE;
        } else {
            throw new ModelConstraintViolationException("A " + getClass().getName() + " can contains only one of types : " + FormationComposite.class.getName() + " and " + FormationLeaf.class.getName());
        }
    }

    private void addElement(FormationLeaf formation) {
        if (this.formations.isEmpty() || this.isLeafContainer) {
            this.isLeafContainer = Boolean.TRUE;
        } else {
            throw new ModelConstraintViolationException("A " + getClass().getName() + " can contains only one of types : " + FormationComposite.class.getName() + " and " + FormationLeaf.class.getName());
        }
    }

}
