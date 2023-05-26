package com.openclassrooms.safetynetp5.repository;

import com.openclassrooms.safetynetp5.DataMock;
import com.openclassrooms.safetynetp5.model.Data;
import com.openclassrooms.safetynetp5.model.Person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonRepositoryTest {

    @Mock
    private DataRepository dataRepository;

    private PersonRepository personRepository;

    @BeforeEach
    public void setUpBeforeTest() throws ParseException {
        Data data = DataMock.getDataMock();
        when(dataRepository.getData()).thenReturn(data);
        personRepository = new PersonRepositoryImpl(dataRepository);
    }

    @Test
    public void testGetAllPerson() {
        List<Person> listPersons = personRepository.getAll();

        assertNotNull(listPersons);
        assertEquals(listPersons.get(1).getFirstName(), "firstNameTest2");
    }

    @Test
    public void testCreatePerson() {
        Person person = new Person("firstNameTest", "lastNameTest", "addressTest", "cityTest", "zipTest", "phoneTest", "emailTest");

        personRepository.save(person);
        List<Person> listPersons= personRepository.getAll();

        assertEquals(4, listPersons.size());
        assertEquals(listPersons.get(3).getFirstName(), "firstNameTest");
    }
    @Test
    public void testDeletePerson() {
        personRepository.delete("firstNameTest", "lastNameTest");

        List<Person> listPersonAfterDelete = personRepository.getAll();

        assertEquals(2, listPersonAfterDelete.size());
    }
    @Test
    public void testUpdatePerson() {
        Person person = new Person("firstNameTest", "lastNameTest", "address2", "cityTest", "zipTest", "phoneTest", "emailTest");

        personRepository.update(person, "firstNameTest", "lastNameTest");

        List<Person> listPersons = personRepository.getAll();

        assertEquals("address2", listPersons.get(0).getAddress());
    }
}
