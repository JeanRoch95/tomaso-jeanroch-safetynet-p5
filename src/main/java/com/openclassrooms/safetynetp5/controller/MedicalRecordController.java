package com.openclassrooms.safetynetp5.controller;

import com.openclassrooms.safetynetp5.exceptions.MedicalRecordCreateException;
import com.openclassrooms.safetynetp5.exceptions.MedicalRecordNotFoundException;
import com.openclassrooms.safetynetp5.exceptions.PersonNotFoundException;
import com.openclassrooms.safetynetp5.exceptions.ResourceNotFoundException;
import com.openclassrooms.safetynetp5.model.MedicalRecord;
import com.openclassrooms.safetynetp5.model.Person;
import com.openclassrooms.safetynetp5.service.MedicalRecordService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medicalRecord")
@AllArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalRecordController.class);

    @GetMapping()
    public List<MedicalRecord> getAllPersons() {
        List<MedicalRecord> medicalRecordList = medicalRecordService.getAllMedicalRecords();

        if(medicalRecordList.isEmpty()) {
            LOGGER.error("No data was found");
            throw new ResourceNotFoundException();
        }
        return medicalRecordList;
    }

    @PostMapping()
    public MedicalRecord createMedicalRecord(@RequestBody Optional<MedicalRecord> medicalRecord) {
        if(medicalRecord.isEmpty()){
            LOGGER.error("Error: No data provided for creating Medical Record");
            throw new IllegalArgumentException("Medical record data cannot be null");
        }

        MedicalRecord createMedicalRecord = medicalRecordService.createMedicalRecord(medicalRecord.get());

        if(createMedicalRecord == null){
            throw new MedicalRecordCreateException("Error creating medical record");
        }

        LOGGER.info("Medical record " + medicalRecord.toString() + " has been created");
        return createMedicalRecord;
    }

    @DeleteMapping()
    public List<MedicalRecord> delete(@RequestParam("firstname")String firstName, @RequestParam("lastname")String lastName) {
        List<MedicalRecord> removedMedicalRecord = medicalRecordService.deleteMedicalRecord(firstName, lastName);

        if(removedMedicalRecord.isEmpty()) {
            LOGGER.error("Medical record with name: {} and lastname: {} not found", firstName, lastName);
            throw new MedicalRecordNotFoundException("Person not found");
        }
        LOGGER.info("{} {} has been deleted", firstName, lastName);
        return removedMedicalRecord;
    }

    @PutMapping()
    public MedicalRecord update(@RequestBody MedicalRecord medicalRecord, @RequestParam("firstname")String firstName,@RequestParam("lastname")String lastName) {
        MedicalRecord updateMedicalRecord = medicalRecordService.updateMedicalRecord(medicalRecord, firstName, lastName);

        if(updateMedicalRecord == null) {
            LOGGER.error("Medical record with name: {} and lastname: {} not found", firstName, lastName);
            throw new MedicalRecordNotFoundException("Medical Record not found");
        }
        LOGGER.info("{} {} has been updated", firstName, lastName);
        return updateMedicalRecord;
    }
}
