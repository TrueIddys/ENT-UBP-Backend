package org.ubp.ent.backend.core.dao.manager.wish;

import org.springframework.stereotype.Service;
import org.ubp.ent.backend.core.dao.manager.course.CourseManager;
import org.ubp.ent.backend.core.dao.manager.teacher.TeacherManager;
import org.ubp.ent.backend.core.dao.repository.wish.WishRepository;
import org.ubp.ent.backend.core.domains.wish.WishDomain;
import org.ubp.ent.backend.core.exceptions.database.CourseAlreadyAssignedToAnotherWish;
import org.ubp.ent.backend.core.exceptions.database.ModelConstraintViolationException;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.WishResourceNotFoundException;
import org.ubp.ent.backend.core.model.course.Course;
import org.ubp.ent.backend.core.model.teacher.Teacher;
import org.ubp.ent.backend.core.model.wish.Wish;
import org.ubp.ent.backend.core.model.wish.WishState;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Anthony on 23/02/2016.
 */
@Service
public class WishManager {

    @Inject
    private WishRepository wishRepository;

    @Inject
    private CourseManager courseManager;

    @Inject
    private TeacherManager teacherManager;

    public List<Wish> findAll() {
        return wishRepository.findAll().stream().map(WishDomain::toModel).collect(Collectors.toList());
    }

    public List<Wish> findAllForCourse(Long courseId) {
        if (courseId == null) {
            throw new IllegalArgumentException("Cannot find a " + Wish.class.getName() + " for a null " + Teacher.class.getName());
        }

        Course course = courseManager.findOneById(courseId);

        List<WishDomain> domain = wishRepository.findByCourseId(course.getId());
        return domain.stream().map(WishDomain::toModel).collect(Collectors.toList());
    }

    public List<Wish> findAllForTeacher(Long teacherId) {
        if (teacherId == null) {
            throw new IllegalArgumentException("Cannot find a " + Wish.class.getName() + " for a null " + Teacher.class.getName());
        }

        Teacher teacher = teacherManager.findOneById(teacherId);

        List<WishDomain> domain = wishRepository.findByTeacherId(teacher.getId());
        return domain.stream().map(WishDomain::toModel).collect(Collectors.toList());
    }

    public Wish findOneForCourseAndTeacher(Long courseId, Long teacherId) {
        if (courseId == null) {
            throw new IllegalArgumentException("Cannot find a " + Wish.class.getName() + " for a null " + Course.class.getName());
        }
        if (teacherId == null) {
            throw new IllegalArgumentException("Cannot find a " + Wish.class.getName() + " for a null " + Teacher.class.getName());
        }

        Course course = courseManager.findOneById(courseId);
        Teacher teacher = teacherManager.findOneById(teacherId);

        WishDomain domain = wishRepository.findByCourseIdAndTeacherId(course.getId(), teacher.getId());
        if (domain == null) {
            throw new WishResourceNotFoundException("No " + Wish.class.getName() + " found for teacherId=" + teacherId + " and courseId=" + courseId);
        }

        return domain.toModel();
    }

    public Wish create(Long courseId, Long teacherId) {
        if (courseId == null) {
            throw new IllegalArgumentException("Cannot persist a " + Wish.class.getName() + " for a null " + Course.class.getName());
        }
        if (teacherId == null) {
            throw new IllegalArgumentException("Cannot persist a " + Wish.class.getName() + " for a null " + Teacher.class.getName());
        }

        Course course = courseManager.findOneById(courseId);
        Teacher teacher = teacherManager.findOneById(teacherId);

        if (wishRepository.findAcceptedWishForCourse(course.getId()) != null) {
            throw new CourseAlreadyAssignedToAnotherWish("An other " + Wish.class.getName() + " is already accepted for the course with id '" + courseId + "'");
        }
        Wish model = new Wish(course, teacher, WishState.PENDING);
        if (wishRepository.exists(new WishDomain(model).getId())) {
            throw new ModelConstraintViolationException("A " + Wish.class.getName() + " already exists for this " + Teacher.class.getName() + " and " + Course.class.getName());
        }

        WishDomain domain = new WishDomain(model);
        domain = wishRepository.saveAndFlush(domain);

        return domain.toModel();
    }

    public Wish acceptWish(Long courseId, Long teacherId) {
        List<Wish> allWishForCourse = findAllForCourse(courseId);

        // if one was already accepted, reject operation
        if (allWishForCourse.stream().filter(w -> w.getState().equals(WishState.ACCEPTED)).count() != 0) {
            throw new CourseAlreadyAssignedToAnotherWish("A wish for the course wish id '" + courseId + "' is already accepted");
        }

        // get wish to accept, and remove it from allWishForCourse
        Wish wishToAccept = findOneForCourseAndTeacher(courseId, teacherId);
        allWishForCourse = allWishForCourse.stream().filter(w -> !w.equals(wishToAccept)).collect(Collectors.toList());

        WishDomain wishToAcceptDomain = new WishDomain(wishToAccept);
        wishToAcceptDomain.accept();

        wishToAcceptDomain = wishRepository.save(wishToAcceptDomain);

        allWishForCourse.stream().map(WishDomain::new).forEach(wd -> {
                    wd.deny();
                    wishRepository.save(wd);
                }
        );
        wishRepository.flush();

        return wishToAcceptDomain.toModel();
    }

}
