package org.ubp.ent.backend.core.dao.repository.module;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.ubp.ent.backend.core.domains.module.ModuleDomain;

import java.util.List;

/**
 * Created by Maxime on 28/02/2016.
 */
public interface ModuleRepository extends JpaRepository<ModuleDomain, Long> {

    @Query(value = "SELECT m FROM ModuleDomain m JOIN FETCH m.courses")
    List<ModuleDomain> findJoiningCourses();

    @Query(value = "SELECT m FROM ModuleDomain m JOIN FETCH m.courses WHERE m.id = :id")
    ModuleDomain findOneByIdJoiningCourses(@Param("id") Long id);

}
