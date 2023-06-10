package com.openclassrooms.safetynetp5.service;

import com.openclassrooms.safetynetp5.model.Firestation;
import com.openclassrooms.safetynetp5.model.MedicalRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicalRecordService {

    /**
     * Retrieve a list of all medical records.
     *
     * <p>This method return a list of {@link MedicalRecord}. Each medical record contain firstName, lastName,
     * birthdate, list of medication and list of allergies
     * </p>
     *
     * @return a list of medical record
     */
    List<MedicalRecord> getAllMedicalRecords();

    MedicalRecord getMedicalRecord(String firstName, String lastName);

    /**
     * Create a new medical record
     *
     * <p>This method create a new {@link MedicalRecord}.
     * </p>
     *
     * @param medicalRecord
     *
     * @return a list of {@link MedicalRecord} representing the remaining medical record after creation.
     */
    MedicalRecord createMedicalRecord(MedicalRecord medicalRecord);

    /**
     * Delete medical record, based on firstname and lastname.
     *
     * @param firstName The firstname of the medical record to be deleted.
     * @param lastName The lastname of the medical record to be deleted.
     *
     * @return a list of {@link MedicalRecord} representing the remaining medical record after deletion.
     */
    List<MedicalRecord> deleteMedicalRecord(String firstName, String lastName);

    /**
     * Updates an existing medical record with new information provided by a medical record object.
     *
     * @param medicalRecord The medical record object containing updated information.
     * @param firstName The firstname of the medical record to be updated.
     * @param lastName The lastname of the medical record to be updated.
     *
     * @return The updated {@link MedicalRecord} object if the operation is successful
     */
    MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord, String firstName, String lastName );
}
