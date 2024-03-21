package com.nadia.studentsmgm.repository;

import com.nadia.studentsmgm.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepository extends JpaRepository<Students, Long> {

}

