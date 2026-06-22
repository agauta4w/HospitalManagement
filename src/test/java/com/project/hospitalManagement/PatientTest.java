package com.project.hospitalManagement;

import com.project.hospitalManagement.entity.Patient;
import com.project.hospitalManagement.repository.PatientRepository;
import com.project.hospitalManagement.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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


        Patient patient1 = patientRepository.findByName("Pooja V");
        System.out.println(patient1);
    }

}
