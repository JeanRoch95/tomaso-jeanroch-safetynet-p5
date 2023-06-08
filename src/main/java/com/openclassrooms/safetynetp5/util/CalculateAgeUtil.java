package com.openclassrooms.safetynetp5.util;

import com.openclassrooms.safetynetp5.model.MedicalRecord;
import com.openclassrooms.safetynetp5.model.Person;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public final class CalculateAgeUtil {

    public static int calculateAge(Date birthDate) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date currentDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int d1 = Integer.parseInt(formatter.format(birthDate));
        int d2 = Integer.parseInt(formatter.format(currentDate));
        return (d2 - d1) / 10000;
    }

    public static boolean isChild(int age) {
        return age <= 18;
    }

}
