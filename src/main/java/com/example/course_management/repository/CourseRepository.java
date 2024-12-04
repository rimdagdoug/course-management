package com.example.course_management.repository;

import com.example.course_management.entities.Course;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course , Long> {
    @Query("select c from Course c where c.name like  %:x%")
    public List<Course> getCourseByMC(@Param("x") String mc);

    @Query("SELECT c FROM Course c JOIN FETCH c.specialities WHERE c.course_id = :id")
    Course findCourseWithSpecialities(@Param("id") Long id);

}
