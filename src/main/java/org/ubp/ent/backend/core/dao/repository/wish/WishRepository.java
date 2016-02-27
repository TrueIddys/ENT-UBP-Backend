package org.ubp.ent.backend.core.dao.repository.wish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.ubp.ent.backend.core.domains.wish.WishDomain;
import org.ubp.ent.backend.core.domains.wish.WishDomainId;
import org.ubp.ent.backend.core.model.wish.WishState;

import java.util.List;

/**
 * Created by Anthony on 23/02/2016.
 */
public interface WishRepository extends JpaRepository<WishDomain, WishDomainId> {

    @Query(value = "SELECT w FROM WishDomain w WHERE w.primaryKey.course.id = :courseId AND w.primaryKey.teacher.id = :teacherId")
    WishDomain findByCourseIdAndTeacherId(@Param("courseId") Long courseId, @Param("teacherId") Long teacherId);

    @Query(value = "SELECT w FROM WishDomain w WHERE w.primaryKey.course.id = :courseId")
    List<WishDomain> findByCourseId(@Param("courseId") Long courseId);

    @Query(value = "SELECT w FROM WishDomain w WHERE w.primaryKey.teacher.id = :teacherId")
    List<WishDomain> findByTeacherId(@Param("teacherId") Long teacherId);

    @Query(value = "SELECT w FROM WishDomain w WHERE w.primaryKey.course.id = :courseId AND w.state = '" + WishState.Constants.ACCEPTED_VALUE + "'")
    WishDomain findAcceptedWishForCourse(@Param("courseId") Long courseId);
}
