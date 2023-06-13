package com.openclassrooms.safetynetp5.controller;

import com.openclassrooms.safetynetp5.dto.*;
import com.openclassrooms.safetynetp5.exceptions.ArgumentNotFoundException;
import com.openclassrooms.safetynetp5.exceptions.PersonCreateException;
import com.openclassrooms.safetynetp5.exceptions.PersonNotFoundException;
import com.openclassrooms.safetynetp5.exceptions.ResourceNotFoundException;
import com.openclassrooms.safetynetp5.model.Person;
import com.openclassrooms.safetynetp5.service.FirestationService;
import com.openclassrooms.safetynetp5.service.PersonService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

        if(personList.isEmpty()) {
            LOGGER.error("No data was found");
            throw new ResourceNotFoundException();
        }
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
        if(removedPersons.isEmpty()) {
            LOGGER.error("Person with name: {} and lastname: {} not found", firstName, lastName);
            throw new PersonNotFoundException("Person not found");
        }
        LOGGER.info("{} {} has been deleted", firstName, lastName);
        return removedPersons;
    }

    @PutMapping()
    public Person update(@RequestBody Person person, @RequestParam("firstname")String firstName,@RequestParam("lastname")String lastName) {
        Person personUpdated = personService.updatePerson(person, firstName, lastName);

        if(personUpdated == null) {
            LOGGER.error("Person with name: {} and lastname: {} not found", firstName, lastName);
            throw new PersonNotFoundException("Person not found");
        }
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
    public PhoneInfoDTO getPhoneInfo(@RequestParam("firestation") String station) throws Exception {

        PhoneInfoDTO listPhoneInfo = personService.getListPhoneInfo(station);
        LOGGER.info("List has been fetched");

        return listPhoneInfo;


    }

    @GetMapping(value = "/communityEmail")
    public CommunityEmailDTO getCommunityEmail(@RequestParam("city") String city) throws Exception {
        return personService.getCommunityEmail(city);
    }

    @GetMapping(value = "/fire")
    public List<InfoPersonFireDTO> getInfoPersonFire(@RequestParam("address") String address) {
        List<InfoPersonFireDTO> infoPersonFireDTOList = firestationService.getFireListPerson(address);

        if(!infoPersonFireDTOList.isEmpty()) {
            LOGGER.info("List info person with address : {} has been fetched", address);
            return infoPersonFireDTOList;
        } else {
            LOGGER.error("Address not found");
            throw new ArgumentNotFoundException();
        }
    }

    @GetMapping(value = "/flood")
    public List<FloodHomeDTO> getFloodListHome(@RequestParam("station") List<String> station) throws MissingRequestValueException {

        if(station.isEmpty()){
            LOGGER.error("Param NULL");
            throw new MissingRequestValueException("Param cannot be empty");
        }
        return personService.getListFloodHome(station);

    }

    @GetMapping(value = "/personInfo")
    public List<FullInfoPersonDTO> getListFullInfoPerson(@RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName) {
        List<FullInfoPersonDTO> fullInfoPersonDTO = personService.getFullPersonInfo(firstName, lastName);

        if(fullInfoPersonDTO.isEmpty()){
            LOGGER.error("Person with firstname {} and lastname {} not found.", firstName, lastName);
            throw new PersonNotFoundException(firstName + " " + lastName);
        }
        LOGGER.info("{} {} has been fetching", firstName, lastName);
        return fullInfoPersonDTO;
    }

    @GetMapping(value = "/firestation")
    public List<FireStationCoveredDTO> getFireStationCoverage(@RequestParam("stationNumber") String station) throws MissingRequestValueException {

        if(station.isEmpty()){
            LOGGER.error("Param NULL");
            throw new MissingRequestValueException("Param cannot be empty");
        }
        return firestationService.getPersonCoveredByFirestation(station);
    }
}
