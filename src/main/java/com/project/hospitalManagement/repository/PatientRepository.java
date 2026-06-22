package com.project.hospitalManagement.repository;

import com.project.hospitalManagement.entity.Patient;
import com.project.hospitalManagement.type.BloodGroupType;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findByName(String name);


    //JPQL

    @Query("SELECT p FROM Patient p WHERE p.bloodGroup = :bloodGroup")
    List<Patient> findByBloodGroup(BloodGroupType bloodGroup);

//    @Query(value = "SELECT * FROM patient_tbl", nativeQuery = true)
//    List<Patient> findAllPatient();

    //Pagination
    @Query(value = "SELECT * FROM patient_tbl", nativeQuery = true)
    Page<Patient> findAllPatient(PageRequest pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Patient p SET p.name = :name WHERE p.id = :id")
    int updateNameWithID(@Param("name") String name, @Param("id") Long id);
}
