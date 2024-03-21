package com.nadia.studentsmgm.repository;

import com.nadia.studentsmgm.entity.UserCredentials;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserCredentialsRepository extends CrudRepository<UserCredentials,Long> {

    Optional<UserCredentials> findByEmail(String email);
}
