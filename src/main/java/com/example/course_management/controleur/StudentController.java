package com.example.course_management.controleur;

import com.example.course_management.entities.Course;
import com.example.course_management.entities.Speciality;
import com.example.course_management.entities.Student;
import com.example.course_management.repository.SpecialityRepository;
import com.example.course_management.service.IServiceStudent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController {
    IServiceStudent serviceStudent;
    SpecialityRepository specialityRepository;
    @GetMapping
    public String getAllStudents(Model model) {
        List<Student> students = serviceStudent.getAllStudents();
        model.addAttribute("students", students);
        return "student/list";
    }

    @GetMapping("/add")
    public String showAddCourseForm(Model model) {
        model.addAttribute("student", new Student());
        List<Speciality> specialities = specialityRepository.findAll();
        model.addAttribute("specialities", specialities);
        return "student/add";
    }

    @PostMapping("/add")
    public String addStudent(@ModelAttribute Student student) {
        serviceStudent.addStudent(student);
        return "redirect:/students";
    }
}
