package com.nadia.studentsmgm.dto;

public record AuthRequest(
        String email,
        String password
) {
}
