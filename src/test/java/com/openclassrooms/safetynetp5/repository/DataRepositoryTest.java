package com.openclassrooms.safetynetp5.repository;

import com.openclassrooms.safetynetp5.model.Data;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DataRepositoryTest {

    private String FILEPATH = "src/test/java/com/openclassrooms/safetynetp5/DataTest.json";

    @Test
    public void testRead() {
        DataRepository dataRepository = new DataRepositoryImpl();
        dataRepository.loadData(FILEPATH);
        Data data = dataRepository.getData();

        assertNotNull(data);
        assertEquals(data.getPersons().size(), 3);

    }
}
