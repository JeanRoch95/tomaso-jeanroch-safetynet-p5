package com.openclassrooms.safetynetp5.dto;

import java.util.List;

public class FullInfoPersonDTO {

    private String email;

    private String address;

    private List<InfoPersonDTO> InfoPerson;

    public FullInfoPersonDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<InfoPersonDTO> getInfoPerson() {
        return InfoPerson;
    }

    public void setInfoPerson(List<InfoPersonDTO> infoPerson) {
        InfoPerson = infoPerson;
    }
}
