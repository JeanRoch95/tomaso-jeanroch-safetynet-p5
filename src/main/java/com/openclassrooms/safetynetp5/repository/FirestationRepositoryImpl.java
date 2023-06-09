package com.openclassrooms.safetynetp5.repository;

import com.openclassrooms.safetynetp5.model.Firestation;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class FirestationRepositoryImpl implements FirestationRepository {

    private final DataRepository dataRepository;

    public FirestationRepositoryImpl(DataRepository dataRepository) { this.dataRepository = dataRepository; }

    @Override
    public List<Firestation> getAll() {
        return dataRepository.getData().getFirestations();
    }

    @Override
    public List<String> findAddressByStation(String station) {
        List<String> listAddress = new ArrayList<>();
        List<Firestation> fireStation = dataRepository.getData().getFirestations();

        for(Firestation s : fireStation){
            if(s.getStation().contentEquals(station)){
                listAddress.add(s.getAddress());
            }
        }
        return listAddress;
    }

    @Override
    public String findStationByAddress(String address) {
        List<Firestation> fireStations = dataRepository.getData().getFirestations();
        for (Firestation f : fireStations) {
            if (f.getAddress().contentEquals(address)) {
                return f.getStation();
            }
        }
        return "null";
    }

    @Override
    public Firestation save(Firestation firestation) {
        dataRepository.getData().getFirestations().add(firestation);
        return firestation;
    }

    @Override
    public List<Firestation> delete(String address, String station) {
        List<Firestation> firestations = dataRepository.getData().getFirestations();
        List<Firestation> removedStation = new ArrayList<>();

        Iterator<Firestation> iterator = firestations.iterator();
        while (iterator.hasNext()) {
            Firestation firestation = iterator.next();
            if (firestation.getAddress().equals(address) && firestation.getStation().equals(station)) {
                removedStation.add(firestation);
                iterator.remove();
            }
        }
        return removedStation;
    }

    @Override
    public Firestation update(Firestation firestation, String address, String station) {
        return dataRepository.getData().getFirestations().stream()
                .filter(f -> f.getStation().equals(station) && f.getAddress().equals(address))
                .findFirst()
                .map(f -> {
                    if (firestation.getAddress() != null) f.setAddress(firestation.getAddress());
                    if (firestation.getStation() != null) f.setStation(firestation.getStation());
                    return f;
                })
                .orElse(null);
    }
}
