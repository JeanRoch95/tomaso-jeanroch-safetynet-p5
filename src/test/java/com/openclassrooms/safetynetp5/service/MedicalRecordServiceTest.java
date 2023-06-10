package com.openclassrooms.safetynetp5.service;

import com.openclassrooms.safetynetp5.model.Firestation;
import com.openclassrooms.safetynetp5.model.MedicalRecord;
import com.openclassrooms.safetynetp5.repository.MedicalRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class MedicalRecordServiceTest {

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    private MedicalRecordService medicalRecordService;

    @BeforeEach
    public void setUpBeforeEachTest() {
        medicalRecordService = new MedicalRecordServiceImpl(medicalRecordRepository);
    }

    @Test
    public void testGetAllMedicalRecords() {

        List<MedicalRecord> mockMedicalRecord  = Arrays.asList(new MedicalRecord(), new MedicalRecord());
        when(medicalRecordRepository.getAll()).thenReturn(mockMedicalRecord);

        List<MedicalRecord> result = medicalRecordService.getAllMedicalRecords();

        assertEquals(mockMedicalRecord, result);
        verify(medicalRecordRepository).getAll();
    }

    @Test
    public void testSaveMedicalRecord() {
        MedicalRecord mockMedicalRecord = new MedicalRecord();
        when(medicalRecordRepository.save(any(MedicalRecord.class))).thenReturn(mockMedicalRecord);

        MedicalRecord result = medicalRecordService.createMedicalRecord(mockMedicalRecord);

        assertEquals(mockMedicalRecord, result);
        verify(medicalRecordRepository).save(mockMedicalRecord);
    }

    @Test
    public void testDeleteMedicalRecord() {
        String firstName = "firstNameTest";
        String lastName = "lastNameTest";
        List<MedicalRecord> mockMedicalRecord = List.of(new MedicalRecord());
        when(medicalRecordRepository.delete(firstName, lastName)).thenReturn(mockMedicalRecord);

        List<MedicalRecord> result = medicalRecordService.deleteMedicalRecord(firstName, lastName);

        assertEquals(mockMedicalRecord, result);
        verify(medicalRecordRepository).delete(firstName, lastName);
    }

    @Test
    public void testUpdateMedicalRecord() {
        String firstName = "firstNameTest";
        String lastName = "lastNameTest";
        MedicalRecord mockMedicalRecord = new MedicalRecord();
        when(medicalRecordRepository.update(any(MedicalRecord.class), eq(firstName), eq(lastName))).thenReturn(mockMedicalRecord);

        MedicalRecord result = medicalRecordService.updateMedicalRecord(mockMedicalRecord, firstName, lastName);

        assertEquals(mockMedicalRecord, result);
        verify(medicalRecordRepository).update(mockMedicalRecord, firstName, lastName);
    }


}
