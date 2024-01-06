package com.lenhac.deprakt.dto;

/**
 * Created by Lee N on 06, Sat,Jan,2024.
 */
public class OrganizationDTO {

    private Long id;
    private String name;
    private String phoneNumber;
    private Integer pinNumber;
    private String emailAddress;

    // Constructors, getters, and setters

    public OrganizationDTO(Long id, String name, String phoneNumber, Integer pinNumber, String emailAddress) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.pinNumber = pinNumber;
        this.emailAddress = emailAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(Integer pinNumber) {
        this.pinNumber = pinNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
