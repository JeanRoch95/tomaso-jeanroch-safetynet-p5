package com.openclassrooms.safetynetp5.service;

import com.openclassrooms.safetynetp5.dto.CommunityEmailDTO;
import com.openclassrooms.safetynetp5.dto.FloodHomeDTO;
import com.openclassrooms.safetynetp5.dto.InfoPersonFireDTO;
import com.openclassrooms.safetynetp5.dto.PhoneInfoDTO;
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
import org.springframework.boot.test.mock.mockito.MockBean;

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

    @Test
    public void test_than_return_email() {
        List<Person> persons = new ArrayList<>();
        Person person1 = new Person("firstname", "lastname", "", "City", "", "", "email1");
        Person person2 = new Person("firstname", "lastname", "", "City", "", "", "email2");
        persons.add(person1);
        persons.add(person2);

        when(personRepository.getAll()).thenReturn(persons);

        List<CommunityEmailDTO> communityEmailTest = personService.getCommunityEmail("City");

        assertEquals(communityEmailTest.size(),2);
    }

    @Test
    public void test_than_return_phone_info() {

        List<Person> personList = new ArrayList<>();
        personList.add(new Person("firstNameTest", "lastNameTest", "Address1", "phoneTest1"));
        personList.add(new Person("firstNameTest2", "lastNameTest2", "Address2", "phoneTest2"));

        List<Firestation> firestationList = new ArrayList<>();
        firestationList.add(new Firestation("Address1", "station1"));
        firestationList.add(new Firestation("Address2", "station2"));

        when(personRepository.getAll()).thenReturn(personList);
        when(firestationRepository.getAll()).thenReturn(firestationList);

        List<PhoneInfoDTO> result = personService.getListPhoneInfo("station1");

        assertEquals(2, personList.size());
        assertEquals(2, firestationList.size());
        assertEquals(1, result.size());
        PhoneInfoDTO phoneInfoDTO = result.get(0);
        assertEquals("firstNameTest", phoneInfoDTO.getFirstName());
    }
    @Test
    public void test_than_return_full_person()throws ParseException {

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


        InfoPersonFireDTO infoPersonFireDTO = personService.getFullPerson(person);

        assertEquals(infoPersonFireDTO.getFirstName(), "firstNameTest");
        assertEquals(infoPersonFireDTO.getLastName(), "lastNameTest");
        assertEquals(infoPersonFireDTO.getPhoneNumber(), "phoneTest");

    }
    @Test
    public void test_than_return_flood_fire_list()throws ParseException {
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


        // WHEN
        List<FloodHomeDTO> listFloodHomeTest = personService.getListFloodHome(listAddressTest);

        // THEN
        assertEquals(listFloodHomeTest.size(), 4);
    }
}
