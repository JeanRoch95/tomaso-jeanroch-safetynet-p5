package com.openclassrooms.safetynetp5.service;


import com.openclassrooms.safetynetp5.dto.InfoPersonFireDTO;
import com.openclassrooms.safetynetp5.model.Firestation;
import com.openclassrooms.safetynetp5.model.Person;
import com.openclassrooms.safetynetp5.repository.FirestationRepository;
import com.openclassrooms.safetynetp5.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FirestationServiceImpl implements FirestationService {

    private final FirestationRepository firestationRepository;

    private final PersonRepository personRepository;

    private final PersonService personService;


    public FirestationServiceImpl(FirestationRepository firestationRepository, PersonRepository personRepository, PersonService personService) {
        this.firestationRepository = firestationRepository;
        this.personRepository = personRepository;
        this.personService = personService;
    }


    @Override
    public List<Firestation> getAllFirestation() {
        return firestationRepository.getAll();
    }

    @Override
    public Firestation createFirestation(Firestation firestation) {
        return firestationRepository.save(firestation);
    }

    @Override
    public List<Firestation> deleteFirestation(String address, String station) {
        return firestationRepository.delete(address, station);
    }

    @Override
    public Firestation updateFirestation(Firestation firestation, String address, String station) {
        return firestationRepository.update(firestation, address, station);
    }

    @Override
    public List<InfoPersonFireDTO> getFireListPerson(String address) {
        List<InfoPersonFireDTO> infoPersonFireDTOList = new ArrayList<>();

        List<Person> personList = personRepository.findPersonByAddress(address);

        for(Person p : personList) {
            InfoPersonFireDTO infoPersonFireDTO = personService.getFullPerson(p);
            infoPersonFireDTOList.add(infoPersonFireDTO);
        }
        return infoPersonFireDTOList;
    }

    @Override
    public List<String> addressCoveredByStation(String station) {
        return firestationRepository.findAddressByStation(station);
    }
}
