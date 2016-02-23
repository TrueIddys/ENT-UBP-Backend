package org.ubp.ent.backend.core.domains.wish;

import org.ubp.ent.backend.core.domains.ModelTransformable;
import org.ubp.ent.backend.core.domains.course.CourseDomain;
import org.ubp.ent.backend.core.domains.teacher.TeacherDomain;
import org.ubp.ent.backend.core.model.wish.Wish;

import javax.persistence.*;

/**
 * Created by Anthony on 17/02/2016.
 */
@Entity
@Table(name = "wish")
@AssociationOverrides({
                              @AssociationOverride(name = "primaryKey.course", joinColumns = @JoinColumn(name = "COURSE_ID")),
                              @AssociationOverride(name = "primaryKey.teacher", joinColumns = @JoinColumn(name = "TEACHER_ID"))
                      })
public class WishDomain implements ModelTransformable<Wish> {

    @EmbeddedId
    private WishDomainId primaryKey = new WishDomainId();

    @SuppressWarnings("unused")
    protected WishDomain() {
    }

    public WishDomain(Wish model) {
        if (model == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " with a null " + Wish.class.getName());
        }

        CourseDomain course = new CourseDomain(model.getCourse());
        TeacherDomain teacher = new TeacherDomain(model.getTeacher());

        this.primaryKey = new WishDomainId(course, teacher);
    }


    public WishDomainId getId() {
        return primaryKey;
    }

    @Transient
    public CourseDomain getCourse() {
        return primaryKey.getCourse();
    }

    @Transient
    public TeacherDomain getTeacher() {
        return primaryKey.getTeacher();
    }

    @Override
    public Wish toModel() {
        return new Wish(getCourse().toModel(), getTeacher().toModel());
    }
}
