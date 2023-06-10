package com.openclassrooms.safetynetp5.service;

import com.openclassrooms.safetynetp5.dto.*;
import com.openclassrooms.safetynetp5.model.Firestation;
import com.openclassrooms.safetynetp5.model.MedicalRecord;
import com.openclassrooms.safetynetp5.model.Person;
import com.openclassrooms.safetynetp5.repository.FirestationRepository;
import com.openclassrooms.safetynetp5.repository.MedicalRecordRepository;
import com.openclassrooms.safetynetp5.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;

    @Mock
    private FirestationRepository firestationRepository;

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    private PersonService personService;

    @Mock
    private FirestationService firestationService;

    @BeforeEach
    public void setUpBeforeTest() {
        personService = new PersonServiceImpl(personRepository, firestationRepository, medicalRecordRepository, firestationService);
    }

    @Test
    public void testGetAllPerson() {

        List<Person> mockPersons = Arrays.asList(new Person(), new Person());
        when(personRepository.getAll()).thenReturn(mockPersons);

        List<Person> result = personService.getAllPersons();

        assertEquals(mockPersons, result);
        verify(personRepository).getAll();

    }
    @Test
    public void testSavePerson() {
        Person mockPerson = new Person();
        when(personRepository.save(any(Person.class))).thenReturn(mockPerson);

        Person result = personService.createPerson(mockPerson);

        assertEquals(mockPerson, result);
        verify(personRepository).save(mockPerson);

    }
    @Test
    public void testDeletePerson() {

        String firstName = "John";
        String lastName = "Doe";
        List<Person> mockPersons = List.of(new Person());
        when(personRepository.delete(firstName, lastName)).thenReturn(mockPersons);

        List<Person> result = personService.deletePerson(firstName, lastName);

        assertEquals(mockPersons, result);
        verify(personRepository).delete(firstName, lastName);

    }
    @Test
    public void testUpdatePerson() {

        String firstName = "John";
        String lastName = "Doe";
        Person mockPerson = new Person();
        when(personRepository.update(any(Person.class), eq(firstName), eq(lastName))).thenReturn(mockPerson);

        Person result = personService.updatePerson(mockPerson, firstName, lastName);

        assertEquals(mockPerson, result);
        verify(personRepository).update(mockPerson, firstName, lastName);
    }

    @Test
    public void testCommunityEmail() {
        List<Person> persons = new ArrayList<>();
        Person person1 = new Person("firstname", "lastname", "", "City", "", "", "email1");
        Person person2 = new Person("firstname", "lastname", "", "City", "", "", "email2");
        persons.add(person1);
        persons.add(person2);

        when(personRepository.getAll()).thenReturn(persons);

        CommunityEmailDTO communityEmailTest = personService.getCommunityEmail("City");

        assertEquals(communityEmailTest.getEmail().size(),2);
    }

    @Test
    public void testGetListPhoneInfo() {

        List<Person> personList = new ArrayList<>();
        personList.add(new Person("firstNameTest", "lastNameTest", "Address1", "phoneTest1"));
        personList.add(new Person("firstNameTest2", "lastNameTest2", "Address2", "phoneTest2"));

        List<Firestation> firestationList = new ArrayList<>();
        firestationList.add(new Firestation("Address1", "station1"));
        firestationList.add(new Firestation("Address2", "station2"));

        when(personRepository.getAll()).thenReturn(personList);
        when(firestationRepository.getAll()).thenReturn(firestationList);

        PhoneInfoDTO result = personService.getListPhoneInfo("station1");
        PhoneInfoDTO result2 = personService.getListPhoneInfo("station2");

        assertEquals(2, personList.size());
        assertEquals(2, firestationList.size());
        assertEquals("phoneTest1", result.getPhoneNumber().get(0));
        assertEquals("phoneTest2", result2.getPhoneNumber().get(0));
    }
    @Test
    public void testGetInfoPerson()throws ParseException {

        Person person = new Person();
        person.setFirstName("firstNameTest");
        person.setLastName("lastNameTest");
        person.setPhone("phoneTest");

        List<String> allergies = new ArrayList<>();
        allergies.add("allergieTest1");
        allergies.add("allergieTest2");

        String stringDate = "01/01/1990";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDay = dateFormat.parse(stringDate);

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setBirthdate(birthDay);
        medicalRecord.setAllergies(allergies);

        when(medicalRecordRepository.getMedicalRecord(any(String.class), any(String.class))).thenReturn(medicalRecord);


        InfoPersonDTO infoPersonDTO = personService.getInfoPerson(person);

        assertEquals(infoPersonDTO.getLastName(), "lastNameTest");

    }
    @Test
    public void testGetListFloodHome()throws ParseException {
        // GIVEN
        List<String> listAddressTest = new ArrayList<>();
        String address1 = "Address1";
        String address2 = "Address2";
        listAddressTest.add(address1);
        listAddressTest.add(address2);
        when(firestationRepository.findAddressByStation(any(String.class))).thenReturn(listAddressTest);

        List<Person> listPersonTest = new ArrayList<>();
        Person person1 = new Person("firstname", "lastname", "", "City", "", "", "email1");
        Person person2 = new Person("firstname", "lastname", "", "City", "", "", "email2");
        listPersonTest.add(person1);
        listPersonTest.add(person2);
        when(personRepository.findPersonByAddress(any(String.class))).thenReturn(listPersonTest);

        String stringDate = "01/01/1990";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDay = dateFormat.parse(stringDate);

        MedicalRecord medicalRecordTest = new MedicalRecord();
        medicalRecordTest.setBirthdate(birthDay);

        when(medicalRecordRepository.getMedicalRecord(any(String.class), any(String.class))).thenReturn(medicalRecordTest);


        List<FloodHomeDTO> listFloodHomeTest = personService.getListFloodHome(listAddressTest);

        assertEquals(listFloodHomeTest.size(), 4);
        FloodHomeDTO floodHomeDTO = listFloodHomeTest.get(0);
        assertEquals(floodHomeDTO.getAddress(), "Address1");
        FloodHomeDTO floodHomeDTO2 = listFloodHomeTest.get(1);
        assertEquals(floodHomeDTO2.getAddress(), "Address2");

    }

    @Test
    public void testGetAge()throws ParseException {
        Person testPerson = new Person();
        testPerson.setFirstName("firstNameTest");
        testPerson.setLastName("lastNameTest");
        MedicalRecord testMedicalRecord = new MedicalRecord();
        String stringDate = "01/01/1990";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDay = dateFormat.parse(stringDate);

        testMedicalRecord.setBirthdate(birthDay);

        when(medicalRecordRepository.getMedicalRecord(anyString(), anyString())).thenReturn(testMedicalRecord);

        int age = personService.getAge(testPerson);

        assertEquals(33, age);

    }

    @Test
    public void testGetListOfChildByAddress()throws ParseException {
        String address = "TestAddress";

        Person testPerson = new Person();
        testPerson.setFirstName("John");
        testPerson.setLastName("Doe");
        testPerson.setAddress(address);

        MedicalRecord testMedicalRecord = new MedicalRecord();

        String stringDate = "01/01/2022";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDay = dateFormat.parse(stringDate);
        testMedicalRecord.setBirthdate(birthDay);
        when(personRepository.findPersonByAddress(anyString())).thenReturn(Arrays.asList(testPerson));
        when(medicalRecordRepository.getMedicalRecord(anyString(), anyString())).thenReturn(testMedicalRecord);

        // When
        List<ChildInfoDTO> result = personService.getListChildByAddress(address);

        // Then
        assertEquals(1, result.size());
        ChildInfoDTO dto = result.get(0);
        assertEquals(testPerson.getFirstName(), dto.getFirstName());
        assertEquals(testPerson.getLastName(), dto.getLastName());
    }

    @Test
    public void testGetFullInfoPerson() {
        String firstName = "firstNameTest";
        String lastName = "lastNameTest";
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);


        when(personRepository.findPersonByFirstNameAndLastName(anyString(), anyString())).thenReturn(Arrays.asList(person));

        List<FullInfoPersonDTO> result = personService.getFullPersonInfo(firstName, lastName);

        assertEquals(1, result.size());
    }

    @Test
    public void testGetPersonByFirstNameAndLastName() {
        String firstName = "firstNameTest";
        String lastName = "lastNameTest";
        Person person = new Person();

        person.setFirstName(firstName);
        person.setLastName(lastName);

        when(personRepository.findPersonByFirstNameAndLastName(any(String.class), any(String.class))).thenReturn(Arrays.asList(person));

        List<Person> result = personService.findPersonByFirstNameAndLastName(firstName, lastName);

        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getFirstName(), "firstNameTest");
    }
}
