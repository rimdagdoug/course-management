package com.example.course_management.repository;

import com.example.course_management.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course , Long> {
    @Query("select c from Course c where c.name like  %:x%")
    public List<Course> getCourseByMC(@Param("x") String mc);
}
