package com.openclassrooms.safetynetp5.repository;

import com.openclassrooms.safetynetp5.model.Firestation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FirestationRepository {

    List<Firestation> getAll();

    List<String> findAddressByStation(String station);

    Firestation save(Firestation firestation);

    List<Firestation> delete(String address, String station);

    Firestation update(Firestation firestation, String address, String station);
}
