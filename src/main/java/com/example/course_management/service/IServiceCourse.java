package com.example.course_management.service;

import com.example.course_management.entities.Course;

import java.util.List;

public interface IServiceCourse {
    public  void addCourse(Course c);
    public List<Course> getAllCourse();
    public  List<Course> getCourseByMC(String mc);
    public Course getCourse(Long id);
    public  void deleteCourse(Long id);
    public  void updateCourse(Course c);
}
