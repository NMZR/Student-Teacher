package com.nadia.studentsmgm.controller;

import com.nadia.studentsmgm.entity.Teachers;
import com.nadia.studentsmgm.service.Teacherservice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")
public class TeacherController {
    private final Teacherservice teacherservice;

    public TeacherController(Teacherservice teacherservice) {
        this.teacherservice = teacherservice;
    }
    @PostMapping("/")
    public ResponseEntity<?> postNewCard(@RequestBody Teachers teacher){
        teacherservice.createTeacher(teacher);
        return new ResponseEntity<>("Teacher Created", HttpStatus.CREATED);
    }
    @GetMapping("/")
    public ResponseEntity<List<Teachers>> getAllTeachers(){
        return new ResponseEntity<>(teacherservice.getAllTeachers(),HttpStatus.FOUND);
    }
    @PutMapping( "/{id}")
    public ResponseEntity<Teachers> UpdateStudent(@PathVariable Long id, @RequestBody Teachers teacher){

        return new ResponseEntity<>(teacherservice.updateTeacher(id,teacher),HttpStatus.ACCEPTED);


    }
    @DeleteMapping( "/{id}")
    public ResponseEntity<Teachers> deleteteacher(@PathVariable Long id, @RequestBody Teachers teacher){

        return new ResponseEntity<>(teacherservice.deleteteacher(id,teacher), HttpStatus.ACCEPTED);


    }
}
