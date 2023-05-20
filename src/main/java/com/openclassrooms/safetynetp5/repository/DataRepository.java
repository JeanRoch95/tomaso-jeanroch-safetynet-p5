package com.openclassrooms.safetynetp5.repository;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.openclassrooms.safetynetp5.model.Data;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.File;

@Component
public interface DataRepository {
    void read();

    Data getData();
}