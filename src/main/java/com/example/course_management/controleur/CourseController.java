package com.example.course_management.controleur;

import com.example.course_management.entities.Course;
import com.example.course_management.entities.Speciality;
import com.example.course_management.repository.SpecialityRepository;
import com.example.course_management.service.IServiceCourse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/course")
public class CourseController {

    IServiceCourse ce;
    SpecialityRepository sp;
    @GetMapping
    public String getAllCourses(Model model) {
        List<Course> courses = ce.getAllCourse();
        model.addAttribute("courses", courses);
        return "course/list";
    }

    @GetMapping("/{id}")
    public String getCourseDetails(@PathVariable Long id, Model model) {
        Course course = ce.getCourse(id);
        model.addAttribute("course", course);
        return "course/details";
    }
    @GetMapping("/add")
    public String showAddCourseForm(Model model) {
        model.addAttribute("course", new Course()); // Initialize a new Course object
        List<Speciality> specialities = sp.findAll();
        model.addAttribute("specialities", specialities);
        return "course/add"; // Return to the add course template
    }

    @PostMapping("/add")
    public String addCourse(@ModelAttribute Course course) {
        ce.addCourse(course);
        return "redirect:/course";
    }

    @GetMapping("/edit/{id}")
    public String showEditCourseForm(@PathVariable Long id, Model model) {
        Course course = ce.getCourse(id);
        List<Speciality> specialities = sp.findAll();
        model.addAttribute("course", course);
        model.addAttribute("specialities", specialities);
        return "course/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateCourse(@PathVariable Long id, @ModelAttribute Course course) {
        course.setCourse_id(id);
        ce.updateCourse(course);
        return "redirect:/course";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        ce.deleteCourse(id); // Assume this method exists in IServiceCourse
        return "redirect:/course";
    }


}
