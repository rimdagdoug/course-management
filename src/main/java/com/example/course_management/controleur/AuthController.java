package com.example.course_management.controleur;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    @GetMapping("/")
    public  String goToHomePage()
    {
        return  "redirect:/specialities/user/home";
    }

    @GetMapping("/notAuthorized")
    public String notAuthorized(Model model) {
        return "redirect:/erreurPage";
    }

    @GetMapping("/erreurPage")
    public String erreurPage(Model model) {
        return "erreurPage"; // This should correspond to erreurPage.html
    }

}
