package com.openclassrooms.safetynetp5.dto;

import java.util.List;

public class FloodHomeDTO {

    private String address;

    private List<InfoPersonFireDTO> floodListPerson;

    public FloodHomeDTO() {
    }

    public FloodHomeDTO(String address, List<InfoPersonFireDTO> floodListPerson) {
        this.address = address;
        this.floodListPerson = floodListPerson;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<InfoPersonFireDTO> getFloodListPerson() {
        return floodListPerson;
    }

    public void setFloodListPerson(List<InfoPersonFireDTO> floodListPerson) {
        this.floodListPerson = floodListPerson;
    }
}
