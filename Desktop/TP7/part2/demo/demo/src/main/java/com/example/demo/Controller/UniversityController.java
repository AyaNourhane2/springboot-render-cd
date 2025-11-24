package com.example.demo.Controller;

import com.example.demo.Service.UniversityService;  // ← 'S' majuscule !
import com.example.demo.model.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/university")
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @PostMapping("/add")
    public String add(@RequestBody University university) {
        System.out.println("🎯 UniversityController ADD called with: " + university.getName());
        universityService.saveUniversity(university);
        return "New university is added";
    }

    @GetMapping("/getAll")
    public List<University> getAllUniversities() {
        System.out.println("🎯 UniversityController GET ALL called");
        return universityService.getAllUniversities();
    }
}