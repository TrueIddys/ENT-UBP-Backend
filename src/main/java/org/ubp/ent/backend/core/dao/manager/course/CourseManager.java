package org.ubp.ent.backend.core.dao.manager.course;

import org.springframework.stereotype.Service;
import org.ubp.ent.backend.core.dao.repository.course.CourseRepository;
import org.ubp.ent.backend.core.domains.course.CourseDomain;
import org.ubp.ent.backend.core.exceptions.database.AlreadyDefinedInOnNonPersistedEntity;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.CourseResourceNotFoundException;
import org.ubp.ent.backend.core.model.course.Course;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Maxime on 15/02/2016.
 */

@Service
public class CourseManager {

    @Inject
    private CourseRepository courseRepository;

    public Course create(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Cannot persist a null " + Course.class.getName());
        }
        if (course.getId() != null) {
            throw new AlreadyDefinedInOnNonPersistedEntity("Cannot persist a " + Course.class.getName() + " which already has an ID.");
        }

        CourseDomain domain = new CourseDomain(course);
        domain = courseRepository.saveAndFlush(domain);
        course.setId(domain.getId());

        return domain.toModel();
    }

    public Course findOneById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Cannot find a " + Course.class.getName() + " with a null id.");
        }
        CourseDomain domain = courseRepository.findOne(id);

        if (domain == null) {
            throw new CourseResourceNotFoundException("No " + Course.class.getName() + " found for id :" + id);
        }

        return domain.toModel();
    }

    public List<Course> findAll() {
        List<CourseDomain> domains = courseRepository.findAll();

        return domains.stream().map(CourseDomain::toModel).collect(Collectors.toList());
    }
}
