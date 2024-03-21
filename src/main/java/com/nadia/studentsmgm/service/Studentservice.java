package com.nadia.studentsmgm.service;

import com.nadia.studentsmgm.entity.Students;

import java.util.List;

public interface Studentservice {
    void createstudents(Students students);
    List<Students> getAllStudents();
    Students updateStudents(Long id, Students students);
    Students deleteStudents(Long id, Students student);
}
