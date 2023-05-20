package com.openclassrooms.safetynetp5;

import com.openclassrooms.safetynetp5.model.Person;
import com.openclassrooms.safetynetp5.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class SafetynetP5Application implements CommandLineRunner {
    @Autowired
    DataRepository dataRepository;


    public static void main(String[] args) {
        SpringApplication.run(SafetynetP5Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        dataRepository.read();
    }
}


