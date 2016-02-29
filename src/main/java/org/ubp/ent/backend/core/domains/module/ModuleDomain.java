package org.ubp.ent.backend.core.domains.module;

import com.google.common.base.Objects;
import org.ubp.ent.backend.core.domains.ModelTransformable;
import org.ubp.ent.backend.core.domains.course.CourseDomain;
import org.ubp.ent.backend.core.model.module.Module;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxime on 28/02/2016.
 */
@Entity
@Table(name = "module")
public class ModuleDomain implements ModelTransformable<Module> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MODULE_ID")
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "MODULE_COURSE", joinColumns = @JoinColumn(name = "MODULE_ID"), inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
    private List<CourseDomain> courses = new ArrayList<>();

    @SuppressWarnings("unused")
    protected ModuleDomain() {
    }

    public ModuleDomain(Module model) {
        if (model == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " with a null " + Module.class.getName());
        }
        id = model.getId();
        name = model.getName();
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

    public List<CourseDomain> getCourses() {
        return courses;
    }

    public void addCourse(CourseDomain course) {
        if (course == null) {
            throw new IllegalArgumentException("Cannot add a null " + CourseDomain.class.getName() + " to a " + getClass().getName());
        }
        this.courses.add(course);
    }

    @Override
    public Module toModel() {
        Module module = new Module(name);
        module.setId(id);

        return module;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModuleDomain that = (ModuleDomain) o;
        return Objects.equal(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
