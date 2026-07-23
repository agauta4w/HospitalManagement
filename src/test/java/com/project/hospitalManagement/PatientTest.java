package com.project.hospitalManagement;

import com.project.hospitalManagement.dto.BloodGroupStats;
import com.project.hospitalManagement.dto.CPatientInfo;
import com.project.hospitalManagement.dto.IPatientInfo;
import com.project.hospitalManagement.entity.Patient;
import com.project.hospitalManagement.repository.PatientRepository;
import com.project.hospitalManagement.service.PatientService;
import com.project.hospitalManagement.type.BloodGroupType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@SpringBootTest
public class PatientTest {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    PatientService patientService;

    @Test
    public void testPatientRepo() {
        // This test will check if the application context loads successfully
        List<Patient> patientList = patientRepository.findAll();

        System.out.println(patientList);
    }

    @Test
    public void testTransactionMethod() {
        // This test will check if the application context loads successfully
       //
        // Patient patient = patientService.getPatientById(1L);

//
//        Patient patient1 = patientRepository.findByName("Pooja V");
//
//        List<Patient> p2 = patientRepository.findByBloodGroup(BloodGroupType.A_Negative);
//        for(Patient patient : p2) {
//            System.out.println(patient);
//        }
//
//        int rowUpdated = patientRepository.updateNameWithID("Bharat", 1L);
//        System.out.println("Rows updated: " + rowUpdated);

        Page<Patient> patients = patientRepository.findAllPatient(PageRequest.of(0, 2));
        List<IPatientInfo> patientList = patientRepository.getAllPatientInfo();

//        for(IPatientInfo patient : patientList){
//        }

//        List<CPatientInfo> cPatientList = patientRepository.getAllCPatientInfo();
//        for (CPatientInfo patient : cPatientList) {
//            System.out.println(patient);
//        }

//        List<BloodGroupStats> bloodGroupStatsList = patientRepository.countPatientsByBloodGroup();
//        for (BloodGroupStats stats : bloodGroupStatsList) {
//            System.out.println(stats);
//        }

        int rowsAffected = patientRepository.updatePatientNameWithID("Sanju Samson", 1L);
        System.out.println("Rows affected: " + rowsAffected);
//        System.out.println("Total pages: " + patients.getTotalPages());
//        System.out.println("Current page: " + patients.getNumber());

    }

}
