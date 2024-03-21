package com.nadia.studentsmgm.controller;

import com.nadia.studentsmgm.entity.Students;
import com.nadia.studentsmgm.service.Studentservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")     /// it allows as to set the path for the endpoint start
public class StudentsController {
    private final Logger logger = LoggerFactory.getLogger(StudentsController.class);
    private final Studentservice studentservice;

    public StudentsController(Studentservice studentservice) {
        this.studentservice = studentservice;
    }
    @PostMapping("/")
    public ResponseEntity<?> postNewCard(@RequestBody Students students){
        logger.info(students.toString());
        studentservice.createstudents(students);
        return new ResponseEntity<>("Students Created", HttpStatus.CREATED);
    }
    @GetMapping("/")
    public ResponseEntity<List<Students>> getstudents(){
        return new ResponseEntity<>(studentservice.getAllStudents(),HttpStatus.OK);
    }
    @PutMapping( "/{id}")

    public ResponseEntity<Students> postUpdateStudent(@PathVariable Long id, @RequestBody Students student){

        return new ResponseEntity<>(studentservice.updateStudents(id,student),HttpStatus.ACCEPTED);}
    @DeleteMapping( "/{id}")

    public ResponseEntity<Students> deleteStudent(@PathVariable Long id, @RequestBody Students student){

        return new ResponseEntity<>(studentservice.deleteStudents(id,student),HttpStatus.ACCEPTED);}

    }


    ///@DeleteMapping assignment







