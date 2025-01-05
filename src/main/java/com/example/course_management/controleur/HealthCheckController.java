package com.example.course_management.controleur;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/live")
    public ResponseEntity<String> live() {
        return ResponseEntity.ok("Alive");
    }

    @GetMapping("/ready")
    public ResponseEntity<String> ready() {
        // Ajoutez une logique pour vérifier si l'application est prête (ex : connexion à la DB)
        return ResponseEntity.ok("Ready");
    }
}
