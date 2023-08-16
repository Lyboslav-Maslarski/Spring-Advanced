package com.example.hateoas.model.dto;

public class OrderDTO {
    private Long studentId;
    private Long courseId;

    public OrderDTO() {
    }

    public Long getStudentId() {
        return studentId;
    }

    public OrderDTO setStudentId(Long studentId) {
        this.studentId = studentId;
        return this;
    }

    public Long getCourseId() {
        return courseId;
    }

    public OrderDTO setCourseId(Long courseId) {
        this.courseId = courseId;
        return this;
    }
}
