package com.openclassrooms.safetynetp5;

import com.openclassrooms.safetynetp5.model.Data;
import com.openclassrooms.safetynetp5.model.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataMock {

    public Data getDataMock() {
        Data dataMock = new Data();
        List<Person> mockPersons = new ArrayList<>();

        Person p = new Person("firstNameTest", "lastNameTest", "addressTest", "cityTest", "zipTest", "phoneTest", "emailTest");
        Person p2 = new Person("firstNameTest2", "lastNameTest2", "addressTest2", "cityTest2", "zipTest2", "phoneTest2", "emailTest2");
        Person p3 = new Person("firstNameTest3", "lastNameTest3", "addressTest3", "cityTest3", "zipTest3", "phoneTest3", "emailTest3");

        mockPersons.add(p);
        mockPersons.add(p2);
        mockPersons.add(p3);

        dataMock.setPersons(mockPersons);

        return dataMock;

    }
}
