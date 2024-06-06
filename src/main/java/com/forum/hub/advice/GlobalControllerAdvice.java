package com.forum.hub.advice;

import com.forum.hub.service.exceptions.CourseNotFoundException;
import com.forum.hub.service.exceptions.UserNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<String> userNotFound(Exception e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("autor não existe");
  }

  @ExceptionHandler(CourseNotFoundException.class)
  public ResponseEntity<String> courseNotFound(Exception e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("curso não existe");
  }

  @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
  public ResponseEntity<String> constraintViolation(Exception e) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body("Mensagem já cadastrada");
  }
}
