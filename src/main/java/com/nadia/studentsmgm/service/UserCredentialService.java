package com.nadia.studentsmgm.service;

import com.nadia.studentsmgm.entity.UserCredentials;

import java.util.List;
import java.util.Optional;

public interface UserCredentialService {
    void createuser(UserCredentials userCredentials);
    void deletuser(Long id);
    List<UserCredentials> getAllUsers();
    UserCredentials getUserById(Long id);

    Optional<UserCredentials> getUserByEmail(String email);
}
