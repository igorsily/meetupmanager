package com.igorsily.meetupmanager.controller;

import com.igorsily.meetupmanager.controller.exceptions.ApiErrors;
import com.igorsily.meetupmanager.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleValidateException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        return new ApiErrors(bindingResult);
    }


    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleBusinessException(BusinessException e) {
        return new ApiErrors(e);
    }


    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus
    public ResponseEntity<ApiErrors> handleResponseStatusException(ResponseStatusException ex) {
        return new ResponseEntity<ApiErrors>(new ApiErrors(ex), ex.getStatus());
    }
}
