package com.openclassrooms.safetynetp5.repository;

import com.openclassrooms.safetynetp5.DataMock;
import com.openclassrooms.safetynetp5.model.Data;
import com.openclassrooms.safetynetp5.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class PersonRepositoryTest {
    @Autowired
    private DataMock dataMock;
    @Autowired
    private Data data;
    @Autowired
    private PersonRepository personRepository;
    @MockBean
    private DataRepository dataRepository;

    @BeforeEach()
    public void setUp() throws Exception{
        data = dataMock.getDataMock();
        when(dataRepository.getData()).thenReturn(data);
    }
    @Test
    public void testGetAllPerson()throws Exception {

        List<Person> listPersons = personRepository.getAllPersons();

        assertEquals(3, listPersons.size());
    }

    @Test
    public void testCreatePerson()throws Exception{
        Person person = new Person("firstNameTest", "lastNameTest", "addressTest", "cityTest", "zipTest", "phoneTest", "emailTest");

        personRepository.createPerson(person);
        List<Person> listPersons= personRepository.getAllPersons();

        assertEquals(4, listPersons.size());
    }
    @Test
    public void testDeletePerson()throws Exception {

        personRepository.deletePerson("firstNameTest", "lastNameTest");

        List<Person> listPersonAfterDelete = personRepository.getAllPersons();

        assertEquals(2, listPersonAfterDelete.size());
    }
    @Test
    public void testUpdatePerson()throws Exception {
        Person person = new Person("firstNameTest", "lastNameTest", "address2", "cityTest", "zipTest", "phoneTest", "emailTest");

        personRepository.updatePerson(person, "firstNameTest", "lastNameTest");

        List<Person> listPersons = data.getPersons();
        Person test= listPersons.get(0);
        assertEquals("address2", test.getAddress());


    }
}
