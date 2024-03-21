package com.nadia.studentsmgm.controller;

import com.nadia.studentsmgm.entity.UserCredentials;
import com.nadia.studentsmgm.service.UserCredentialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserCredentialService userCredentialService;

    public UserController(UserCredentialService userCredentialService) {
        this.userCredentialService = userCredentialService;
    }
    @PostMapping("/")
    public ResponseEntity<?> postNewUser(UserCredentials userCredentials){
        userCredentialService.createuser(userCredentials);
        return new ResponseEntity<>("User Created", HttpStatus.CREATED);
    }

}
