package com.openclassrooms.safetynetp5.service;


import com.openclassrooms.safetynetp5.controller.PersonController;
import com.openclassrooms.safetynetp5.dto.FireStationCoveredDTO;
import com.openclassrooms.safetynetp5.dto.InfoPersonDTO;
import com.openclassrooms.safetynetp5.dto.InfoPersonFireDTO;
import com.openclassrooms.safetynetp5.exceptions.ArgumentNotFoundException;
import com.openclassrooms.safetynetp5.exceptions.FirestationNotFoundException;
import com.openclassrooms.safetynetp5.exceptions.PersonNotFoundException;
import com.openclassrooms.safetynetp5.exceptions.ResourceNotFoundException;
import com.openclassrooms.safetynetp5.model.Firestation;
import com.openclassrooms.safetynetp5.model.Person;
import com.openclassrooms.safetynetp5.repository.FirestationRepository;
import com.openclassrooms.safetynetp5.repository.PersonRepository;
import com.openclassrooms.safetynetp5.util.CalculateAgeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MissingRequestValueException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class FirestationServiceImpl implements FirestationService {

    private final FirestationRepository firestationRepository;

    private final PersonRepository personRepository;

    private final PersonService personService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);



    public FirestationServiceImpl(FirestationRepository firestationRepository, PersonRepository personRepository, @Lazy PersonService personService) {
        this.firestationRepository = firestationRepository;
        this.personRepository = personRepository;
        this.personService = personService;
    }


    @Override
    public List<Firestation> getAllFirestation() {
        List<Firestation> firestationList = firestationRepository.getAll();

        if(firestationList.isEmpty()) {
            LOGGER.error("No data was found");
            throw new ResourceNotFoundException();
        }
        return firestationList;
    }

    @Override
    public Firestation createFirestation(Firestation firestation) {
        return firestationRepository.save(firestation);
    }

    @Override
    public List<Firestation> deleteFirestation(String address, String station) {
        List<Firestation> firestationDeleted = firestationRepository.delete(address, station);

        if(firestationDeleted.isEmpty()) {
            LOGGER.error("Firestation with address: {} and station number: {} not found", address, station);
            throw new FirestationNotFoundException();
        }
        return firestationDeleted;
    }

    @Override
    public Firestation updateFirestation(Firestation firestation, String address, String station) {
        Firestation firestationUpdated = firestationRepository.update(firestation, address, station);

        if(firestationUpdated == null) {
            LOGGER.error("Firestation with address: {} and station number: {} not found", address, station);
            throw new PersonNotFoundException();
        }
        return firestationUpdated;
    }

    @Override
    public List<InfoPersonFireDTO> getFireListPerson(String address) {
        List<Person> personList = personRepository.findPersonByAddress(address);
        List<InfoPersonFireDTO> infoPersonFireDTOS = new ArrayList<>();


        for(Person p : personList) {
            InfoPersonFireDTO infoPersonFireDTO = new InfoPersonFireDTO();

            InfoPersonDTO infoPersonDTO = personService.getInfoPerson(p);

            infoPersonFireDTO.setInfoPerson(infoPersonDTO);
            infoPersonFireDTO.setStation(firestationRepository.findStationByAddress(address));
            infoPersonFireDTO.setPhoneNumber(p.getPhone());

            infoPersonFireDTOS.add(infoPersonFireDTO);
        }

        if(infoPersonFireDTOS.isEmpty()) {
            LOGGER.error("Address not found");
            throw new ArgumentNotFoundException();
        }

        return infoPersonFireDTOS;
    }

    @Override
    public List<String> addressCoveredByStation(String station) {
        return firestationRepository.findAddressByStation(station);
    }

    public List<String> getListAddress(){
        List<Firestation> firestationsList = firestationRepository.getAll();
        List<String> addressList = new ArrayList<>();

        for(Firestation s : firestationsList) {
            addressList.add(s.getAddress());
        }
        return addressList;
    }

    public List<String> getListStationNumber() {
        List<Firestation> firestationList = firestationRepository.getAll();
        List<String> stationNumberList = new ArrayList<>();

        for(Firestation f : firestationList) {
            stationNumberList.add(f.getStation());
        }
        return stationNumberList;
    }

    @Override
    public List<FireStationCoveredDTO> getPersonCoveredByFirestation(String station) {
        List<FireStationCoveredDTO> fireStationCoverage = new ArrayList<>();
        List<Person> personByStation = new ArrayList<>();
        List<String> addressCoveredByStation = addressCoveredByStation(station);

        if(getListStationNumber().contains(station)){
            LOGGER.error("Station: {} do not exist", station);
            throw new ArgumentNotFoundException();
        }

        int countChild = 0;
        int countAdult = 0;

        for (String a : addressCoveredByStation) {
            List<Person> personByAddress = personRepository.findPersonByAddress(a);
            personByStation.addAll(personByAddress);
        }

        for (Person p : personByStation) {
            FireStationCoveredDTO fireStationCoveredDTO = new FireStationCoveredDTO();

            InfoPersonDTO infoPersonDTO = personService.getInfoPerson(p);

            fireStationCoveredDTO.setInfoPerson(infoPersonDTO);

            fireStationCoverage.add(fireStationCoveredDTO);

            if (CalculateAgeUtil.isChild(personService.getAge(p))) {
                countChild++;
            } else
                countAdult++;

            fireStationCoveredDTO.setChildCount(countChild);
            fireStationCoveredDTO.setAdultCount(countAdult);
            fireStationCoveredDTO.setAddress(p.getAddress());
            fireStationCoveredDTO.setPhoneNumber(p.getPhone());
            fireStationCoveredDTO.setFirstName(p.getFirstName());

        }

        if(fireStationCoverage.isEmpty()){
            LOGGER.error("Param NULL");
            throw new ArgumentNotFoundException();
        }

        return fireStationCoverage;

    }
}
