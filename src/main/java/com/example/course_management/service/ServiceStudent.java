package com.example.course_management.service;
import com.example.course_management.entities.Student;
import com.example.course_management.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ServiceStudent implements IServiceStudent{
    private StudentRepository studentRepository;
    @Override
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public void updateStudent(Long id, Student student) {
        // Vérifier si l'étudiant existe
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));

        // Mettre à jour les propriétés de l'étudiant existant
        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setPhoneNumber(student.getPhoneNumber());
        existingStudent.setPassword(student.getPassword());
        // Mettre à jour les spécialités
        if (student.getSpecialities() != null) {
            existingStudent.setSpecialities(student.getSpecialities());
        }

        // Enregistrer l'étudiant mis à jour
        studentRepository.save(existingStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
