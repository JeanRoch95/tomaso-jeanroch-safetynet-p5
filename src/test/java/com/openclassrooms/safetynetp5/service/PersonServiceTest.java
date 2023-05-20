package com.openclassrooms.safetynetp5.service;

import com.openclassrooms.safetynetp5.model.Person;
import com.openclassrooms.safetynetp5.repository.PersonRepository;
import com.openclassrooms.safetynetp5.repository.PersonRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;
    @InjectMocks
    private PersonServiceImpl personService;

    @BeforeEach
    public void setUpBeforeEach() {
        Person p = new Person("firstNameTest", "lastNameTest", "addressTest", "cityTest", "zipTest", "phoneTest", "emailTest");
    }
    @Test
    public void test_than_read_return_list()throws Exception {
        List<Person> testPerson = new ArrayList<>();
        Person p = new Person("firstNameTest", "lastNameTest", "addressTest", "cityTest", "zipTest", "phoneTest", "emailTest");

        testPerson.add(p);

        when(personRepository.getAllPersons()).thenReturn(testPerson);
        personService.read();

        assertEquals(testPerson.get(0).getFirstName(), "firstNameTest");
        assertEquals(testPerson.get(0).getLastName(), "lastNameTest");
        assertEquals(testPerson.get(0).getAddress(), "addressTest");
        assertEquals(testPerson.get(0).getCity(), "cityTest");
        assertEquals(testPerson.get(0).getZip(), "zipTest");
        assertEquals(testPerson.get(0).getPhone(), "phoneTest");
        assertEquals(testPerson.get(0).getEmail(), "emailTest");

    }
    @Test
    public void test_than_person_is_save()throws Exception {
        Person p = new Person("firstNameTest", "lastNameTest", "addressTest", "cityTest", "zipTest", "phoneTest", "emailTest");

        personService.save(p);

        verify(personRepository, times(1)).createPerson(p);

    }
    @Test
    public void test_than_person_is_delete()throws Exception {
        List<Person> testPerson = new ArrayList<>();
        Person p = new Person("firstNameTest", "lastNameTest", "addressTest", "cityTest", "zipTest", "phoneTest", "emailTest");

        testPerson.add(p);

        when(personRepository.deletePerson("firstNameTest", "lastNameTest")).thenReturn(testPerson);
        List<Person> actualList = personService.delete("firstNameTest", "lastNameTest");

        assertEquals(testPerson.size(), actualList.size());
        assertEquals(testPerson.get(0).getFirstName(), actualList.get(0).getFirstName());
        assertEquals(testPerson.get(0).getLastName(), actualList.get(0).getLastName());

    }
    @Test
    public void test_than_person_is_updated()throws Exception {

        Person p = new Person("firstNameTest", "lastNameTest", "addressTest", "cityTest", "zipTest", "phoneTest", "emailTest");

        when(personRepository.updatePerson(p, "firstNameTest", "lastNameTest")).thenReturn(p);

        Person actualPerson = personService.updatePerson(p, "firstNameTest", "lastNameTest");

        assertEquals(p.getFirstName(), actualPerson.getFirstName());
        assertEquals(p.getLastName(), actualPerson.getLastName());
    }
}
