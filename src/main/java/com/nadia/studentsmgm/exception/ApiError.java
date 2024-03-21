package com.nadia.studentsmgm.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;
//record is the type of class that is imutable.
public record ApiError(
        String Path,
        String message,

        int statuseCode,
        LocalDateTime timestamp) {

}
