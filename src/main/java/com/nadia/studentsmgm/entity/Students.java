package com.nadia.studentsmgm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity

public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String course;
    @ManyToOne
    @JsonIgnore
    private Teachers teacher;

    public Students() {
    }


    public Students(String name, String email, String course) {
        this.name = name;
        this.email = email;
        this.course = course;
    }

    public Students(String name, String email, String course, Teachers teacher) {
        this.name = name;
        this.email = email;
        this.course = course;
        this.teacher = teacher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Teachers getTeacher() {
        return teacher;
    }

    public void setTeacher(Teachers teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", make='" + name + '\'' +
                ", model='" + email + '\'' +
                ", vin='" + course + '\'' +
                "Owner = " +teacher+
                '}';
    }
}
