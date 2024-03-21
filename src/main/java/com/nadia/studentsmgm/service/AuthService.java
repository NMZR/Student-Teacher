package com.nadia.studentsmgm.service;

import com.nadia.studentsmgm.dto.AuthRequest;
import com.nadia.studentsmgm.dto.AuthResponse;

public interface AuthService {
    AuthResponse login (AuthRequest authRequest);
}
