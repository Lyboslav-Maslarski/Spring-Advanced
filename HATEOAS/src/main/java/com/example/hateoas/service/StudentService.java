package com.example.hateoas.service;

import com.example.hateoas.model.dto.OrderDTO;
import com.example.hateoas.model.dto.StudentDTO;
import com.example.hateoas.model.entity.OrderEntity;
import com.example.hateoas.model.entity.StudentEntity;
import com.example.hateoas.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Optional<StudentDTO> getStudentById(Long studentId) {
        return studentRepository.findById(studentId).map(this::map);
    }

    private StudentDTO map(StudentEntity studentEntity) {
        List<OrderDTO> orders = studentEntity
                .getOrders()
                .stream()
                .map(this::map)
                .toList();

        return new StudentDTO()
                .setAge(studentEntity.getAge())
                .setDeleted(studentEntity.isDeleted())
                .setId(studentEntity.getId())
                .setName(studentEntity.getName())
                .setOrders(orders);
    }

    private OrderDTO map(OrderEntity orderEntity) {
        return new OrderDTO()
                .setCourseId(orderEntity.getCourse().getId())
                .setStudentId(orderEntity.getStudent().getId());
    }
}
