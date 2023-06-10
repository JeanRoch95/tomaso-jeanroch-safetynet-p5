package com.openclassrooms.safetynetp5.dto;

import java.util.List;

public class PhoneInfoDTO {

    private List<String> phoneNumber;

    public PhoneInfoDTO(List<String> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PhoneInfoDTO() {
    }

    public List<String> getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(List<String> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
