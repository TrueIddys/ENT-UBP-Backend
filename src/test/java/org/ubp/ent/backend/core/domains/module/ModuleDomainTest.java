package org.ubp.ent.backend.core.domains.module;

import org.junit.Test;
import org.ubp.ent.backend.core.domains.course.CourseDomain;
import org.ubp.ent.backend.core.domains.course.CourseDomainTest;
import org.ubp.ent.backend.core.model.course.CourseTest;
import org.ubp.ent.backend.core.model.module.Module;
import org.ubp.ent.backend.core.model.module.ModuleTest;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by Maxime on 28/02/2016.
 */
public class ModuleDomainTest {

    public static ModuleDomain createOne(String name, int courseCount) {
        Module module = ModuleTest.createOne(name);
        ModuleDomain domain = new ModuleDomain(module);

        for (int i = 0; i < courseCount; i++) {
            domain.addCourse(CourseDomainTest.createOne("course " + i));
        }
        return domain;
    }

    public static ModuleDomain createOne(int courseCount) {
        return createOne("Default module name", courseCount);
    }


    public static ModuleDomain createOne(String name) {
        return createOne(name, 0);
    }

    public static ModuleDomain createOne() {
        return createOne(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullModel() {
        new ModuleDomain(null);
    }

    @Test
    public void shouldCreateFromModel() {
        Module model = ModuleTest.createOne();
        model.setId(12L);
        ModuleDomain domain = new ModuleDomain(model);

        assertThat(domain.getId()).isEqualTo(model.getId());
        assertThat(domain.getName()).isEqualTo(model.getName());
        // Model to domain should not transform courses
        assertThat(domain.getCourses()).isEmpty();
    }



    @Test
    public void shouldTransformToModel() {
        Module model = ModuleTest.createOne();
        model.setId(12L);
        ModuleDomain domain = new ModuleDomain(model);

        Module newModel = domain.toModel();
        assertThat(newModel.getId()).isEqualTo(model.getId());
        assertThat(newModel.getName()).isEqualTo(model.getName());
        assertThat(newModel.getCourses()).isEmpty();
    }

    @Test
    public void shouldNotPopulateListOfCourseWhenCreatingDomainFromModel() {
        Module model = ModuleTest.createOne();
        model.addCourse(CourseTest.createOne());
        ModuleDomain domain = new ModuleDomain(model);

        assertThat(domain.getCourses()).isEmpty();
    }

    @Test
    public void shouldNotPopulateListOfCourseWhenTransformingDomainFromModel() {
        Module model = ModuleTest.createOne();
        ModuleDomain domain = new ModuleDomain(model);
        domain.getCourses().add(new CourseDomain(CourseTest.createOne()));

        Module domainToModel = domain.toModel();
        assertThat(domainToModel.getCourses()).isEmpty();
    }

    @Test
    public void shouldBeEqualByName() {
        ModuleDomain domain = ModuleDomainTest.createOne("Genie Log");
        ModuleDomain domain2 = ModuleDomainTest.createOne("Genie Log");

        assertThat(domain2).isEqualTo(domain);
    }

    @Test
    public void shouldNotBeEqualWithDifferentName() {
        ModuleDomain domain = ModuleDomainTest.createOne("Genie Log");
        ModuleDomain domain2 = ModuleDomainTest.createOne("Compilation");

        assertThat(domain2).isNotEqualTo(domain);
    }
}
