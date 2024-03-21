package com.nadia.studentsmgm.service;

import com.nadia.studentsmgm.dto.AuthRequest;
import com.nadia.studentsmgm.dto.AuthResponse;
import com.nadia.studentsmgm.entity.UserCredentials;
import com.nadia.studentsmgm.repository.UserCredentialsRepository;
import com.nadia.studentsmgm.security.JwtServices;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    private final UserCredentialsRepository userCredentialsRepository;
    private final JwtServices jwtServices;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserCredentialsRepository userCredentialsRepository, JwtServices jwtServices, AuthenticationManager authenticationManager) {
        this.userCredentialsRepository = userCredentialsRepository;
        this.jwtServices = jwtServices;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.email(),
                authRequest.password()
        ));
        UserCredentials userCredentials = userCredentialsRepository.findByEmail(authRequest.email()).orElseThrow();
        String jwt = jwtServices.generateToken(userCredentials.getEmail());

        return new AuthResponse(jwt);
    }
}
