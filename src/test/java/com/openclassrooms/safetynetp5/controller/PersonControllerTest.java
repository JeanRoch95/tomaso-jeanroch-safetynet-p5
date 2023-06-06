package com.openclassrooms.safetynetp5.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynetp5.dto.ChildInfoDTO;
import com.openclassrooms.safetynetp5.dto.FloodHomeDTO;
import com.openclassrooms.safetynetp5.dto.InfoPersonFireDTO;
import com.openclassrooms.safetynetp5.dto.PhoneInfoDTO;
import com.openclassrooms.safetynetp5.model.Person;
import com.openclassrooms.safetynetp5.service.FirestationService;
import com.openclassrooms.safetynetp5.service.PersonService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @MockBean
    private FirestationService firestationService;

    private Person person;

    @BeforeEach
    public void setUpTestEach() {
        person = new Person("firstNameTest", "lastNameTest", "addressTest", "cityTest", "zipTest", "phoneTest", "emailTest");
    }

    @Test
    @DisplayName("Testing person endpoint Get")
    public void testGetPerson()throws Exception {
        mockMvc.perform(get("/person"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Testing endpoint post")
    public void testAddingPerson()throws Exception {

        when(personService.createPerson(any(Person.class))).thenReturn(person);

        mockMvc.perform(post("/person")
                .content(asJsonString(person))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("firstNameTest"))
                .andExpect(jsonPath("$.lastName").value("lastNameTest"));
    }

    @Test
    @DisplayName("Testing endpoint delete")
    public void testDeletingPerson()throws Exception {

        List<Person> mockResponse = Arrays.asList(person, person);

        when(personService.deletePerson(any(String.class), any(String.class))).thenReturn(mockResponse);

        mockMvc.perform(delete("/person?firstname=" + "firstNameTest" + "&lastname=" + "lastNameTest")
                .content(asJsonString(person))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Testing endpoint put")
    public void testUpdatingPerson()throws Exception {

        when(personService.updatePerson(any(Person.class), any(String.class), any(String.class))).thenReturn(person);

        mockMvc.perform(put("/person?firstname=" + "firstNameTest" + "&lastname=" + "lastNameTest")
                .content(asJsonString(person))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testChildAlert()throws Exception {

        ChildInfoDTO childInfoDTO = new ChildInfoDTO("firstNameTest", "lastNameTest", 12);
        List<ChildInfoDTO> listChildInfoMock = new ArrayList<>();
        listChildInfoMock.add(childInfoDTO);

        when(personService.getListChildByAddress(any(String.class))).thenReturn(listChildInfoMock);

        mockMvc.perform(get("/person/childAlert?address=addressTest")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$..firstName").value("firstNameTest"))
                .andExpect(jsonPath("$..lastName").value("lastNameTest"));
    }
    @Test
    public void testPhoneInfoList()throws Exception {
        PhoneInfoDTO phoneInfoDTO = new PhoneInfoDTO("firstNameTest", "lastNameTest", "phoneTest");
        List<PhoneInfoDTO> phoneInfoDTOList = new ArrayList<>();
        phoneInfoDTOList.add(phoneInfoDTO);

        when(personService.getListPhoneInfo(any(String.class))).thenReturn(phoneInfoDTOList);

        mockMvc.perform(get("/person/phoneAlert?firestation=stationTest")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..firstName").value("firstNameTest"))
                .andExpect(jsonPath("$..lastName").value("lastNameTest"))
                .andExpect(jsonPath("$..phoneNumber").value("phoneTest"))
                .andExpect(status().isOk());
    }
    @Test
    public void testGetFirePersonList()throws Exception {
        InfoPersonFireDTO infoPersonFireDTO = new InfoPersonFireDTO();
        infoPersonFireDTO.setFirstName("firstNameTest");
        infoPersonFireDTO.setLastName("lastNameTest");
        infoPersonFireDTO.setAge("10");
        infoPersonFireDTO.setStation("stationTest");

        List<InfoPersonFireDTO> infoPersonFireDTOList = new ArrayList<>();
        infoPersonFireDTOList.add(infoPersonFireDTO);

        when(firestationService.getFireListPerson(any(String.class))).thenReturn(infoPersonFireDTOList);

        mockMvc.perform(get("/person/fire?address=address").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$..firstName").value("firstNameTest"))
                .andExpect(jsonPath("$..lastName").value("lastNameTest"))
                .andExpect(jsonPath("$..age").value("10"));
    }

    @Test
    public void testGetListFloodHome()throws Exception {
        FloodHomeDTO floodHomeDTO = new FloodHomeDTO();
        floodHomeDTO.setAddress("addressTest");

        List<FloodHomeDTO> floodHomeDTOList = new ArrayList<>();
        floodHomeDTOList.add(floodHomeDTO);
        List<String> listStation = new ArrayList<>();
        listStation.add("Test");

        when(personService.getListFloodHome(listStation)).thenReturn(floodHomeDTOList);

        mockMvc.perform(get("/person/flood?station=1")
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
