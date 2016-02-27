package org.ubp.ent.backend.core.dao.repository.classroom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.ubp.ent.backend.core.domains.classroom.ClassroomDomain;

import java.util.List;

/**
 * Created by Anthony on 23/11/2015.
 */
public interface ClassroomRepository extends JpaRepository<ClassroomDomain, Long> {


    @Query(value = "SELECT c FROM ClassroomDomain c JOIN FETCH c.equipments")
    List<ClassroomDomain> findJoiningEquipments();

    @Query(value = "SELECT c FROM ClassroomDomain c JOIN FETCH c.equipments WHERE c.id = :id")
    ClassroomDomain findOneByIdJoiningEquipments(@Param("id") Long id);

}
