package com.openclassrooms.safetynetp5.service;

import com.openclassrooms.safetynetp5.controller.MedicalRecordController;
import com.openclassrooms.safetynetp5.exceptions.MedicalRecordNotFoundException;
import com.openclassrooms.safetynetp5.exceptions.ResourceNotFoundException;
import com.openclassrooms.safetynetp5.model.MedicalRecord;
import com.openclassrooms.safetynetp5.repository.MedicalRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalRecordController.class);


    public MedicalRecordServiceImpl(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public List<MedicalRecord> getAllMedicalRecords() {
        List<MedicalRecord> medicalRecordList = medicalRecordRepository.getAll();
        if(medicalRecordList.isEmpty()) {
            LOGGER.error("No data was found");
            throw new ResourceNotFoundException();
        }
        return medicalRecordList;
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
        List<MedicalRecord> medicalRecordDeleted = medicalRecordRepository.delete(firstName, lastName);

        if(medicalRecordDeleted.isEmpty()) {
            LOGGER.error("Medical record with name: {} and lastname: {} not found", firstName, lastName);
            throw new MedicalRecordNotFoundException();
        }
        return medicalRecordDeleted;
    }

    @Override
    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord, String firstName, String lastName) {
        MedicalRecord medicalRecordUpdated = medicalRecordRepository.update(medicalRecord, firstName, lastName);

        if(medicalRecordUpdated == null) {
            LOGGER.error("Medical record with name: {} and lastname: {} not found", firstName, lastName);
            throw new MedicalRecordNotFoundException();
        }
        return medicalRecordUpdated;
    }
}
