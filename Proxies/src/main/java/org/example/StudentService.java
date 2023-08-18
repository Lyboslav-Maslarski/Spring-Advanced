package org.example;

import java.util.List;

public class StudentService implements StudentServiceIfc {
    private static List<StudentDTO> allStudents = List.of(
            new StudentDTO("Pesho", 5, 21),
            new StudentDTO("Gosho", 3, 21),
            new StudentDTO("Tosho", 6, 21)
    );

    @Override
    @Cacheable("students")
    public List<StudentDTO> getALlStudents() {
        System.out.println("Complex calculation of all students.");

        dummyWait();

        System.out.println("Students calculated.");

        return allStudents;
    }

    private void dummyWait() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
