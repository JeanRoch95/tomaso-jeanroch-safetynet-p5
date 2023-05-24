package com.openclassrooms.safetynetp5.controller;

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

        if(!firestationList.isEmpty()) {
            LOGGER.info("Return list of firestation");
            return firestationList;
        } else {
            LOGGER.error("Failed to get data");
            return null;
        }
    }
    @PostMapping()
    public Firestation createFirestation(@RequestBody Firestation firestation) {
        Firestation createdFirestation = firestationService.createFirestation(firestation);

        if(createdFirestation != null) {
            LOGGER.info("Firestation with address : {} and station : {} successfully created", createdFirestation.getAddress(), createdFirestation.getStation());
            return createdFirestation;
        } else {
            LOGGER.error("Failed while creating firestation");
            return null;
        }
    }
    @DeleteMapping()
    public List<Firestation> deleteFirestation(@RequestParam("address")String address, @RequestParam("station") String station) {
        List<Firestation> removedFirestation = firestationService.deleteFirestation(address, station);

        if(!removedFirestation.isEmpty()) {
            LOGGER.info("Firestation with address : {} and station : {} successfully deleted", address, station);
            return removedFirestation;
        } else {
            LOGGER.error("Error while deleting firestation with address : {} and station : {}", address, station);
            return null;
        }
    }
    @PutMapping()
    public Firestation updateFirestation(@RequestBody Firestation firestation, @RequestParam("address")String address, @RequestParam("station")String station) {
        Firestation updatedFirestation = firestationService.updateFirestation(firestation, address, station);

        if(updatedFirestation != null) {
            LOGGER.info("Firestation(s) with address : {} and station : {} successfully updated.", address, station);
            return updatedFirestation;
        } else {
            LOGGER.error("Error while updating firestation(s) with address : {} and station {}.", address, station);
            return null;
        }
    }
}
