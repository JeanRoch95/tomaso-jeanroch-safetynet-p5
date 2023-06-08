package com.openclassrooms.safetynetp5.dto;

public class FireStationCoveredDTO {

    private int childCount;

    private int adultCount;

    private InfoPersonDTO infoPerson;

    private String firstName;

    private String phoneNumber;

    private String address;

    public FireStationCoveredDTO() {
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public int getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(int adultCount) {
        this.adultCount = adultCount;
    }

    public InfoPersonDTO getInfoPerson() {
        return infoPerson;
    }

    public void setInfoPerson(InfoPersonDTO infoPerson) {
        this.infoPerson = infoPerson;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}