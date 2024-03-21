package com.nadia.studentsmgm.controller;

import com.nadia.studentsmgm.dto.AuthRequest;
import com.nadia.studentsmgm.dto.AuthResponse;
import com.nadia.studentsmgm.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest){
        return new ResponseEntity<>(authService.login(authRequest), HttpStatus.OK);
    }

}
