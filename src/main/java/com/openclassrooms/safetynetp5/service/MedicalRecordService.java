package com.openclassrooms.safetynetp5.service;

import com.openclassrooms.safetynetp5.model.MedicalRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicalRecordService {

    List<MedicalRecord> getAllMedicalRecords();

    MedicalRecord createMedicalRecord(MedicalRecord medicalRecord);

    List<MedicalRecord> deleteMedicalRecord(String firstName, String lastName);

    MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord, String firstName, String lastName );
}
