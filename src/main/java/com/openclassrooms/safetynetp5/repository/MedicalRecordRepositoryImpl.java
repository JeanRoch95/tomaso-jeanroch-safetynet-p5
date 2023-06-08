package com.openclassrooms.safetynetp5.repository;


import com.openclassrooms.safetynetp5.model.Firestation;
import com.openclassrooms.safetynetp5.model.MedicalRecord;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Repository
public class MedicalRecordRepositoryImpl implements MedicalRecordRepository {

    private final DataRepository dataRepository;


    public MedicalRecordRepositoryImpl(DataRepository dataRepository) { this.dataRepository = dataRepository; }

    @Override
    public List<MedicalRecord> getAll() {
        return dataRepository.getData().getMedicalRecords();
    }

    @Override
    public MedicalRecord getMedicalRecord(String firstName, String lastName) {

        List<MedicalRecord> medicalRecords = dataRepository.getData().getMedicalRecords();

        for (MedicalRecord m : medicalRecords) {
            if (m.getFirstName().contentEquals(firstName) && m.getLastName().contentEquals(lastName)) {
                return m;
            }
        }
        return null;
    }

    @Override
    public MedicalRecord save(MedicalRecord medicalRecord) {
        dataRepository.getData().getMedicalRecords().add(medicalRecord);
        return medicalRecord;
    }

    @Override
    public List<MedicalRecord> delete(String firstName, String lastName) {
        List<MedicalRecord> medicalRecords = dataRepository.getData().getMedicalRecords();
        List<MedicalRecord> removedMedicalRecord = new ArrayList<>();

        Iterator<MedicalRecord> iterator = medicalRecords.iterator();
        while (iterator.hasNext()) {
            MedicalRecord medicalRecord = iterator.next();
            if (medicalRecord.getFirstName().equals(firstName) && medicalRecord.getLastName().equals(lastName)) {
                removedMedicalRecord.add(medicalRecord);
                iterator.remove();
            }
        }
        return removedMedicalRecord;
    }

    @Override
    public MedicalRecord update(MedicalRecord medicalRecord, String firstName, String lastName) {
        return dataRepository.getData().getMedicalRecords().stream()
                .filter(f -> f.getFirstName().equals(firstName) && f.getLastName().equals(lastName))
                .findFirst()
                .map(mr -> {
                    if (medicalRecord.getBirthdate() != null) mr.setBirthdate(medicalRecord.getBirthdate());
                    if (medicalRecord.getMedication() != null) mr.setMedication(medicalRecord.getMedication());
                    if (medicalRecord.getAllergies() != null) mr.setAllergies(medicalRecord.getAllergies());
                    return mr;
                })
                .orElse(null);
    }
}
