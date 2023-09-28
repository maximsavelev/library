package ru.savelyev.library.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.savelyev.library.exception.EntityNotFoundException;
import ru.savelyev.library.exception.InvalidDataException;
import ru.savelyev.library.exception.NoSuchResourceException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessage handleNotFoundException(EntityNotFoundException e) {
        log.error(e.getMessage(), e);
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ExceptionHandler(InvalidDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessage handleNotFoundException(InvalidDataException e) {
        log.error(e.getMessage(), e);
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(NoSuchResourceException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public ErrorMessage handleNoSuchResourceException (NoSuchResourceException e) {
        log.error(e.getMessage(), e);
        return new ErrorMessage(HttpStatus.NO_CONTENT.value(), e.getMessage());
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error(ex.getMessage(), ex);
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return handleExceptionInternal(ex, new ErrorMessage(HttpStatus.BAD_REQUEST.value(), errors.toString()),
                new HttpHeaders(), HttpStatus.BAD_REQUEST , request);
    }
}