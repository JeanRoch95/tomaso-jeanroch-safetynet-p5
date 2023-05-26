package com.openclassrooms.safetynetp5.repository;

import com.openclassrooms.safetynetp5.DataMock;
import com.openclassrooms.safetynetp5.model.Data;
import com.openclassrooms.safetynetp5.model.Firestation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.ParseException;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class FirestationRepositoryTest {

    @Mock
    private DataRepository dataRepository;

    private FirestationRepository firestationRepository;

    @BeforeEach
    public void setUpBeforeTest() throws ParseException {
        Data data = DataMock.getDataMock();
        when(dataRepository.getData()).thenReturn(data);
        firestationRepository = new FirestationRepositoryImpl(dataRepository);
    }

    @Test
    public void testGetAllFirestations() {
        List<Firestation> listFirestation = firestationRepository.getAll();

        assertNotNull(listFirestation);
        assertEquals(listFirestation.get(1).getAddress(), "addressTest2");
    }

    @Test
    public void testCreateFirestation() {
        Firestation firestationTestCreate = new Firestation("addressTestCreate", "stationTestCreate");

        firestationRepository.save(firestationTestCreate);
        List<Firestation> listFirestation = firestationRepository.getAll();

        assertEquals(4, listFirestation.size());
        assertEquals(listFirestation.get(3).getAddress(), "addressTestCreate");
    }

    @Test
    public void testDeleteFirestation() {
        firestationRepository.delete("addressTest", "stationTest");

        List<Firestation> listFirestation = firestationRepository.getAll();

        assertEquals(2, listFirestation.size());
    }

    @Test
    public void testUpdateFirestation() {
        Firestation firestation = new Firestation("addressTest", "stationTestModify");

        firestationRepository.update(firestation, "addressTest", "stationTest");

        List<Firestation> listFirestation = firestationRepository.getAll();

        assertEquals("stationTestModify", listFirestation.get(0).getStation());

    }
}
