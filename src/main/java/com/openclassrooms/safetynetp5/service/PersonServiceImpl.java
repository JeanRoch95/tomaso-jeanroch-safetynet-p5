package com.openclassrooms.safetynetp5.service;

import com.openclassrooms.safetynetp5.controller.PersonController;
import com.openclassrooms.safetynetp5.dto.*;
import com.openclassrooms.safetynetp5.exceptions.ArgumentNotFoundException;
import com.openclassrooms.safetynetp5.exceptions.FirestationNotFoundException;
import com.openclassrooms.safetynetp5.exceptions.PersonNotFoundException;
import com.openclassrooms.safetynetp5.exceptions.ResourceNotFoundException;
import com.openclassrooms.safetynetp5.model.Firestation;
import com.openclassrooms.safetynetp5.model.MedicalRecord;
import com.openclassrooms.safetynetp5.model.Person;
import com.openclassrooms.safetynetp5.repository.FirestationRepository;
import com.openclassrooms.safetynetp5.repository.MedicalRecordRepository;
import com.openclassrooms.safetynetp5.repository.PersonRepository;
import com.openclassrooms.safetynetp5.util.CalculateAgeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MissingRequestValueException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    private final FirestationRepository firestationRepository;

    private final MedicalRecordRepository medicalRecordRepository;

    private final FirestationService firestationService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);


    public PersonServiceImpl(PersonRepository personRepository, FirestationRepository firestationRepository, MedicalRecordRepository medicalRecordRepository, FirestationService firestationService) {
        this.personRepository = personRepository;
        this.firestationRepository = firestationRepository;
        this.medicalRecordRepository = medicalRecordRepository;
        this.firestationService = firestationService;
    }


    @Override
    public List<Person> getAllPersons() {
        List<Person> personList = personRepository.getAll();

        if(personList.isEmpty()) {
            LOGGER.error("No data was found");
            throw new ResourceNotFoundException();
        }
        return personList;
    }

    @Override
    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public List<Person> deletePerson(String firstName, String lastName) {
        List<Person> removedPersons =  personRepository.delete(firstName, lastName);

        if(removedPersons.isEmpty()) {
            LOGGER.error("Person with name: {} and lastname: {} not found", firstName, lastName);
            throw new PersonNotFoundException();
        }
        return removedPersons;
    }

    @Override
    public Person updatePerson(Person person, String firstName, String lastName) {
        Person personUpdated = personRepository.update(person, firstName, lastName);

        if(personUpdated == null) {
            LOGGER.error("Person with name: {} and lastname: {} not found", firstName, lastName);
            throw new PersonNotFoundException();
        }
        return personUpdated;
    }

    @Override
    public List<String> getCommunityEmail(String city) {
        List<Person> persons = personRepository.getAll();
        List<String> emailPerson = new ArrayList<>();

        for (Person p : persons) {
            if (p.getCity().contentEquals(city) || p.getCity().toLowerCase().contentEquals(city.toLowerCase())) {
                emailPerson.add(p.getEmail());
            } else {
                LOGGER.error("City not found");
                throw new PersonNotFoundException();
            }
        }
        return emailPerson;
    }

    @Override
    public List<String> getListPhoneInfo(String station) {
        List<Person> personList = personRepository.getAll();
        List<Firestation> firestationList = firestationRepository.getAll();
        List<String> allPhoneList = new ArrayList<>();

        if(!firestationService.getListStationNumber().contains(station)){
            LOGGER.error("Station not found");
            throw new ArgumentNotFoundException();
        }

        for(Firestation f : firestationList){
            if(f.getStation().equals(station)){
                for(Person p : personList) {
                    if (f.getAddress().equals(p.getAddress())) {
                        allPhoneList.add(p.getPhone());
                    }
                }
            }
        }
        return allPhoneList;
    }

    @Override
    public List<Person> findPersonByAddress(String address) {
        return personRepository.findPersonByAddress(address);
    }

    public List<ChildInfoDTO> getListChildByAddress(String address) {

        List<Person> allPersonInAddress = personRepository.findPersonByAddress(address);
        List<ChildInfoDTO> listChild = new ArrayList<>();

        if(!firestationService.getListAddress().contains(address)){
            LOGGER.error("Address not in the database");
            throw new ArgumentNotFoundException();
        }

        for (Person p : allPersonInAddress) {
            MedicalRecord medicalRecord = medicalRecordRepository.getMedicalRecord(p.getFirstName(), p.getLastName());
            if (medicalRecord != null) {
                int age = CalculateAgeUtil.calculateAge(medicalRecord.getBirthdate());

                if (CalculateAgeUtil.isChild(age)) {
                    ChildInfoDTO childInfo = new ChildInfoDTO();
                    childInfo.setFirstName(p.getFirstName());
                    childInfo.setLastName(p.getLastName());
                    childInfo.setAge(age);
                    childInfo.setMembers(allPersonInAddress);
                    listChild.add(childInfo);
                }
            }
        }
        return listChild;
    }

    @Override
    public InfoPersonDTO getInfoPerson(Person person) {
        InfoPersonDTO infoPersonDTO = new InfoPersonDTO();

        infoPersonDTO.setLastName(person.getLastName());

        MedicalRecord medicalRecord = medicalRecordRepository.getMedicalRecord(person.getFirstName(), person.getLastName());

        if(medicalRecord != null) {
            infoPersonDTO.setAllergies(medicalRecord.getAllergies());
            infoPersonDTO.setMedications(medicalRecord.getMedications());
            int age = CalculateAgeUtil.calculateAge(medicalRecord.getBirthdate());
            infoPersonDTO.setAge(String.valueOf(age));
        }
        return infoPersonDTO;
    }

    @Override
    public List<FloodHomeDTO> getListFloodHome(List<String> station) {
        List<FloodHomeDTO> result = new ArrayList<>();
        List<String> address = new ArrayList<>();
        List<String> listStation = firestationService.getListStationNumber();

        for(String s : station) {
            if(listStation.contains(s)){
                List<String> addressByStation = new ArrayList<>();
                addressByStation = firestationRepository.findAddressByStation(s);
                address.addAll(addressByStation);
            } else {
                LOGGER.error("Station: {} not found", s);
                throw new ArgumentNotFoundException();
            }

        }

        for(String s : address) {
            List<Person> personList = new ArrayList<>();
            personList = findPersonByAddress(s);

            FloodHomeDTO floodHomeDTO = new FloodHomeDTO();
            floodHomeDTO.setAddress(s);

            List<InfoPersonDTO> listFlood = new ArrayList<>();
            for(Person p : personList) {
                InfoPersonDTO infoPersonDTO = getInfoPerson(p);

                listFlood.add(infoPersonDTO);
            }
            floodHomeDTO.setFloodListPerson(listFlood);
            result.add(floodHomeDTO);
        }

        if(result.isEmpty()){
            LOGGER.error("Param NULL");
            throw new ArgumentNotFoundException();
        }
        return result;
    }

    @Override
    public int getAge(Person person) {

        int agePerson = 0;
        MedicalRecord medicalRecord = medicalRecordRepository.getMedicalRecord(person.getFirstName(), person.getLastName());

        if(medicalRecord != null) {
            agePerson = CalculateAgeUtil.calculateAge(medicalRecord.getBirthdate());
        }
        return agePerson;
    }

    @Override
    public List<Person> findPersonByFirstNameAndLastName(String firstName, String lastName) {
        return personRepository.findPersonByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<FullInfoPersonDTO> getFullPersonInfo(String firstName, String lastName) {
        List<FullInfoPersonDTO> fullInfoPersonDTOList = new ArrayList<>();
        List<Person> persons = personRepository.findPersonByFirstNameAndLastName(firstName, lastName);

        if(!persons.isEmpty()) {
            List<Person> personsByLastName = personRepository.findPersonByLastName(lastName);

            List<Person> filteredList = personsByLastName.stream()
                    .filter(person -> !person.getFirstName().equals(firstName))
                    .collect(Collectors.toList());

            persons.addAll(filteredList);
        }


        for(Person p : persons) {
            FullInfoPersonDTO fullInfoPersonDTO = new FullInfoPersonDTO();
            InfoPersonDTO infoPersonDTO = getInfoPerson(p);

            fullInfoPersonDTO.setPersons(infoPersonDTO);
            fullInfoPersonDTO.setEmail(p.getEmail());
            fullInfoPersonDTO.setAddress(p.getAddress());
            fullInfoPersonDTOList.add(fullInfoPersonDTO);
        }

        if(fullInfoPersonDTOList.isEmpty()){
            LOGGER.error("Person with firstname {} and lastname {} not found.", firstName, lastName);
            throw new PersonNotFoundException();
        }

        return fullInfoPersonDTOList;
    }
}
