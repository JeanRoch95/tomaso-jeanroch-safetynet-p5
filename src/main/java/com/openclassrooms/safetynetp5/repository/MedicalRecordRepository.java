package com.openclassrooms.safetynetp5.repository;

import com.openclassrooms.safetynetp5.model.MedicalRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MedicalRecordRepository {

    List<MedicalRecord> getAll();

    MedicalRecord save(MedicalRecord medicalRecord);

    List<MedicalRecord> delete(String firstName, String lastName);

    MedicalRecord update(MedicalRecord medicalRecord, String firstName, String lastName);

}
