package com.nadia.studentsmgm.service;

import com.nadia.studentsmgm.entity.Students;
import com.nadia.studentsmgm.exception.ResourceAccessException;
import com.nadia.studentsmgm.repository.StudentsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentsServiceImpl implements Studentservice {
    private final StudentsRepository studentsRepository;

    public StudentsServiceImpl(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    @Override
    public void createstudents(Students students) {
        studentsRepository.save(students);
        studentsRepository.findAll();

    }

    @Override
    public List<Students> getAllStudents() {
        return studentsRepository.findAll();

    }

    @Override
    public Students updateStudents(Long id, Students students) {
        Optional<Students> optionalStudents = studentsRepository.findById(id);
        if(optionalStudents.isEmpty()) throw new EntityNotFoundException();
        if(!optionalStudents.get().getName().equals(students.getName())){
            optionalStudents.get().setName(students.getName());
        }
        if(!optionalStudents.get().getEmail().equals(students.getEmail())){
            optionalStudents.get().setEmail(students.getEmail());
        }
        if(!optionalStudents.get().getCourse().equals(students.getCourse())){
            optionalStudents.get().setCourse(students.getCourse());
        }

        return optionalStudents.get();
    }

    @Override
    public Students deleteStudents(Long id, Students student) {
        Students studentsdelete = studentsRepository.findById(id).orElseThrow(()-> new ResourceAccessException("Students Not found"));
        studentsRepository.delete(studentsdelete);
        return studentsdelete;
    }
}
