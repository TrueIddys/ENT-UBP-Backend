package org.ubp.ent.backend.core.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Anthony on 29/11/2015.
 */
@ControllerAdvice
public class ErrorHandler {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public void illegalArgument(HttpServletRequest req, IllegalArgumentException e) {

    }

}
