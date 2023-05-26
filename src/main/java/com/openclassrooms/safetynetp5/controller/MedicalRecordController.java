package com.openclassrooms.safetynetp5.controller;

import com.openclassrooms.safetynetp5.model.MedicalRecord;
import com.openclassrooms.safetynetp5.model.Person;
import com.openclassrooms.safetynetp5.service.MedicalRecordService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicalRecord")
@AllArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalRecordController.class);

    @GetMapping()
    public List<MedicalRecord> getAllPersons() {
        List<MedicalRecord> medicalRecordList = medicalRecordService.getAllMedicalRecords();

        if(!medicalRecordList.isEmpty()) {
            LOGGER.info("Return list of medical Record");
            return medicalRecordList;
        } else {
            LOGGER.error("Failed to get data");
            return null;
        }
    }

    @PostMapping()
    public MedicalRecord createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        MedicalRecord createMedicalRecord = medicalRecordService.createMedicalRecord(medicalRecord);

        if(createMedicalRecord != null) {
            LOGGER.info("Medical record with firstname: {} and lastname: {} successfully created ", createMedicalRecord.getFirstName(), createMedicalRecord.getLastName());
            return createMedicalRecord;
        } else {
            LOGGER.error("Failed while creating medical record");
            return null;
        }
    }

    @DeleteMapping()
    public List<MedicalRecord> delete(@RequestParam("firstname")String firstName, @RequestParam("lastname")String lastName) {
        List<MedicalRecord> removedMedicalRecord = medicalRecordService.deleteMedicalRecord(firstName, lastName);

        if(!removedMedicalRecord.isEmpty()){
            LOGGER.info("Medical record with firstname: {} and lastname: {} successfully deleted.", firstName, lastName);
            return removedMedicalRecord;
        } else {
            LOGGER.error("Error while deleting medical record with firstname: {} and lastname: {}", firstName, lastName);
            return null;
        }
    }

    @PutMapping()
    public MedicalRecord update(@RequestBody MedicalRecord medicalRecord, @RequestParam("firstname")String firstName,@RequestParam("lastname")String lastName) {
        MedicalRecord updateMedicalRecord = medicalRecordService.updateMedicalRecord(medicalRecord, firstName, lastName);

        if (updateMedicalRecord != null) {
            LOGGER.info("Medical record with firstname: {} and lastname: {} successfully updated.", firstName, lastName);
            return updateMedicalRecord;
        } else {
            LOGGER.error("Error while updating medical record with firstname: {} and lastname: {}", firstName, lastName);
            return null;
        }
    }
}
