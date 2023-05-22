package com.openclassrooms.safetynetp5.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynetp5.model.Person;
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

import java.util.Arrays;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;
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
    private Person person;

    String firstNameTest = "firstNameTest";
    String lastNameTest = "lastNameTest";
    String addressTest = "AddressTest";
    String cityTest = "CityTest";
    String zipCodeTest = "zipTest";
    String phoneTest = "phoneTest";
    String emailTest = "emailTest";

    @BeforeEach
    public void setUpTestEach() {
        person = new Person();
        person.setFirstName(firstNameTest);
        person.setLastName(lastNameTest);
        person.setAddress(addressTest);
        person.setCity(cityTest);
        person.setZip(zipCodeTest);
        person.setPhone(phoneTest);
        person.setEmail(emailTest);
    }
    @Test
    @DisplayName("Testing endpoint read")
    public void testGetPerson()throws Exception {
        mockMvc.perform(get("/person"))
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("Testing endpoint add")
    public void testAddingPerson()throws Exception {

        when(personService.createPerson(any(Person.class))).thenReturn(person);

        mockMvc.perform(post("/person")
                .content(asJsonString(person))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(firstNameTest))
                .andExpect(jsonPath("$.lastName").value(lastNameTest));
    }
    @Test
    @DisplayName("Testing endpoint delete")
    public void testDeletingPerson()throws Exception {

        List<Person> mockResponse = Arrays.asList(person, person);

        when(personService.deletePerson(any(String.class), any(String.class))).thenReturn(mockResponse);

        mockMvc.perform(delete("/person?firstname=" + firstNameTest + "&lastname=" + lastNameTest)
                .content(asJsonString(person))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("Testing endpoint put")
    public void testUpdatingPerson()throws Exception {

        when(personService.updatePerson(any(Person.class), any(String.class), any(String.class))).thenReturn(person);

        mockMvc.perform(put("/person?firstname=" + firstNameTest + "&lastname=" + lastNameTest)
                .content(asJsonString(person))
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
