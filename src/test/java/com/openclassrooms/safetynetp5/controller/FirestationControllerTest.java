package com.openclassrooms.safetynetp5.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynetp5.model.Firestation;
import com.openclassrooms.safetynetp5.service.FirestationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@SpringBootTest
@AutoConfigureMockMvc
public class FirestationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FirestationService firestationService;

    private Firestation firestation;

    @BeforeEach
    public void setUpTestBeforeEach() {
        firestation = new Firestation("addressTest", "stationTest");

    }

    @Test
    @DisplayName("Testing endpoint Get")
    public void testGetStation() throws Exception {
        mockMvc.perform(get("/firestation"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Testing endpoint Post ")
    public void testCreateStation() throws Exception {

        when(firestationService.createFirestation(any(Firestation.class))).thenReturn(firestation);

        mockMvc.perform(post("/firestation")
                .content(asJsonString(firestation))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").value("addressTest"))
                .andExpect(jsonPath("$.station").value("stationTest"));

    }

    @Test
    @DisplayName("Testing endpoint delete")
    public void testDeleteFirestation()throws Exception {

        List<Firestation> mockResponse = Arrays.asList(firestation, firestation);

        when(firestationService.deleteFirestation(any(String.class), any(String.class))).thenReturn(mockResponse);

        mockMvc.perform(delete("/firestation?address=" + "addressTest" + "&station=" + "stationTest")
                .content(asJsonString(firestation))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Testing endpoint put")
    public void testUpdateFirestation()throws Exception {

        when(firestationService.updateFirestation(any(Firestation.class), any(String.class), any(String.class))).thenReturn(firestation);

        mockMvc.perform(delete("/firestation?address=" + "addressTest" + "&station=" + "stationTest")
                .content(asJsonString(firestation))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}