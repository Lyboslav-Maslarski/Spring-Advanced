package com.example.cache.repository;

import com.example.cache.model.StudentDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {

    private static List<StudentDTO> allStudents = List.of(
            new StudentDTO("Pesho", 5, 21),
            new StudentDTO("Gosho", 3, 19),
            new StudentDTO("Tosho", 6, 22)
    );

    public List<StudentDTO> getAllStudents() {
        dummyWait();

        return allStudents;
    }

    public StudentDTO findStudentByName(String name) {
        dummyWait();

        return allStudents
                .stream()
                .filter(s -> s.getName().equals(name))
                .findAny()
                .orElse(null);
    }

    private void dummyWait() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
