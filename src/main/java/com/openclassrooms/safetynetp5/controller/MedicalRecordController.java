package com.openclassrooms.safetynetp5.controller;

import com.openclassrooms.safetynetp5.exceptions.MedicalRecordNotFoundException;
import com.openclassrooms.safetynetp5.exceptions.ResourceNotFoundException;
import com.openclassrooms.safetynetp5.model.MedicalRecord;
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

        LOGGER.info("Medical records has been fetched");
        return medicalRecordList;
    }

    @PostMapping()
    public MedicalRecord createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        MedicalRecord createMedicalRecord = medicalRecordService.createMedicalRecord(medicalRecord);

        LOGGER.info("Medical record " + medicalRecord.toString() + " has been created");
        return createMedicalRecord;
    }

    @DeleteMapping()
    public List<MedicalRecord> delete(@RequestParam("firstname")String firstName, @RequestParam("lastname")String lastName) {
        List<MedicalRecord> removedMedicalRecord = medicalRecordService.deleteMedicalRecord(firstName, lastName);

        LOGGER.info("{} {} has been deleted", firstName, lastName);
        return removedMedicalRecord;
    }

    @PutMapping()
    public MedicalRecord update(@RequestBody MedicalRecord medicalRecord, @RequestParam("firstname")String firstName,@RequestParam("lastname")String lastName) {
        MedicalRecord updateMedicalRecord = medicalRecordService.updateMedicalRecord(medicalRecord, firstName, lastName);

        LOGGER.info("{} {} has been updated", firstName, lastName);
        return updateMedicalRecord;
    }
}
