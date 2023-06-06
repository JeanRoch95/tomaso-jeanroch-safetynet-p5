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


    public PersonServiceImpl(PersonRepository personRepository, FirestationRepository firestationRepository, MedicalRecordRepository medicalRecordRepository) {
        this.personRepository = personRepository;
        this.firestationRepository = firestationRepository;
        this.medicalRecordRepository = medicalRecordRepository;
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
    public InfoPersonFireDTO getFullPerson(Person person) {

        InfoPersonFireDTO infoPersonFireDTO = new InfoPersonFireDTO();

        infoPersonFireDTO.setFirstName(person.getFirstName());
        infoPersonFireDTO.setLastName(person.getLastName());
        infoPersonFireDTO.setPhoneNumber(person.getPhone());

        MedicalRecord medicalRecord = medicalRecordRepository.getMedicalRecord(person.getFirstName(), person.getLastName());

        if(medicalRecord != null) {
            infoPersonFireDTO.setAllergies(medicalRecord.getAllergies());
            infoPersonFireDTO.setMedications(medicalRecord.getMedication());
            int age = CalculateAgeUtil.calculateAge(medicalRecord.getBirthdate());
            infoPersonFireDTO.setAge(String.valueOf(age));
        }
        return infoPersonFireDTO;
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

            List<InfoPersonFireDTO> listFlood = new ArrayList<>();
            for(Person p : personList) {
                InfoPersonFireDTO infoPersonFireDTO = getFullPerson(p);

                listFlood.add(infoPersonFireDTO);
            }
            floodHomeDTO.setFloodListPerson(listFlood);
            result.add(floodHomeDTO);
        }
        return result;
    }
}
