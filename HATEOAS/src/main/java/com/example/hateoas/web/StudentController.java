package com.example.hateoas.web;

import com.example.hateoas.model.dto.OrderDTO;
import com.example.hateoas.model.dto.StudentDTO;
import com.example.hateoas.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<StudentDTO>>> getStudents() {
        List<EntityModel<StudentDTO>> models = studentService
                .getAllStudents()
                .stream()
                .map(s -> EntityModel.of(s, getStudentsLinks(s)))
                .toList();

        return ResponseEntity.ok(CollectionModel.of(models));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDTO>> getStudentById(@PathVariable("id") Long studentId) {
        Optional<StudentDTO> studentById = studentService.getStudentById(studentId);

        if (studentById.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        StudentDTO studentDTO = studentById.get();

        return ResponseEntity.ok(EntityModel.of(studentDTO, getStudentsLinks(studentDTO)));
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<CollectionModel<EntityModel<OrderDTO>>> getStudentOrders(@PathVariable("id") Long studentId) {
        List<EntityModel<OrderDTO>> models = studentService
                .getStudentOrders(studentId)
                .stream()
                .map(o -> EntityModel.of(o, getOrderLinks(o)))
                .toList();
        return ResponseEntity.ok(CollectionModel.of(models));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDTO>> updateStudent(@PathVariable("id") Long studentId,
                                                                 StudentDTO studentDTO) {
        return null;
    }

    private Link[] getStudentsLinks(StudentDTO studentDTO) {
        List<Link> studentLinks = new ArrayList<>();

        Link link = linkTo(methodOn(StudentController.class).getStudentById(studentDTO.getId())).withSelfRel();

        studentLinks.add(link);

        if (!studentDTO.isDeleted()) {
            Link orderLink = linkTo(methodOn(StudentController.class)
                    .getStudentOrders(studentDTO.getId()))
                    .withRel("orders");

            studentLinks.add(orderLink);

            Link updateLink = linkTo(methodOn(StudentController.class)
                    .updateStudent(studentDTO.getId(), studentDTO))
                    .withRel("update");

            studentLinks.add(updateLink);
        }

        return studentLinks.toArray(new Link[0]);
    }

    private Link getOrderLinks(OrderDTO orderDTO) {
        return linkTo(methodOn(StudentController.class).getStudentById(orderDTO.getStudentId()))
                .withRel("student");
    }
}
