package com.example.course_management.controleur;

import com.example.course_management.dto.StudentDTO;
import com.example.course_management.entities.Course;
import com.example.course_management.entities.Speciality;
import com.example.course_management.entities.Student;
import com.example.course_management.repository.SpecialityRepository;
import com.example.course_management.service.IServiceStudent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Student student = serviceStudent.getStudentById(id);

        // Convertir l'étudiant en StudentDTO
        StudentDTO studentDTO = getStudentDTO(student);
        model.addAttribute("student", studentDTO);

        // Fetch all specialities
        List<Speciality> specialities = specialityRepository.findAll();
        model.addAttribute("specialities", specialities);

        return "student/edit"; // Assuming this is your edit template
    }

    private static StudentDTO getStudentDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setPhoneNumber(student.getPhoneNumber());
        List<Long> specialityIds = student.getSpecialities().stream()
                .map(Speciality::getSpeciality_id)
                .collect(Collectors.toList());
        studentDTO.setSpecialities(specialityIds);
        studentDTO.setPassword(student.getPassword());
        return studentDTO;
    }


    @PostMapping("/edit/{id}")
    public String editStudent(@PathVariable Long id, @ModelAttribute Student student) {
        serviceStudent.updateStudent(id, student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        serviceStudent.deleteStudent(id);
        return "redirect:/students";
    }

    @GetMapping("/{id}")
    public String viewDetails(@PathVariable Long id, Model model) {
        Student student = serviceStudent.getStudentById(id);
        model.addAttribute("student", student);
        return "student/details";
    }
}
