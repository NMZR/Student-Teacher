package com.nadia.studentsmgm.service;

import com.nadia.studentsmgm.entity.UserCredentials;
import com.nadia.studentsmgm.repository.UserCredentialsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCredentialServiceImpl implements UserCredentialService{
    private final UserCredentialsRepository userCredentialsRepository;

    public UserCredentialServiceImpl(UserCredentialsRepository userCredentialsRepository) {
        this.userCredentialsRepository = userCredentialsRepository;
    }

    @Override
    public void createuser(UserCredentials userCredentials) {
        userCredentialsRepository.save(userCredentials);

    }

    @Override
    public void deletuser(Long id) {
        userCredentialsRepository.deleteById(id);


    }

    @Override
    public List<UserCredentials> getAllUsers() {

        return (List<UserCredentials>) userCredentialsRepository.findAll();
    }

    @Override
    public UserCredentials getUserById(Long id) {
        return userCredentialsRepository.findById(id).orElseThrow();
    }

    @Override
    public Optional<UserCredentials> getUserByEmail(String email) {
        return userCredentialsRepository.findByEmail(email);
    }
}
