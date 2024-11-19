package com.example.course_management.service;

import com.example.course_management.entities.Speciality;

import java.util.List;

public interface IServiceSpeciality {
    public  void addSpeciality(Speciality s);
    public List<Speciality> getAllSpeciality();
    public  List<Speciality> getSpecialityByMC(String mc);
    public Speciality getSpeciality(Long id);
    public  void deleteSpeciality(Long id);
    public  void updateSpeciality(Speciality s);
}
