package com.openclassrooms.safetynetp5.repository;

import com.openclassrooms.safetynetp5.DataMock;
import com.openclassrooms.safetynetp5.model.Data;
import com.openclassrooms.safetynetp5.model.MedicalRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public void setUpBeforeEachTest() throws ParseException {
        Data data = DataMock.getDataMock();
        when(dataRepository.getData()).thenReturn(data);
        medicalRecordRepository = new MedicalRecordRepositoryImpl(dataRepository);
    }

    @Test
    public void testGetAllMedicalRecords() {
        List<MedicalRecord> medicalRecordList = medicalRecordRepository.getAll();

        assertNotNull(medicalRecordList);
        assertEquals(medicalRecordList.get(0).getFirstName(), "firstNameTest");
    }

    @Test
    public void testGetMedicalRecord() {
        MedicalRecord medicalRecordList = medicalRecordRepository.getMedicalRecord("firstNameTest", "lastNameTest");

        assertEquals(medicalRecordList.getFirstName(), "firstNameTest");
        assertEquals(medicalRecordList.getAllergies(), dataRepository.getData().getMedicalRecords().get(0).getAllergies());
    }

    @Test
    public void testCreateMedicalRecord() throws ParseException {
        List<String> mockAllergies = new ArrayList<>();
        List<String> mockMedications = new ArrayList<>();
        mockAllergies.add("allergiesTest");
        mockMedications.add("medicationTest");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDay = dateFormat.parse("01/01/2021");

        MedicalRecord medicalRecordTestCreate = new MedicalRecord("firstNameTest", "lastNameTest", birthDay , mockMedications, mockAllergies);


        medicalRecordRepository.save(medicalRecordTestCreate);
        List<MedicalRecord> listMedicalRecord = medicalRecordRepository.getAll();

        assertEquals(4, listMedicalRecord.size());
        assertEquals(listMedicalRecord.get(3).getBirthdate(), birthDay);
    }

    @Test
    public void testDeleteMedicalRecord() {
        medicalRecordRepository.delete("firstNameTest", "lastNameTest");

        List<MedicalRecord> medicalRecordList = medicalRecordRepository.getAll();

        assertEquals(medicalRecordList.size(), 2);
    }

    @Test
    public void testUpdateMedicalRecord() throws ParseException {
        List<String> mockAllergies = new ArrayList<>();
        List<String> mockMedications = new ArrayList<>();
        mockAllergies.add("allergiesTest");
        mockMedications.add("medicationTest");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDay = dateFormat.parse("01/01/2021");

        MedicalRecord medicalRecordTestUpdate = new MedicalRecord("firstNameTest", "lastNameTest", birthDay, mockMedications, mockAllergies);

        medicalRecordRepository.update(medicalRecordTestUpdate, "firstNameTest", "lastNameTest");

        List<MedicalRecord> medicalRecordList = medicalRecordRepository.getAll();

        assertEquals(birthDay, medicalRecordList.get(0).getBirthdate());
    }
}
