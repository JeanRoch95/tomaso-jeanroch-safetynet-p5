package com.openclassrooms.safetynetp5.dto;

import java.util.List;

public class InfoPersonFireDTO {

    private InfoPersonDTO infoPerson;

    private String phoneNumber;

    private String station;

    public InfoPersonFireDTO() {
    }

    public InfoPersonFireDTO(InfoPersonDTO infoPersonDTO, String phoneNumber, String station) {
        this.infoPerson = infoPersonDTO;
        this.phoneNumber = phoneNumber;
        this.station = station;
    }

    public InfoPersonDTO getInfoPerson() {
        return infoPerson;
    }

    public void setInfoPerson(InfoPersonDTO infoPerson) {
        this.infoPerson = infoPerson;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }
}
