package com.example.course_management.service;

import com.example.course_management.entities.Speciality;
import com.example.course_management.repository.SpecialityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class ServiceSpeciality implements IServiceSpeciality{
    private SpecialityRepository specialityRepository;
    @Override
    public void addSpeciality(Speciality s) {
        specialityRepository.save(s);
    }

    @Override
    public List<Speciality> getAllSpeciality() {
        return specialityRepository.findAll();
    }

    @Override
    public List<Speciality> getSpecialityByMC(String mc) {
        return specialityRepository.getSpecialityByMC(mc);
    }

    @Override
    public Speciality getSpeciality(Long id) {
        return specialityRepository.findById(id).get();
    }

    @Override
    public void deleteSpeciality(Long id) {
        specialityRepository.deleteById(id);
    }

    @Override
    public void updateSpeciality(Speciality s) {
        specialityRepository.save(s);
    }
}
