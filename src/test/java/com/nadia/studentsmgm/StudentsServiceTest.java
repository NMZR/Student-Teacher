package com.nadia.studentsmgm;
///machito

import com.nadia.studentsmgm.entity.Students;
import com.nadia.studentsmgm.repository.StudentsRepository;
import com.nadia.studentsmgm.service.StudentsServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;


public class StudentsServiceTest {
    @Mock
    private StudentsRepository studentsRepository;
    private AutoCloseable cloaabale;
    @BeforeEach
    public void setup() throws Exception{
       cloaabale = MockitoAnnotations.openMocks(this);

    }
    @AfterEach
    public void tearDown() throws Exception{
        cloaabale.close();
    }
    @InjectMocks
    private StudentsServiceImpl studentsService;

    @Test
    public void studentsServiceGetAllStudents_Sucssess_shouldReturnList(){
        List<Students> ExpectedStuednts = new ArrayList<>();
        ExpectedStuednts.addAll(Arrays.asList(
                new Students(),
                new Students()
                )

        );
        Mockito.when(studentsRepository.findAll()).thenReturn(ExpectedStuednts);
        List<Students> ActualStudents = studentsService.getAllStudents();
        assertThat(ActualStudents).isEqualTo(ExpectedStuednts);
    }
    @Test
    public void StudentsService_shouldSaveStudents(){
        Students students = new Students();
        studentsService.createstudents(students);
        verify(studentsRepository).save(students);

    }

}
