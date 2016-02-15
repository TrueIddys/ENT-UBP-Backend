package org.ubp.ent.backend.core.controllers.course;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.ubp.ent.backend.core.dao.manager.course.CourseManager;
import org.ubp.ent.backend.core.model.course.Course;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Maxime on 15/02/2016.
 */

@RestController
@RequestMapping(CourseController.BASE_URL)
public class CourseController {

    public static final String BASE_URL = "/api/course";

    @Inject
    private CourseManager courseManager;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Course> findAll() {
        return courseManager.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Course findOneById(@PathVariable("id") Long id) {
        return courseManager.findOneById(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Course create(@RequestBody Course course) {
        return courseManager.create(course);
    }

}
