package com.psoft.match.tcc.util.exception;

import com.psoft.match.tcc.util.exception.professor.ProfessorNotFoundException;
import com.psoft.match.tcc.util.exception.user.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApiControllerExceptionHandler {

    @ExceptionHandler({ ProfessorNotFoundException.class })
    public ResponseEntity<CustomErrorType> handleNotFoundException(RuntimeException exception, HttpServletRequest req) {
        return this.buildErrorResponse(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ UserAlreadyExistsException.class })
    public ResponseEntity<CustomErrorType> handleAlreadyExistsException(RuntimeException exception, HttpServletRequest req) {
        return this.buildErrorResponse(exception, HttpStatus.CONFLICT);
    }

    private ResponseEntity<CustomErrorType> buildErrorResponse(RuntimeException exception, HttpStatus statusCode) {
        CustomErrorType error = new CustomErrorType(exception.getMessage());
        return new ResponseEntity(error, statusCode);
    }
}
