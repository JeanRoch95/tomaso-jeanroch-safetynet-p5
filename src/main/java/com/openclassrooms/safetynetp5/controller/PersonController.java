package com.openclassrooms.safetynetp5.controller;

import com.openclassrooms.safetynetp5.model.Person;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    @GetMapping()
    public List<Person> getAllPersons(){
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
}
