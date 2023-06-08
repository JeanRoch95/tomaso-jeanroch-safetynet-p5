package com.openclassrooms.safetynetp5.service;

import com.openclassrooms.safetynetp5.dto.*;
import com.openclassrooms.safetynetp5.model.Firestation;
import com.openclassrooms.safetynetp5.model.MedicalRecord;
import com.openclassrooms.safetynetp5.model.Person;
import com.openclassrooms.safetynetp5.repository.FirestationRepository;
import com.openclassrooms.safetynetp5.repository.MedicalRecordRepository;
import com.openclassrooms.safetynetp5.repository.PersonRepository;
import com.openclassrooms.safetynetp5.util.CalculateAgeUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    private final FirestationRepository firestationRepository;

    private final MedicalRecordRepository medicalRecordRepository;

    private final FirestationService firestationService;


    public PersonServiceImpl(PersonRepository personRepository, FirestationRepository firestationRepository, MedicalRecordRepository medicalRecordRepository, FirestationService firestationService) {
        this.personRepository = personRepository;
        this.firestationRepository = firestationRepository;
        this.medicalRecordRepository = medicalRecordRepository;
        this.firestationService = firestationService;
    }


    @Override
    public List<Person> getAllPersons() {
        return personRepository.getAll();
    }

    @Override
    public Person createPerson(Person person) {
        return personRepository.save(person);
    }
    @Override
    public List<Person> deletePerson(String firstName, String lastName) {
        return personRepository.delete(firstName, lastName);
    }
    @Override
    public Person updatePerson(Person person, String firstName, String lastName) {
        return personRepository.update(person, firstName, lastName);
    }

    @Override
    public List<CommunityEmailDTO> getCommunityEmail(String city) {
        List<CommunityEmailDTO> result = new ArrayList<>();
        List<Person> persons = personRepository.getAll();

        for (Person p : persons) {
            if (p.getCity().contentEquals(city)) {
                CommunityEmailDTO communityEmail = new CommunityEmailDTO();
                communityEmail.setEmail(p.getEmail());
                result.add(communityEmail);
            }
        }
        return result;
    }

    @Override
    public List<PhoneInfoDTO> getListPhoneInfo(String station) {
        List<PhoneInfoDTO> result = new ArrayList<>();
        List<Person> personList = personRepository.getAll();
        List<Firestation> firestationList = firestationRepository.getAll();

        for(Firestation f : firestationList){
            if(f.getStation().equals(station)){
                for(Person p : personList) {
                    if (f.getAddress().equals(p.getAddress())) {
                        PhoneInfoDTO phoneInfoDTO = new PhoneInfoDTO();
                        phoneInfoDTO.setFirstName(p.getFirstName());
                        phoneInfoDTO.setLastName(p.getLastName());
                        phoneInfoDTO.setPhoneNumber(p.getPhone());
                        result.add(phoneInfoDTO);
                    }
                }
            }
        }
        return result;
    }

    @Override
    public List<Person> findPersonByAddress(String address) {
        return personRepository.findPersonByAddress(address);
    }

    public List<ChildInfoDTO> getListChildByAddress(String address) {

        List<Person> allPersonInAddress = personRepository.findPersonByAddress(address);
        List<ChildInfoDTO> listChild = new ArrayList<>();

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
            infoPersonDTO.setMedications(medicalRecord.getMedication());
            int age = CalculateAgeUtil.calculateAge(medicalRecord.getBirthdate());
            infoPersonDTO.setAge(String.valueOf(age));
        }
        return infoPersonDTO;
    }

    @Override
    public List<FloodHomeDTO> getListFloodHome(List<String> station) {
        List<FloodHomeDTO> result = new ArrayList<>();
        List<String> address = new ArrayList<>();

        for(String s : station) {
            List<String> addressByStation = new ArrayList<>();
            addressByStation = firestationRepository.findAddressByStation(s);
            address.addAll(addressByStation);
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
        return result;
    }

    @Override
    public List<Person> findPersonByFirstNameAndLastName(String firstName, String lastName) {
        return personRepository.findPersonByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<FullInfoPersonDTO> getFullPersonInfo(String firstName, String lastName) {
        List<FullInfoPersonDTO> fullInfoPersonDTOList = new ArrayList<>();
        List<Person> persons = findPersonByFirstNameAndLastName(firstName, lastName);
        List<InfoPersonDTO> infoPersonDTOList = new ArrayList<>();

        for(Person p : persons) {
            FullInfoPersonDTO fullInfoPersonDTO = new FullInfoPersonDTO();
            InfoPersonDTO infoPersonDTO = getInfoPerson(p);
            infoPersonDTOList.add(infoPersonDTO);

            fullInfoPersonDTO.setInfoPerson(infoPersonDTOList);
            fullInfoPersonDTO.setEmail(p.getEmail());
            fullInfoPersonDTO.setAddress(p.getAddress());
            fullInfoPersonDTOList.add(fullInfoPersonDTO);
        }
        return fullInfoPersonDTOList;
    }
}
