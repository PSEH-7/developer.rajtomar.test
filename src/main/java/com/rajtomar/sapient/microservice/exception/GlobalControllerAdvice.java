package com.rajtomar.sapient.microservice.exception;

import com.rajtomar.sapient.microservice.response.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(NO_CONTENT)
    @ExceptionHandler({NoDataFoundException.class})
    public ErrorResponse handleException(NoDataFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setStatus(NO_CONTENT.getReasonPhrase());
        errorResponse.setStatusValue(NO_CONTENT.value());
        return errorResponse;
    }

}
