package com.example.lms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @GetMapping("/personalized")
    public ResponseEntity<?> getPersonalizedCourses(
            @RequestParam(required = false) String level) {
        // Dummy data for demo purposes
        List<Map<String, Object>> courses = new ArrayList<>();
        courses.add(Map.of(
            "id", 1,
            "title", "AI for Beginners",
            "level", "beginner",
            "modules", List.of(
                Map.of("title", "Intro to AI", "youtube", "https://www.youtube.com/embed/1"),
                Map.of("title", "AI Applications", "youtube", "https://www.youtube.com/embed/2"),
                Map.of("title", "AI Quiz", "youtube", ""),
                Map.of("title", "AI Assignment", "youtube", ""),
                Map.of("title", "AI Community", "youtube", "")
            )
        ));
        courses.add(Map.of(
            "id", 2,
            "title", "DevOps Essentials",
            "level", "intermediate",
            "modules", List.of(
                Map.of("title", "Intro to DevOps", "youtube", "https://www.youtube.com/embed/3"),
                Map.of("title", "CI/CD", "youtube", "https://www.youtube.com/embed/4"),
                Map.of("title", "DevOps Quiz", "youtube", ""),
                Map.of("title", "DevOps Assignment", "youtube", ""),
                Map.of("title", "DevOps Community", "youtube", "")
            )
        ));
        courses.add(Map.of(
            "id", 3,
            "title", "Python Programming",
            "level", "beginner",
            "modules", List.of(
                Map.of("title", "Intro to Python", "youtube", "https://www.youtube.com/embed/5"),
                Map.of("title", "Python Basics", "youtube", "https://www.youtube.com/embed/6"),
                Map.of("title", "Python Quiz", "youtube", ""),
                Map.of("title", "Python Assignment", "youtube", ""),
                Map.of("title", "Python Community", "youtube", "")
            )
        ));
        // Filter by level if provided
        if (level != null) {
            courses = courses.stream()
                .filter(c -> level.equalsIgnoreCase((String)c.get("level")))
                .toList();
        }
        return ResponseEntity.ok(courses);
    }
}