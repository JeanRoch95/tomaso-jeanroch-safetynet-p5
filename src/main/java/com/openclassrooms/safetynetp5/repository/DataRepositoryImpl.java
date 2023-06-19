package com.openclassrooms.safetynetp5.repository;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.openclassrooms.safetynetp5.model.Data;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public class DataRepositoryImpl implements DataRepository {

    private Data data;

    private static final Logger LOGGER = LogManager.getLogger(DataRepositoryImpl.class);

    public void loadData(String FILEPATH) {
        LOGGER.debug("Starting data loading from {}", FILEPATH);
        try {
            ObjectMapper mapper = new ObjectMapper();
            data = mapper.readValue(new File(FILEPATH), Data.class);
            LOGGER.debug("Data successfully loaded");
        } catch (Exception e) {
            LOGGER.error("Fail to read the json file", e);
        }
    }

    public Data getData() {
        return data;
    }
}
