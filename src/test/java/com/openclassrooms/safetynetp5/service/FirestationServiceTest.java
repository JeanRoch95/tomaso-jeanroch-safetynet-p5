package com.openclassrooms.safetynetp5.service;

import com.openclassrooms.safetynetp5.dto.FireStationCoveredDTO;
import com.openclassrooms.safetynetp5.dto.InfoPersonDTO;
import com.openclassrooms.safetynetp5.dto.InfoPersonFireDTO;
import com.openclassrooms.safetynetp5.model.Firestation;
import com.openclassrooms.safetynetp5.model.MedicalRecord;
import com.openclassrooms.safetynetp5.model.Person;
import com.openclassrooms.safetynetp5.repository.FirestationRepository;
import com.openclassrooms.safetynetp5.repository.MedicalRecordRepository;
import com.openclassrooms.safetynetp5.repository.PersonRepository;
import com.openclassrooms.safetynetp5.util.CalculateAgeUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
public class FirestationServiceTest {

    @Mock
    private FirestationRepository firestationRepository;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    private FirestationService firestationService;

    private MedicalRecordService medicalRecordService;
    @Mock
    private PersonService personService;

    @BeforeEach
    public void setUpBeforeTest() {
        firestationService = new FirestationServiceImpl(firestationRepository, personRepository, personService);
    }

    @Test
    public void testGetAllFirestation() {

        List<Firestation> mockFirestations = Arrays.asList(new Firestation(), new Firestation());
        when(firestationService.getAllFirestation()).thenReturn(mockFirestations);

        List<Firestation> result = firestationService.getAllFirestation();

        assertEquals(mockFirestations, result);
        verify(firestationRepository).getAll();
    }

    @Test
    public void testSaveFirestation() {
        Firestation mockFirestation = new Firestation();
        when(firestationRepository.save(any(Firestation.class))).thenReturn(mockFirestation);

        Firestation result = firestationService.createFirestation(mockFirestation);

        assertEquals(mockFirestation, result);
        verify(firestationRepository).save(mockFirestation);
    }

    @Test
    public void testDeleteFirestation() {
         String address = "Address";
         String station = "Station";
         List<Firestation> mockFirestation = List.of(new Firestation());
         when(firestationRepository.delete(address, station)).thenReturn(mockFirestation);

         List<Firestation> result = firestationService.deleteFirestation(address, station);

        assertEquals(mockFirestation, result);
        verify(firestationRepository).delete(address, station);
    }

    @Test
    public void testUpdateFirestation() {
        String address = "Address";
        String station = "Station";
        Firestation mockFirestation = new Firestation();
        when(firestationRepository.update(any(Firestation.class), eq(address), eq(station))).thenReturn(mockFirestation);

        Firestation result = firestationService.updateFirestation(mockFirestation, address, station);

        assertEquals(mockFirestation, result);
        verify(firestationRepository).update(mockFirestation, address, station);
    }
    @Test
    public void testGetFireListPerson() {
        List<Person> personList = Arrays.asList(new Person(), new Person());

        when(personRepository.findPersonByAddress(any(String.class))).thenReturn(personList);

        List<InfoPersonFireDTO> list = firestationService.getFireListPerson("addressTest");

        assertEquals(list.size(), 2);
    }
    @Test
    public void testAddressCoveredByStation() {
        List<String> addressList = new ArrayList<>();
        String address = "addressTest";
        String address2 = "addressTest2";
        String station = "stationTest";

        addressList.add(address);
        addressList.add(address2);

        when(firestationRepository.findAddressByStation(any(String.class))).thenReturn(addressList);

        List<String> result = firestationService.addressCoveredByStation(station);

        assertEquals(result.size(), addressList.size());
        assertEquals(result.get(0), "addressTest");
        assertEquals(result.get(1), "addressTest2");
    }

    @Test
    public void testGetPersonCoveredByFirestation() {
        List<String> addressList = new ArrayList<>();
        String address = "addressTest";

        addressList.add(address);

        when(firestationService.addressCoveredByStation(any(String.class))).thenReturn(addressList);

        Person person1 = new Person();
        Person person2 = new Person();
        List<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);

        when(personRepository.findPersonByAddress(any(String.class))).thenReturn(personList);

        assertEquals(firestationService.getPersonCoveredByFirestation("station").size(), 2);
    }

    @Test
    public void testGetListAddress() {
        Firestation firestation1 = new Firestation();
        firestation1.setAddress("AddressTest1");
        Firestation firestation2 = new Firestation();
        firestation2.setAddress("AddressTest2");

        when(firestationRepository.getAll()).thenReturn(Arrays.asList(firestation1, firestation2));

        List<String> addresses = firestationService.getListAddress();

        assertEquals(2, addresses.size());
        assertEquals("AddressTest1", addresses.get(0));
        assertEquals("AddressTest2", addresses.get(1));
    }

    @Test
    public void testGetStationNumber() {
        Firestation firestation1 = new Firestation();
        firestation1.setStation("1");
        Firestation firestation2 = new Firestation();
        firestation2.setStation("2");

        when(firestationRepository.getAll()).thenReturn(Arrays.asList(firestation1, firestation2));

        List<String> stationNumber = firestationService.getListStationNumber();

        assertEquals(2, stationNumber.size());
        assertEquals("1", stationNumber.get(0));
        assertEquals("2", stationNumber.get(1));
    }

}
