package com.example.course_management.controleur;

import com.example.course_management.entities.Speciality;
import com.example.course_management.service.IServiceSpeciality;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/specialities")
public class SpecialityController {
    IServiceSpeciality sp;
    @GetMapping
    public String getAllSpecialities(Model model) {
        List<Speciality> specialities = sp.getAllSpeciality();
        model.addAttribute("specialities", specialities);
        return "speciality/list"; // Nom du template Thymeleaf pour la liste des spécialités
    }
}
