package com.openclassrooms.safetynetp5.controller;

import com.openclassrooms.safetynetp5.exceptions.FirestationCreateException;
import com.openclassrooms.safetynetp5.exceptions.FirestationNotFoundException;
import com.openclassrooms.safetynetp5.exceptions.PersonNotFoundException;
import com.openclassrooms.safetynetp5.exceptions.ResourceNotFoundException;
import com.openclassrooms.safetynetp5.model.Firestation;
import com.openclassrooms.safetynetp5.service.FirestationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/firestation")
@AllArgsConstructor
public class FirestationController {

    private final FirestationService firestationService;

    private static final Logger LOGGER = LoggerFactory.getLogger(FirestationController.class);
    @GetMapping()
    public List<Firestation> getAllFirestations() {
        List<Firestation> firestationList = firestationService.getAllFirestation();

        if(firestationList.isEmpty()) {
            LOGGER.error("No data was found");
            throw new ResourceNotFoundException();
        }
        return firestationList;
    }
    @PostMapping()
    public Firestation createFirestation(@RequestBody Optional<Firestation> firestation) {
        if(firestation.isEmpty()) {
            LOGGER.error("Error: No data provided for creating firestation");
            throw new IllegalArgumentException("Firestation data cannot be null");
        }
        Firestation createdFirestation = firestationService.createFirestation(firestation.get());

        if(createdFirestation == null) {
            throw new FirestationCreateException("Error creating firestation");
        }

        LOGGER.info("Firestation" + createdFirestation.toString() + " has been created");
        return createdFirestation;
    }
    @DeleteMapping()
    public List<Firestation> deleteFirestation(@RequestParam("address")String address, @RequestParam("station") String station) {
        List<Firestation> removedFirestation = firestationService.deleteFirestation(address, station);

        if(removedFirestation.isEmpty()) {
            LOGGER.error("Firestation with address: {} and station number: {} not found", address, station);
            throw new FirestationNotFoundException("Person not found");
        }
        LOGGER.info("{} {} has been deleted", address, station);
        return removedFirestation;
    }
    @PutMapping()
    public Firestation updateFirestation(@RequestBody Firestation firestation, @RequestParam("address")String address, @RequestParam("station")String station) {
        Firestation updatedFirestation = firestationService.updateFirestation(firestation, address, station);

        if(updatedFirestation == null) {
            LOGGER.error("Firestation with address: {} and station number: {} not found", address, station);
            throw new PersonNotFoundException("Person not found");
        }
        LOGGER.info("{} {} has been updated", address, station);
        return updatedFirestation;
        }
}
