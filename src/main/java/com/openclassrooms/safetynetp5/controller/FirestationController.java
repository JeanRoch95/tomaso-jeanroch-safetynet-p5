package com.openclassrooms.safetynetp5.controller;

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

@RestController
@RequestMapping("/firestation")
@AllArgsConstructor
public class FirestationController {

    private final FirestationService firestationService;

    private static final Logger LOGGER = LoggerFactory.getLogger(FirestationController.class);
    @GetMapping()
    public List<Firestation> getAllFirestations() {
        List<Firestation> firestationList = firestationService.getAllFirestation();

        LOGGER.info("Firestations has been fetch");
        return firestationList;
    }
    @PostMapping()
    public Firestation createFirestation(@RequestBody Firestation firestation) {
        Firestation createdFirestation = firestationService.createFirestation(firestation);

        LOGGER.info("Firestation" + createdFirestation.toString() + " has been created");
        return createdFirestation;
    }
    @DeleteMapping()
    public List<Firestation> deleteFirestation(@RequestParam("address")String address, @RequestParam("station") String station) {
        List<Firestation> removedFirestation = firestationService.deleteFirestation(address, station);

        LOGGER.info("{} {} has been deleted", address, station);
        return removedFirestation;
    }
    @PutMapping()
    public Firestation updateFirestation(@RequestBody Firestation firestation, @RequestParam("address")String address, @RequestParam("station")String station) {
        Firestation updatedFirestation = firestationService.updateFirestation(firestation, address, station);

        LOGGER.info("{} {} has been updated", address, station);
        return updatedFirestation;
        }
}
