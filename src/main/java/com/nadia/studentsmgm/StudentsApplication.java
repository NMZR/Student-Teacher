package com.nadia.studentsmgm;

import com.nadia.studentsmgm.entity.Students;
import com.nadia.studentsmgm.entity.Teachers;
import com.nadia.studentsmgm.repository.StudentsRepository;
import com.nadia.studentsmgm.repository.TeachersRepository;
import org.slf4j.Logger; // search
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class StudentsApplication implements CommandLineRunner {
    @Autowired
    private StudentsRepository studentsRepository;
    @Autowired
    private TeachersRepository teacherRepository;
    private static final Logger logger = LoggerFactory.getLogger(StudentsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(StudentsApplication.class, args);
        logger.info("Application Started");
        logger.error("An Error Example");
        logger.trace("A trace Example");
        logger.warn("A Warning !!!!");

    }



    @Override
    public void run(String... args) throws Exception {
        studentsRepository.saveAll(Arrays.asList(
                new Students("nadia","45289hj","hjde6789"),
                new Students("asra","150fghs","aghfejakf"),
                new Students("Scion","jh70","kajdfh")

        ));
        List<Students> carList = studentsRepository.findAll();
        carList.forEach(x-> logger.info(x.toString()));
        Teachers teacher = teacherRepository.save(new Teachers("nadia", LocalDate.parse("1999-01-02")));
        teacherRepository.save(new Teachers("Azim", LocalDate.parse("1999-08-06")));
        teacherRepository.save(new Teachers("Haseeb", LocalDate.parse("1989-09-12")));
        Students students =new Students("rehan","rehan1","5678vbnm",teacher);
        studentsRepository.save(students);
        studentsRepository.findAll().forEach(x -> logger.info(x.toString()));


    }
}
// annotations
// @ spring boot represents: config, coponent scan, Auto configuration
// @REST cantroler: