package com.example.userManagement.exption;


import com.example.userManagement.model.Error;
import com.example.userManagement.model.ErrorResponse;
import com.example.userManagement.model.Request;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {

        Error error = new Error();
        error.setCode(HttpStatus.NOT_FOUND.getReasonPhrase());
        error.setDescription(ex.getMessage());
        error.setAdditionalInfo(ex.getLocalizedMessage());

        Request req = new Request();
        req.setQueryString(((ServletWebRequest) request).getRequest().getQueryString());
        req.setUri(String.valueOf(((ServletWebRequest) request).getRequest().getRequestURL()));
        req.setMethod(Request.MethodEnum.valueOf(((ServletWebRequest) request).getRequest().getMethod()));

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(error);
        errorResponse.setRequest(req);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(WebRequest request) {

        Error error = new Error();
        error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        error.setDescription("Something went wrong!");
        error.setAdditionalInfo("Internal Server Error. This might be happen because of some external dependency isn't working right now.");


        Request req = new Request();
        req.setQueryString(((ServletWebRequest) request).getRequest().getQueryString());
        req.setUri(String.valueOf(((ServletWebRequest) request).getRequest().getRequestURL()));
        req.setMethod(Request.MethodEnum.valueOf(((ServletWebRequest) request).getRequest().getMethod()));

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(error);
        errorResponse.setRequest(req);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
