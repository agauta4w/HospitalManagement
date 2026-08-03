package com.project.hospitalManagement.service;

import com.project.hospitalManagement.entity.Insurance;
import com.project.hospitalManagement.entity.Patient;
import com.project.hospitalManagement.repository.InsuranceRepository;
import com.project.hospitalManagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public void assignInsuranceToPatient(Insurance insurance, Long PatientId){
        Patient patient = patientRepository.findById(PatientId).orElseThrow(() -> new RuntimeException("Patient not found"));
        patient.setInsurance(insurance);
        patientRepository.save(patient);
    }
}
