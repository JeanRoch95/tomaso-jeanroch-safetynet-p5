package com.openclassrooms.safetynetp5.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynetp5.model.MedicalRecord;
import com.openclassrooms.safetynetp5.service.MedicalRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MedicalRecordService medicalRecordService;

    @MockBean
    private MedicalRecord medicalRecord;

    String firstNameTest = "firstNameTest";

    String lastNameTest = "lastNameTest";

    String birthdate = "birthdate";

    List<String> medicationsListConst;

    List<String> allergiesListConst;

    @BeforeEach
    public void setUpBeforeTest() {
        medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName(firstNameTest);
        medicalRecord.setLastName(lastNameTest);
        medicalRecord.setBirthdate(birthdate);
        medicalRecord.setMedication(medicationsListConst);
        medicalRecord.setAllergies(allergiesListConst);
    }

    @Test
    @DisplayName("Testing medical record endpoint get")
    public void testGetMedicalRecord()throws Exception {
        mockMvc.perform(get("/medicalRecord"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Testing medical record endpoint post")
    public void testCreateMedicalRecord()throws Exception {
        when(medicalRecordService.createMedicalRecord(any(MedicalRecord.class))).thenReturn(medicalRecord);

        mockMvc.perform(post("/medicalRecord")
                .content(asJsonString(medicalRecord))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(firstNameTest))
                .andExpect(jsonPath("$.lastName").value(lastNameTest));
    }

    @Test
    @DisplayName("Testing medical record endpoint delete")
    public void testDeleteMedicalRecord()throws Exception {

        List<MedicalRecord> medicalRecordList = Arrays.asList(medicalRecord, medicalRecord);

        when(medicalRecordService.deleteMedicalRecord(any(String.class), any(String.class))).thenReturn(medicalRecordList);

        mockMvc.perform(delete("/medicalRecord?firstname=" + firstNameTest + "&lastname=" + lastNameTest)
                .content(asJsonString(medicalRecord))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Testing medical record endpoint update")
    public void testUpdateMedicalRecord()throws Exception {

        when(medicalRecordService.updateMedicalRecord(any(MedicalRecord.class), any(String.class), any(String.class))).thenReturn(medicalRecord);

        mockMvc.perform(put("/medicalRecord?firstname=" + firstNameTest + "&lastname=" + lastNameTest)
                .content(asJsonString(medicalRecord))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(firstNameTest))
                .andExpect(jsonPath("$.lastName").value(lastNameTest));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}