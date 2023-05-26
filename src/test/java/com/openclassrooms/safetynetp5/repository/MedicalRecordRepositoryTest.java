package com.openclassrooms.safetynetp5.repository;

import com.openclassrooms.safetynetp5.DataMock;
import com.openclassrooms.safetynetp5.model.Data;
import com.openclassrooms.safetynetp5.model.MedicalRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)

public class MedicalRecordRepositoryTest {

    @Mock
    DataRepository dataRepository;

    private MedicalRecordRepository medicalRecordRepository;

    @BeforeEach
    public void setUpBeforeEachTest() {
        Data data = DataMock.getDataMock();
        when(dataRepository.getData()).thenReturn(data);
        medicalRecordRepository = new MedicalRecordRepositoryImpl(dataRepository);
    }

    @Test
    public void testGetAllMedicalRecordTest() {
        List<MedicalRecord> medicalRecordList = medicalRecordRepository.getAll();

        assertNotNull(medicalRecordList);
        assertEquals(medicalRecordList.get(0).getFirstName(), "firstNameTest");
    }

    @Test
    public void testCreateMedicalRecord() {
        List<String> mockAllergies = new ArrayList<>();
        List<String> mockMedications = new ArrayList<>();
        mockAllergies.add("allergiesTest");
        mockMedications.add("medicationTest");

        MedicalRecord medicalRecordTestCreate = new MedicalRecord("firstNameTest", "lastNameTest", "06091990", mockMedications, mockAllergies);


        medicalRecordRepository.save(medicalRecordTestCreate);
        List<MedicalRecord> listMedicalRecord = medicalRecordRepository.getAll();

        assertEquals(4, listMedicalRecord.size());
        assertEquals(listMedicalRecord.get(3).getBirthdate(), "06091990");
    }

    @Test
    public void testDeleteMedicalRecord() {
        medicalRecordRepository.delete("firstNameTest", "lastNameTest");

        List<MedicalRecord> medicalRecordList = medicalRecordRepository.getAll();

        assertEquals(medicalRecordList.size(), 2);
    }

    @Test
    public void testUpdateMedicalRecord() {
        List<String> mockAllergies = new ArrayList<>();
        List<String> mockMedications = new ArrayList<>();
        mockAllergies.add("allergiesTest");
        mockMedications.add("medicationTest");

        MedicalRecord medicalRecordTestUpdate = new MedicalRecord("firstNameTest", "lastNameTest", "06091990", mockMedications, mockAllergies);

        medicalRecordRepository.update(medicalRecordTestUpdate, "firstNameTest", "lastNameTest");

        List<MedicalRecord> medicalRecordList = medicalRecordRepository.getAll();

        assertEquals("06091990", medicalRecordList.get(0).getBirthdate());
    }
}
