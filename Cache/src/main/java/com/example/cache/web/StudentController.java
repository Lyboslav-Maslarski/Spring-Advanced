package com.example.cache.web;

import com.example.cache.model.StudentDTO;
import com.example.cache.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> allStudents = studentService.getAllStudents();

        return ResponseEntity.ok(allStudents);
    }

    @GetMapping("/search")
    public ResponseEntity<StudentDTO> getStudentByName(@RequestParam("name") String name) {
        StudentDTO studentDTO = studentService.getStudentByName(name);

        if (studentDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentDTO);
    }

    @GetMapping("/evict")
    public ResponseEntity<StudentDTO> evict() {
        studentService.refresh();
        return ResponseEntity.noContent().build();
    }
}
