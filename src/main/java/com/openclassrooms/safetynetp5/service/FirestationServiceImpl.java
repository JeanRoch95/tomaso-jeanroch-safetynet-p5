package com.openclassrooms.safetynetp5.service;


import com.openclassrooms.safetynetp5.model.Firestation;
import com.openclassrooms.safetynetp5.repository.FirestationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FirestationServiceImpl implements FirestationService {

    private final FirestationRepository firestationRepository;

    public FirestationServiceImpl(FirestationRepository firestationRepository) { this.firestationRepository = firestationRepository; }
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
}
