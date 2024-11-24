package com.example.course_management.controleur;

import com.example.course_management.entities.Speciality;
import com.example.course_management.service.IServiceSpeciality;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "speciality/list";
    }

    @GetMapping("/add")
    public String showAddSpecialityForm(Model model) {
        model.addAttribute("speciality", new Speciality());
        return "speciality/add";
    }

    @PostMapping("/add")
    public String addSpeciality(@ModelAttribute Speciality speciality) {
        sp.addSpeciality(speciality);
        return "redirect:/specialities";
    }

    @GetMapping("/delete/{id}")
    public  String dlete(@PathVariable Long id){
        sp.deleteSpeciality(id);
        return "redirect:/specialities";
    }

    @GetMapping("/edit/{id}")
    public String showEditSpecialityForm(@PathVariable Long id, Model model) {
        Speciality speciality = sp.getSpeciality(id);
        model.addAttribute("speciality", speciality);
        return "speciality/edit";
    }

    @PostMapping("/edit")
    public String updateSpeciality(@ModelAttribute Speciality speciality) {
        sp.updateSpeciality(speciality);
        return "redirect:/specialities";
    }
}
