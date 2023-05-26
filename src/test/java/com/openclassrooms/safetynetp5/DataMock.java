package com.openclassrooms.safetynetp5;

import com.openclassrooms.safetynetp5.model.Data;
import com.openclassrooms.safetynetp5.model.Firestation;
import com.openclassrooms.safetynetp5.model.MedicalRecord;
import com.openclassrooms.safetynetp5.model.Person;

import java.util.ArrayList;
import java.util.List;

public class DataMock {

    public static Data getDataMock() {
        Data dataMock = new Data();
        List<Person> mockPersons = new ArrayList<>();
        List<Firestation> mockFirestation = new ArrayList<>();
        List<MedicalRecord> mockMedicalRecord = new ArrayList<>();

        List<String> mockAllergies = new ArrayList<>();
        List<String> mockMedications = new ArrayList<>();

        Person p = new Person("firstNameTest", "lastNameTest", "addressTest", "cityTest", "zipTest", "phoneTest", "emailTest");
        Person p2 = new Person("firstNameTest2", "lastNameTest2", "addressTest2", "cityTest2", "zipTest2", "phoneTest2", "emailTest2");
        Person p3 = new Person("firstNameTest3", "lastNameTest3", "addressTest3", "cityTest3", "zipTest3", "phoneTest3", "emailTest3");

        mockPersons.add(p);
        mockPersons.add(p2);
        mockPersons.add(p3);

        dataMock.setPersons(mockPersons);

        Firestation f = new Firestation("addressTest", "stationTest");
        Firestation f2 = new Firestation("addressTest2", "stationTest2");
        Firestation f3 = new Firestation("addressTest3", "stationTest3");

        mockFirestation.add(f);
        mockFirestation.add(f2);
        mockFirestation.add(f3);

        dataMock.setFirestations(mockFirestation);

        mockAllergies.add("allergieTest1");
        mockAllergies.add("allergieTest2");
        mockMedications.add("medicationTest1");
        mockMedications.add("medicationTest2");

        MedicalRecord m = new MedicalRecord("firstNameTest", "lastNameTest", "06091995", mockMedications, mockAllergies);
        MedicalRecord m2 = new MedicalRecord("firstNameTest2", "lastNameTest2", "06091995", mockMedications, mockAllergies);
        MedicalRecord m3 = new MedicalRecord("firstNameTest3", "lastNameTest3", "06091995", mockMedications, mockAllergies);

        mockMedicalRecord.add(m);
        mockMedicalRecord.add(m2);
        mockMedicalRecord.add(m3);

        dataMock.setMedicalRecord(mockMedicalRecord);

        return dataMock;
    }
}
