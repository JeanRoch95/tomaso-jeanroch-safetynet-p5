package com.openclassrooms.safetynetp5.service;

import com.openclassrooms.safetynetp5.dto.*;
import com.openclassrooms.safetynetp5.model.Firestation;
import com.openclassrooms.safetynetp5.model.Person;
import org.springframework.stereotype.Service;

import java .util.List;
@Service
public interface PersonService {

    /**
     * Retrieve a list of all person.
     *
     * <p>This method return a list of {@link Person}. Each Firestation contain firstname, lastname, address,
     * city, zip, phone and email
     * </p>
     *
     * @return a list of person
     */
    List<Person> getAllPersons();

    /**
     * Create a new person
     *
     * <p>This method create a new {@link Person}.
     * </p>
     *
     * @param person The person object containing information of new person
     *
     * @return a list of {@link Person} representing the remaining person after creation.
     */
    Person createPerson(Person person);

    /**
     * Delete person, based on firstname and lastname.
     *
     * @param firstName The firstname of the person to be deleted.
     * @param lastName The lastname of the person to be deleted.
     *
     * @return a list of {@link Person} representing the remaining person after deletion.
     */
    List<Person> deletePerson(String firstName, String lastName);

    /**
     * Updates an existing person with new information provided by a Person object.
     *
     * @param person The person object containing updated information.
     * @param firstName The firstname of the person to be updated.
     * @param lastName The lastname of the person to be updated.
     *
     * @return The updated {@link Person} object if the operation is successful
     */
    Person updatePerson(Person person, String firstName, String lastName);

    /**
     * Retrieves the emails of all persons in a given city.
     *
     * <p>
     * This method returns a String object which includes a list of email addresses
     * of all the persons living in the specified city.
     * </p>
     *
     * @param city The name of the city for which emails are to be retrieved.
     * This should be a non-null string representing a valid city name.
     *
     * @return A String object containing a list of emails of persons residing in the
     * specified city.
     */
    List<String> getCommunityEmail(String city);

    /**
     * Retrieves a list of phone information for persons covered by the specified fire station.
     *
     * <p>
     * This method returns a list of String objects, each of which includes
     * the phone information of a person served by the fire station identified by the 'station' parameter.
     * </p>
     *
     * @param station The identifier of the fire station for which person's phone information is to be retrieved.
     *
     * @return A list of String objects representing phone information of persons served by
     * the specified fire station.
     */
    List<String> getListPhoneInfo(String station);

    /**
     * Retrieves a list of persons residing at a given address.
     *
     * <p>
     * This method returns a list of {@link Person} objects, each representing a person
     * residing at the specified address.
     * </p>
     *
     * @param address A String representing the address for which resident information is to be retrieved.
     *
     * @return A list of {@link Person} objects, each representing a person residing at
     * the specified address.
     */
    List<Person> findPersonByAddress(String address);

    /**
     * Retrieves a list of children residing at a specific address.
     *
     * <p>
     * This method finds all the children who are living at a specific address, based on the address
     * provided as the parameter. It returns a list of {@link ChildInfoDTO} objects, each of which
     * represents a child living at the specified address along with relevant details such as name, age, and other family members.
     * </p>
     *
     * @param address A String representing the address.
     * This is used to find all children residing at this location.
     *
     * @return A list of {@link ChildInfoDTO} objects, each representing a child living
     * at the specified address.
     */
    List<ChildInfoDTO> getListChildByAddress(String address);

    /**
     * Retrieves detailed information for a given Person.
     *
     * <p>
     * This method receives a {@link Person} object and returns a {@link InfoPersonDTO} object containing
     * detailed information about the person. This can include name,age, and medical record information.
     * This DTO is usefully for build other other EndPoint.
     * </p>
     *
     * @param person A {@link Person} object for which information is to be retrieved.
     *
     * @return An {@link InfoPersonDTO} object containing detailed information about the specified person.
     */
    InfoPersonDTO getInfoPerson(Person person);

    /**
     * Retrieves a list of homes that are covered by the specified fire stations and at risk of flooding.
     *
     * <p>
     * This method receives a list of fire station identifiers and returns a list of {@link FloodHomeDTO}
     * objects. Each {@link FloodHomeDTO} contains information about a home that is covered by one of
     * the specified stations.
     * </p>
     *
     * @param station A List of Strings representing the identifiers of fire stations.
     *
     * @return A List of {@link FloodHomeDTO} objects, each representing a home covered by one of
     * the specified fire stations and at risk of flooding.
     */
    List<FloodHomeDTO> getListFloodHome(List<String> station);

    /**
     * Calculates the age of a given person.
     *
     * <p>
     * This method takes a {@link Person} object as input and calculates the age of the person based on their date of birth.
     * The date of birth is retrieved from the person's medical record.
     * </p>
     *
     * @param person A {@link Person} object representing the individual whose age needs to be calculated.
     *
     * @return An integer representing the age of the person.
     */
    int getAge(Person person);

    /**
     * Retrieves a list of persons matching a specific first and last name.
     *
     * <p>
     * This method takes as input the first and last name of a person, and returns a list of {@link Person} objects,
     * each representing a person that matches the specified first and last name.
     * </p>
     *
     * @param firstName A String representing the first name of the person(s) to be retrieved.
     * @param lastName A String representing the last name of the person(s) to be retrieved.
     *
     * @return A list of {@link Person} objects, each representing a person that matches the specified
     * first and last name.
     */
    List<Person> findPersonByFirstNameAndLastName(String firstName, String lastName);

    /**
     * Retrieves full information for a person identified by a specific first and last name.
     *
     * <p>
     * This method takes as input the first and last name of a person and returns a list of {@link FullInfoPersonDTO}
     * objects. Each object provides information about a person who matches the provided first and last name.
     * This include their contact information, address, age, lastname, email medical record.
     * </p>
     *
     * @param firstName A String representing the first name of the person(s) whose information is to be retrieved.
     * @param lastName A String representing the last name of the person(s) whose information is to be retrieved.
     *
     * @return A list of {@link FullInfoPersonDTO} objects, each representing complete information about a person who matches
     * the specified first and last name. If lastName matches with other person, these will be added on the list.
     *
     */
    List<FullInfoPersonDTO> getFullPersonInfo(String firstName, String lastName);
}

