package com.openclassrooms.safetynetp5.util;

import com.openclassrooms.safetynetp5.controller.PersonController;
import com.openclassrooms.safetynetp5.model.MedicalRecord;
import com.openclassrooms.safetynetp5.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public final class CalculateAgeUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);


    public static int calculateAge(Date birthDate) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date currentDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int date1 = Integer.parseInt(formatter.format(birthDate));
        int date2 = Integer.parseInt(formatter.format(currentDate));
        int age = (date2 - date1) / 10000;

        LOGGER.debug("Birthdate: {}, Actual data: {}, Age: {}", birthDate, currentDate, age);
        return age;
    }

    public static boolean isChild(int age) {
        return age <= 18;
    }

}
