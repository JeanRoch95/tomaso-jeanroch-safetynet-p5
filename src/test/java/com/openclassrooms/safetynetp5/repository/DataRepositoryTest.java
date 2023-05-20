package com.openclassrooms.safetynetp5.repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynetp5.repository.DataRepositoryImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynetp5.model.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
public class DataRepositoryTest {

    @Autowired
    private DataRepository dataRepository;
    @Mock
    private ObjectMapper mapper;

    @Test
    public void testRead() throws Exception {

        Data data;
        dataRepository = new DataRepositoryImpl(mapper);

        data = mapper.readValue(new File("src/main/resources/data.json"), Data.class);

        assertEquals(data, dataRepository.getData());
    }
}
