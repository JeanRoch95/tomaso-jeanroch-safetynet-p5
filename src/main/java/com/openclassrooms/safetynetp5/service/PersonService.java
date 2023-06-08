package com.openclassrooms.safetynetp5.service;

import com.openclassrooms.safetynetp5.dto.*;
import com.openclassrooms.safetynetp5.model.Person;
import org.springframework.stereotype.Service;

import java .util.List;
@Service
public interface PersonService {

    List<Person> getAllPersons();

    Person createPerson(Person person);

    List<Person> deletePerson(String firstName, String lastName);

    Person updatePerson(Person person, String firstName, String lastName);

    List<CommunityEmailDTO> getCommunityEmail(String city);

    List<PhoneInfoDTO> getListPhoneInfo(String station);

    List<Person> findPersonByAddress(String address);

    List<ChildInfoDTO> getListChildByAddress(String address);

    InfoPersonDTO getInfoPerson(Person person);

    List<FloodHomeDTO> getListFloodHome(List<String> station);

    List<Person> findPersonByFirstNameAndLastName(String firstName, String lastName);

    List<FullInfoPersonDTO> getFullPersonInfo(String firstName, String lastName);


}

