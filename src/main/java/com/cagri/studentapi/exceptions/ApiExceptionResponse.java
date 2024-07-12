package com.cagri.studentapi.exceptions;

import java.time.LocalDateTime;

public record ApiExceptionResponse(int status, String message, LocalDateTime dateTime) {
}
