package com.openclassrooms.safetynetp5.service;

import com.openclassrooms.safetynetp5.model.Firestation;
import com.openclassrooms.safetynetp5.repository.FirestationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
public class FirestationServiceTest {

    @Mock
    private FirestationRepository firestationRepository;

    private FirestationService firestationService;

    @BeforeEach
    public void setUpBeforeTest() {
        firestationService = new FirestationServiceImpl(firestationRepository);
    }

    @Test
    public void test_than_read_return_list() {

        List<Firestation> mockFirestations = Arrays.asList(new Firestation(), new Firestation());
        when(firestationService.getAllFirestation()).thenReturn(mockFirestations);

        List<Firestation> result = firestationService.getAllFirestation();

        assertEquals(mockFirestations, result);
        verify(firestationRepository).getAll();
    }

    @Test
    public void test_than_firestation_is_save() {
        Firestation mockFirestation = new Firestation();
        when(firestationRepository.save(any(Firestation.class))).thenReturn(mockFirestation);

        Firestation result = firestationService.createFirestation(mockFirestation);

        assertEquals(mockFirestation, result);
        verify(firestationRepository).save(mockFirestation);
    }

    @Test
    public void test_than_firestation_is_delete() {
         String address = "Address";
         String station = "Station";
         List<Firestation> mockFirestation = List.of(new Firestation());
         when(firestationRepository.delete(address, station)).thenReturn(mockFirestation);

         List<Firestation> result = firestationService.deleteFirestation(address, station);

        assertEquals(mockFirestation, result);
        verify(firestationRepository).delete(address, station);
    }

    @Test
    public void test_than_firestation_is_updated() {
        String address = "Address";
        String station = "Station";
        Firestation mockFirestation = new Firestation();
        when(firestationRepository.update(any(Firestation.class), eq(address), eq(station))).thenReturn(mockFirestation);

        Firestation result = firestationService.updateFirestation(mockFirestation, address, station);

        assertEquals(mockFirestation, result);
        verify(firestationRepository).update(mockFirestation, address, station);
    }


}
