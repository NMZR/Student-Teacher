package com.nadia.studentsmgm;

import com.nadia.studentsmgm.controller.StudentsController;
import com.nadia.studentsmgm.entity.Students;
import com.nadia.studentsmgm.service.Studentservice;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers

public class TestContainers {
    @LocalServerPort
    private int port;
    @Autowired
    JdbcTemplate jbdcTemplate;
    @Autowired
    Studentservice studentservice;
    @Autowired
    StudentsController studentsCantroller;
    @Container
    private static final MySQLContainer<?> mysqlContainer = new MySQLContainer<>(DockerImageName.parse("mysql:latest"))
    .withExposedPorts(3306, 3322)
    .withDatabaseName("schooldb")
    .withUsername("nadiam")
    .withPassword("nadiamalikzada");
    @DynamicPropertySource
    static void configureproperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mysqlContainer::getUsername);
        registry.add("spring.datasource.password", mysqlContainer::getPassword);
    }
    @BeforeAll
    public static void BeforeAll(){mysqlContainer.start();}
    @AfterAll
    public static void AfterAll(){mysqlContainer.stop();}
    @Test
    void testcontaizedDatabase(){
        jbdcTemplate.execute("USE schooldb");
        int count = jbdcTemplate.queryForObject("SELECT COUNT(*) FROM students", Integer.class);
        Assertions.assertEquals(4,count);
    }
    @Test
    void setStudentsCantrollerShould_return_list(){
        ResponseEntity<List<Students>> response = studentsCantroller.getstudents();
        Assertions.assertEquals(302, response.getStatusCode().value());
        Assertions.assertNotNull(response.getBody());
    }

}
