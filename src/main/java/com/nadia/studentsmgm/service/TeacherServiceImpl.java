package com.nadia.studentsmgm.service;

import com.nadia.studentsmgm.entity.Teachers;
import com.nadia.studentsmgm.exception.ResourceAccessException;
import com.nadia.studentsmgm.repository.TeachersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements Teacherservice{
    private final TeachersRepository teahcerRepository;

    public TeacherServiceImpl(TeachersRepository teahcerRepository) {
        this.teahcerRepository = teahcerRepository;
    }

    @Override
    public void createTeacher(Teachers teacher) {
        teahcerRepository.save(teacher);
        teahcerRepository.findAll();

    }

    @Override
    public List<Teachers> getAllTeachers() {
        return teahcerRepository.findAll();
    }

    @Override
    public Teachers updateTeacher(Long id, Teachers teacher) {
        Optional<Teachers> optionalTeacher =teahcerRepository.findById(id);
        if(optionalTeacher.isEmpty()) throw new EntityNotFoundException();
        if(!optionalTeacher.get().getName().equals(teacher.getName())){
            optionalTeacher.get().setName(teacher.getName());
        }
        if(!optionalTeacher.get().getDob().equals(teacher.getDob())){
            optionalTeacher.get().setDob(teacher.getDob());
        }
        return optionalTeacher.get();

    }

    @Override
    public Teachers deleteteacher(long id, Teachers teachers) {
        Teachers deleteteacher = teahcerRepository.findById(id).orElseThrow(()-> new ResourceAccessException("Students Not found"));
        teahcerRepository.delete(deleteteacher);
        return deleteteacher;
    }





}
