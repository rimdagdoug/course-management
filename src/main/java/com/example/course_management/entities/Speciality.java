package com.example.course_management.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

public class Speciality {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long specialty_id;
    @Column(unique = true)
    public String specialty_name;
    @OneToMany(mappedBy = "speciality", cascade = CascadeType.ALL)
    private List<Course> courses;
}
