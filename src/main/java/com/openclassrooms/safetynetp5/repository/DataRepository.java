package com.openclassrooms.safetynetp5.repository;

import com.openclassrooms.safetynetp5.model.Data;
import org.springframework.stereotype.Component;

@Component
public interface DataRepository {
    void loadData(String FILEPATH);

    Data getData();
}