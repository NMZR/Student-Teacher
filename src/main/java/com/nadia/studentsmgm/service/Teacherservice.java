package com.nadia.studentsmgm.service;

import com.nadia.studentsmgm.entity.Students;
import com.nadia.studentsmgm.entity.Teachers;

import java.util.List;

public interface Teacherservice {
    void createTeacher(Teachers teacher);
    List<Teachers> getAllTeachers();
    Teachers updateTeacher(Long id, Teachers teacher);

     Teachers deleteteacher(long id, Teachers teachers);
}


