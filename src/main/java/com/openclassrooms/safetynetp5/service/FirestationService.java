package com.openclassrooms.safetynetp5.service;

import com.openclassrooms.safetynetp5.model.Firestation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FirestationService {

    List<Firestation> getAllFirestation();

    Firestation createFirestation(Firestation firestation);

    List<Firestation> deleteFirestation(String address, String station);

    Firestation updateFirestation(Firestation firestation, String address, String station);

}
