package com.example.task.handler;

import com.example.task.response.RestErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestErrorResponse> handleGeneralException(Exception ex) {

        RestErrorResponse restErrorResponse = new RestErrorResponse();
        Map<String, Object> body = new LinkedHashMap<>();
        restErrorResponse.setCode("general error status");
        restErrorResponse.setMessage(ex.getMessage());
        restErrorResponse.setAdditional(new HashMap<>());

        return new ResponseEntity<>(restErrorResponse, HttpStatus.BAD_REQUEST);

    }


    // error handle for @Valid
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        RestErrorResponse restErrorResponse = new RestErrorResponse();
        Map<String, Object> body = new LinkedHashMap<>();

        restErrorResponse.setCode(String.valueOf(status.value()));
        restErrorResponse.setMessage("Invalid fields");

        //reasons
        Set<String> errors = exception.getBindingResult().getFieldErrors()
                            .stream()
                            .map(fieldError -> fieldError.getField())
                            .collect(Collectors.toSet());


        Map<String , List<String>> errorsAndCauses = new HashMap<>();
        // empty arraylist
        errors.forEach(error -> {
            errorsAndCauses.put(error , new ArrayList<>());
        });


        exception.getBindingResult().getFieldErrors()
                .stream()
                .forEach(fieldError -> {
                    errorsAndCauses.get(fieldError.getField()).add(fieldError.getDefaultMessage());
                });

        body.put("variableNameAndValidationErrorType", errorsAndCauses);
        restErrorResponse.setAdditional(body);

        return new ResponseEntity<>(restErrorResponse, headers, status);

    }

}