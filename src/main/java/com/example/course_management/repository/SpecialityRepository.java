package com.example.course_management.repository;

import com.example.course_management.entities.Course;
import com.example.course_management.entities.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpecialityRepository extends JpaRepository<Speciality , Long> {
    @Query("select s from Speciality s where s.specialty_name like  %:x%")
    public List<Speciality> getSpecialityByMC(@Param("x") String mc);
}
