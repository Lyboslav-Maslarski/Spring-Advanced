package com.example.cache.model;

public class StudentDTO {
    private String name;
    private int avgScore;
    private int age;

    public StudentDTO() {
    }

    public StudentDTO(String name, int avgScore, int age) {
        this.name = name;
        this.avgScore = avgScore;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public StudentDTO setName(String name) {
        this.name = name;
        return this;
    }

    public int getAvgScore() {
        return avgScore;
    }

    public StudentDTO setAvgScore(int avgScore) {
        this.avgScore = avgScore;
        return this;
    }

    public int getAge() {
        return age;
    }

    public StudentDTO setAge(int age) {
        this.age = age;
        return this;
    }
}
