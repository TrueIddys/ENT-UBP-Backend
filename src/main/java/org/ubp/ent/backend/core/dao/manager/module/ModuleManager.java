package org.ubp.ent.backend.core.dao.manager.module;

import org.springframework.stereotype.Service;
import org.ubp.ent.backend.core.dao.repository.course.CourseRepository;
import org.ubp.ent.backend.core.dao.repository.module.ModuleRepository;
import org.ubp.ent.backend.core.domains.course.CourseDomain;
import org.ubp.ent.backend.core.domains.module.ModuleDomain;
import org.ubp.ent.backend.core.exceptions.database.AlreadyDefinedInOnNonPersistedEntity;
import org.ubp.ent.backend.core.exceptions.database.ModelConstraintViolationException;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.ModuleResourceNotFoundException;
import org.ubp.ent.backend.core.model.course.Course;
import org.ubp.ent.backend.core.model.module.Module;
import org.ubp.ent.backend.core.model.type.ClassroomType;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Maxime on 28/02/2016.
 */

@Service
public class ModuleManager {

    @Inject
    private ModuleRepository moduleRepository;

    @Inject
    private CourseRepository courseRepository;

    public Module create(Module module) {
        if (module == null) {
            throw new IllegalArgumentException("Cannot persist a null " + Module.class.getName());
        }
        if (module.getId() != null) {
            throw new AlreadyDefinedInOnNonPersistedEntity("Cannot persist a " + Module.class.getName() + " which already has an ID.");
        }

        ModuleDomain domain = new ModuleDomain(module);
        domain = moduleRepository.saveAndFlush(domain);
        module.setId(domain.getId());

        return domain.toModel();
    }

    public Module findOneById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Cannot find a " + Module.class.getName() + " with a null id.");
        }
        ModuleDomain domain = moduleRepository.findOne(id);

        if (domain == null) {
            throw new ModuleResourceNotFoundException("No " + Module.class.getName() + " found for id :" + id);
        }

        return domain.toModel();
    }

    public List<Module> findAll() {
        List<ModuleDomain> domains = moduleRepository.findAll();

        return domains.stream().map(ModuleDomain::toModel).collect(Collectors.toList());
    }

    public Module findOneByIdJoiningCourses(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Cannot find a " + Module.class.getName() + " with a null id.");
        }
        ModuleDomain domain = moduleRepository.findOneByIdJoiningCourses(id);

        if (domain == null) {
            throw new ModuleResourceNotFoundException("No " + Module.class.getName() + " found for id :" + id);
        }

        Module model = domain.toModel();
        domain.getCourses().forEach(e -> model.addCourse(e.toModel()));

        return model;
    }


    public List<Module> findAllJoiningCourses() {
        List<ModuleDomain> domains = moduleRepository.findJoiningCourses();

        return domains.parallelStream().map(
                domain -> {
                    Module model = domain.toModel();
                    domain.getCourses().forEach(e -> model.addCourse(e.toModel()));

                    return model;
                }
        ).collect(Collectors.toList());
    }

    public Course addCourse(Long moduleId, Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Cannot add a null course to a " + Module.class.getName());
        }
        if (moduleId == null){
            throw new IllegalArgumentException("Cannot add a course to a " + Module.class.getName() + "who has a null id");
        }

        Module module = findOneById(moduleId);

        CourseDomain courseDomain = new CourseDomain(course);
        courseDomain = courseRepository.saveAndFlush(courseDomain);

        ModuleDomain moduleDomain = new ModuleDomain(module);
        moduleDomain.addCourse(courseDomain);
        moduleRepository.saveAndFlush(moduleDomain);
        course.setId(courseDomain.getId());

        return courseDomain.toModel();
    }
}
