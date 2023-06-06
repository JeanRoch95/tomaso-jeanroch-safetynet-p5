package com.openclassrooms.safetynetp5.service;

import com.openclassrooms.safetynetp5.model.MedicalRecord;
import com.openclassrooms.safetynetp5.repository.MedicalRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordServiceImpl(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordRepository.getAll();
    }

    @Override
    public MedicalRecord getMedicalRecord(String firstName, String lastName) {
        return medicalRecordRepository.getMedicalRecord(firstName, lastName);
    }

    @Override
    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }

    @Override
    public List<MedicalRecord> deleteMedicalRecord(String firstName, String lastName) {
        return medicalRecordRepository.delete(firstName, lastName);
    }

    @Override
    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord, String firstName, String lastName) {
        return medicalRecordRepository.update(medicalRecord, firstName, lastName);
    }
}
