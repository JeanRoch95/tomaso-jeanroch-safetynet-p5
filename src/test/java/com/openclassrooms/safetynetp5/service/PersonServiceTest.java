package com.openclassrooms.safetynetp5.service;

import com.openclassrooms.safetynetp5.model.Person;
import com.openclassrooms.safetynetp5.repository.PersonRepository;
import org.assertj.core.api.JUnitJupiterSoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;

    private PersonService personService;

    @BeforeEach
    public void setUpBeforeTest() {
        personService = new PersonServiceImpl(personRepository);
    }

    @Test
    public void test_than_read_return_list() {


        List<Person> mockPersons = Arrays.asList(new Person(), new Person());
        when(personRepository.getAll()).thenReturn(mockPersons);

        List<Person> result = personService.getAllPersons();

        assertEquals(mockPersons, result);
        verify(personRepository).getAll();

    }
    @Test
    public void test_than_person_is_save() {
        Person mockPerson = new Person();
        when(personRepository.save(any(Person.class))).thenReturn(mockPerson);

        Person result = personService.createPerson(mockPerson);

        assertEquals(mockPerson, result);
        verify(personRepository).save(mockPerson);

    }
    @Test
    public void test_than_person_is_delete() {

        String firstName = "John";
        String lastName = "Doe";
        List<Person> mockPersons = List.of(new Person());
        when(personRepository.delete(firstName, lastName)).thenReturn(mockPersons);

        List<Person> result = personService.deletePerson(firstName, lastName);

        assertEquals(mockPersons, result);
        verify(personRepository).delete(firstName, lastName);

    }
    @Test
    public void test_than_person_is_updated() {

        String firstName = "John";
        String lastName = "Doe";
        Person mockPerson = new Person();
        when(personRepository.update(any(Person.class), eq(firstName), eq(lastName))).thenReturn(mockPerson);

        Person result = personService.updatePerson(mockPerson, firstName, lastName);

        assertEquals(mockPerson, result);
        verify(personRepository).update(mockPerson, firstName, lastName);
    }
}
