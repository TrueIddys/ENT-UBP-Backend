package org.ubp.ent.backend.core.controllers.wish;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.ubp.ent.backend.core.dao.manager.wish.WishManager;
import org.ubp.ent.backend.core.model.wish.Wish;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Anthony on 23/02/2016.
 */
@RestController
@RequestMapping(WishController.BASE_URL)
public class WishController {

    public static final String BASE_URL = "/api/wish";

    @Inject
    private WishManager wishManager;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Wish> findAll() {
        return wishManager.findAll();
    }

    @RequestMapping(value = "/course/{course_id}", method = RequestMethod.GET)
    public List<Wish> findAllForCourse(@PathVariable("course_id") Long courseId) {
        return wishManager.findAllForCourse(courseId);
    }

    @RequestMapping(value = "/teacher/{teacher_id}", method = RequestMethod.GET)
    public List<Wish> findAllForTeacher(@PathVariable("teacher_id") Long teacherId) {
        return wishManager.findAllForTeacher(teacherId);
    }

    @RequestMapping(value = "/course/{course_id}/teacher/{teacher_id}", method = RequestMethod.GET)
    public Wish findOneByCourseIdAndTeacherId(@PathVariable("course_id") Long courseId, @PathVariable("teacher_id") Long teacherId) {
        return wishManager.findOneForCourseAndTeacher(courseId, teacherId);
    }

    @RequestMapping(value = "/course/{course_id}/teacher/{teacher_id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Wish create(@PathVariable("course_id") Long courseId, @PathVariable("teacher_id") Long teacherId) {
        return wishManager.create(courseId, teacherId);
    }

    @RequestMapping(value = "/accept/course/{course_id}/teacher/{teacher_id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Wish acceptWish(@PathVariable("course_id") Long courseId, @PathVariable("teacher_id") Long teacherId) {
        return wishManager.acceptWish(courseId, teacherId);
    }

}
