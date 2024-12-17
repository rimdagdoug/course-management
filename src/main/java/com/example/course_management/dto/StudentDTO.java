package com.example.course_management.dto;

import java.util.List;

public class StudentDTO {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;

    private String password;
    private List<Long> specialities;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Long> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<Long> specialities) {
        this.specialities = specialities;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
