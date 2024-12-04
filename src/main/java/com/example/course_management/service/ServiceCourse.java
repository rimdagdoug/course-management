package com.example.course_management.service;

import com.example.course_management.entities.Course;
import com.example.course_management.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
//créer une instance de votre classe en passant toutes les dépendances requises à ce constructeur
@AllArgsConstructor
@Service
public class ServiceCourse implements IServiceCourse{
    // Dépendance injectée
    private CourseRepository courseRepository;
    @Override
    public void addCourse(Course c) {
        courseRepository.save(c);
    }

    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> getCourseByMC(String mc) {
        return courseRepository.getCourseByMC(mc);
    }

    @Override
    public Course getCourse(Long id) {
        Course resultat =    courseRepository.findCourseWithSpecialities(id);
        return resultat;
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public void updateCourse(Course c) {
        courseRepository.save(c);
    }
}
