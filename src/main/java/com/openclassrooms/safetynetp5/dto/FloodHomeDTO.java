package com.openclassrooms.safetynetp5.dto;

import java.util.List;

public class FloodHomeDTO {

    private String address;

    private String phoneNumber;

    private List<InfoPersonDTO> floodListPerson;

    public FloodHomeDTO() {
    }

    public FloodHomeDTO(String address, List<InfoPersonDTO> floodListPerson) {
        this.address = address;
        this.floodListPerson = floodListPerson;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<InfoPersonDTO> getFloodListPerson() {
        return floodListPerson;
    }

    public void setFloodListPerson(List<InfoPersonDTO> floodListPerson) {
        this.floodListPerson = floodListPerson;
    }
}
