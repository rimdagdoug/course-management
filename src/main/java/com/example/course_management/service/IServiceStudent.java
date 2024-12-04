package com.example.course_management.service;

import com.example.course_management.entities.Speciality;
import com.example.course_management.entities.Student;

import java.util.List;

public interface IServiceStudent {
    public  void addStudent(Student student);
    public List<Student> getAllStudents();

}
