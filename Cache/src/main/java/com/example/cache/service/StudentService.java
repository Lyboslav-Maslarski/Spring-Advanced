package com.example.cache.service;

import com.example.cache.model.StudentDTO;
import com.example.cache.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Cacheable("students")
    public List<StudentDTO> getAllStudents() {
        LOGGER.info("Getting all students.");
        return studentRepository.getAllStudents();
    }

    @Cacheable("students")
    public StudentDTO getStudentByName(String name) {
        LOGGER.info("Getting student by name {}.", name);
        return studentRepository.findStudentByName(name);
    }

    @CacheEvict(cacheNames = "students", allEntries = true)
    public void refresh() {

    }
}
