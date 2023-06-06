package com.openclassrooms.safetynetp5.controller;

import com.openclassrooms.safetynetp5.dto.*;
import com.openclassrooms.safetynetp5.model.Person;
import com.openclassrooms.safetynetp5.service.FirestationService;
import com.openclassrooms.safetynetp5.service.PersonService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

        if(!personList.isEmpty()){
            LOGGER.info("Return List of persons");
            return personList;
        } else {
            LOGGER.error("Failed to get data");
            return null;
        }
    }
    @PostMapping()
    public Person createPerson(@RequestBody Person person) {
        Person createdPerson = personService.createPerson(person);

        if(createdPerson != null) {
            LOGGER.info("Person with firstname: {} and lastname: {} successfully created ", createdPerson.getFirstName(), createdPerson.getLastName());
            return createdPerson;
        } else {
            LOGGER.error("Failed while creating person");
            return null;
        }
    }
    @DeleteMapping()
    public List<Person> delete(@RequestParam("firstname")String firstName,@RequestParam("lastname")String lastName) {
        List<Person> removedPersons = personService.deletePerson(firstName, lastName);

       if(!removedPersons.isEmpty()){
            LOGGER.info("Person with firstname: {} and lastname: {} successfully deleted.", firstName, lastName);
            return removedPersons;
        } else {
            LOGGER.error("Error while deleting person with firstname: {} and lastname: {}", firstName, lastName);
            return null;
        }
    }
    @PutMapping()
    public Person update(@RequestBody Person person, @RequestParam("firstname")String firstName,@RequestParam("lastname")String lastName) {
        Person personUpdated = personService.updatePerson(person, firstName, lastName);

        if(personUpdated != null){
            LOGGER.info("Person(s) with firstname: {} and lastname: {} successfully updated.", firstName, lastName);
            return personUpdated;
        } else {
            LOGGER.error("Error while updating person(s) with firstname: {} and lastname: {}", firstName, lastName);
            return null;
        }
    }

    @GetMapping(value = "/childAlert")
    public List<ChildInfoDTO> getChildAlert(@RequestParam("address") String address) {
        return personService.getListChildByAddress(address);

        /*if(listChild != null){
            LOGGER.info("List of child with address : {} has been fetching", address);
            return listChild;
        } else {
            LOGGER.error("Error while fetching list with address : {}", address);
            return null;
        }*/
    }
    @GetMapping(value = "/phoneAlert")
    public List<PhoneInfoDTO> getPhoneInfo(@RequestParam("firestation") String station) throws Exception {
        List<PhoneInfoDTO> listPhoneInfo = personService.getListPhoneInfo(station);

        if(listPhoneInfo != null){
            LOGGER.info("List of phoneInfo with number station : {} has been fetching", station);
            return listPhoneInfo;
        } else {
            LOGGER.error("Error while fetching listPhoneInfo with station : {}", station);
            return null;
        }

    }


    @GetMapping(value = "/communityEmail")
    public List<CommunityEmailDTO> getCommunityEmail(@RequestParam("city") String city) throws Exception {

        if(city.isEmpty()) {
            LOGGER.error("getCommunityEmail : city is empty");
            throw new Exception("city value is empty");
        }
        LOGGER.info("get email success");
        return personService.getCommunityEmail(city);

    }
    @GetMapping(value = "/fire")
    public List<InfoPersonFireDTO> getInfoPersonFire(@RequestParam("address") String address)throws Exception {
        List<InfoPersonFireDTO> infoPersonFireDTOList = firestationService.getFireListPerson(address);

        if(!infoPersonFireDTOList.isEmpty()) {
            LOGGER.info("List info person with address : {} has been feetching", address);
            return infoPersonFireDTOList;
        } else {
            LOGGER.error("List info person fetch with address : {} has failed", address);
            return null;
        }
    }

    @GetMapping(value = "/flood")
    public List<FloodHomeDTO> getFloodListHome(@RequestParam("station") List<String> station)throws Exception {
        List<FloodHomeDTO> floodHomeDTOList = personService.getListFloodHome(station);

        if(floodHomeDTOList != null) {
            LOGGER.info("List of flood home with station : {} has been fetching", station);
            return floodHomeDTOList;
        } else {
            LOGGER.error("Fetch of list person flood with station : {} has failed", station);
            return null;
        }


    }

}
