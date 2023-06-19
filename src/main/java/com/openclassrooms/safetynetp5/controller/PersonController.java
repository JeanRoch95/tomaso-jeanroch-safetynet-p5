package com.openclassrooms.safetynetp5.controller;

import com.openclassrooms.safetynetp5.dto.*;
import com.openclassrooms.safetynetp5.exceptions.ArgumentNotFoundException;
import com.openclassrooms.safetynetp5.exceptions.PersonNotFoundException;
import com.openclassrooms.safetynetp5.exceptions.ResourceNotFoundException;
import com.openclassrooms.safetynetp5.model.Person;
import com.openclassrooms.safetynetp5.service.FirestationService;
import com.openclassrooms.safetynetp5.service.PersonService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;

    private final FirestationService firestationService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    @GetMapping()
    public List<Person> getAllPersons() {
        List<Person> personList = personService.getAllPersons();
        return personList;
    }

    @PostMapping()
    public Person createPerson(@RequestBody Person person) {
        Person createdPerson = personService.createPerson(person);

        LOGGER.info("Person " + createdPerson.toString() + " has been created");
        return createdPerson;
    }

    @DeleteMapping()
    public List<Person> delete(@RequestParam("firstname")String firstName,@RequestParam("lastname")String lastName) {
        List<Person> removedPersons = personService.deletePerson(firstName, lastName);

        LOGGER.info("{} {} has been deleted", firstName, lastName);
        return removedPersons;
    }

    @PutMapping()
    public Person update(@RequestBody Person person, @RequestParam("firstname")String firstName,@RequestParam("lastname")String lastName) {
        Person personUpdated = personService.updatePerson(person, firstName, lastName);

        LOGGER.info("{} {} has been updated", firstName, lastName);
        return personUpdated;
    }

    @GetMapping(value = "/childAlert")
    public List<ChildInfoDTO> getChildAlert(@RequestParam("address") String address) {

        List<ChildInfoDTO> listChild = personService.getListChildByAddress(address);
        LOGGER.info("List has been fetched");

        return listChild;
    }

    @GetMapping(value = "/phoneAlert")
    public List<String> getPhoneInfo(@RequestParam("firestation") String station) {

        List<String> listPhoneInfo = personService.getListPhoneInfo(station);
        LOGGER.info("List has been fetched");

        return listPhoneInfo;

    }

    @GetMapping(value = "/communityEmail")
    public ResponseEntity<List<String>> getCommunityEmail(@RequestParam("city") String city) {
        final List<String> emails = personService.getCommunityEmail(city);
        return ResponseEntity.ok(emails);
    }

    @GetMapping(value = "/fire")
    public List<InfoPersonFireDTO> getInfoPersonFire(@RequestParam("address") String address) {
        List<InfoPersonFireDTO> infoPersonFireDTOList = firestationService.getFireListPerson(address);

        LOGGER.info("List info person with address : {} has been fetched", address);
        return infoPersonFireDTOList;
    }

    @GetMapping(value = "/flood")
    public List<FloodHomeDTO> getFloodListHome(@RequestParam("station") List<String> station) throws MissingRequestValueException {
        List<FloodHomeDTO> floodHomeDTOList = personService.getListFloodHome(station);

        LOGGER.info("List flood home has been fetched");
        return floodHomeDTOList;

    }

    @GetMapping(value = "/personInfo")
    public List<FullInfoPersonDTO> getListFullInfoPerson(@RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName) {
        List<FullInfoPersonDTO> fullInfoPersonDTO = personService.getFullPersonInfo(firstName, lastName);

        LOGGER.info("{} {} has been fetching", firstName, lastName);
        LOGGER.debug("Test debug");
        return fullInfoPersonDTO;
    }

    @GetMapping(value = "/firestation")
    public List<FireStationCoveredDTO> getFireStationCoverage(@RequestParam("stationNumber") String station) throws MissingRequestValueException {
        List<FireStationCoveredDTO> fireStationCoveredDTOList = firestationService.getPersonCoveredByFirestation(station);

        LOGGER.info("Firestation has been fetching with station number : {}", station);
        return fireStationCoveredDTOList;
    }
}
