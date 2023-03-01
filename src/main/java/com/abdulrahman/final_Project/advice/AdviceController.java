package com.abdulrahman.final_Project.advice;

import com.abdulrahman.final_Project.exception.ApiException;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.format.DateTimeParseException;

@RestControllerAdvice
public class AdviceController {


    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity ApiException(ApiException e) {
        String message = e.getMessage();
        return ResponseEntity.status(400).body(message);
    }
//    @ExceptionHandler(value =  UsernameNotFoundException.class)
//    public ResponseEntity ApiException(UsernameNotFoundException e) {
//        String message = e.getMessage();
//        return ResponseEntity.status(400).body("Wrong credential");
//    }
    @ExceptionHandler(value = DateTimeParseException.class)
    public ResponseEntity ApiException(DateTimeParseException e) {
        String message = "Enter a valid date and time like this: 2020/40/40 9:00";
        return ResponseEntity.status(400).body(message);
    }

    // Server Validation Exception
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String msg = e.getFieldError().getDefaultMessage();
        return ResponseEntity.status(400).body(msg);
    }

    // SQL Constraint Exception
    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResponseEntity SQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e){
        String msg=e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }

    // Method not allowed Exception
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> ResponseEntityHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }

    // Json parse Exception
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity HttpMessageNotReadableException(HttpMessageNotReadableException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }

    // TypesMisMatch Exception
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity MethodArgumentTypeMismatchException(DataIntegrityViolationException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(400).body("Duplicated value");
    }
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity ConstraintViolationException(ConstraintViolationException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }
}
