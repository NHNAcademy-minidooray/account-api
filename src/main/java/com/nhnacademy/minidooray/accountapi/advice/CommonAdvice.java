package com.nhnacademy.minidooray.accountapi.advice;

import com.nhnacademy.minidooray.accountapi.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class CommonAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.initDirectFieldAccess();
    }

    //400 Bad Request
    @ExceptionHandler({ValidationFailedException.class, MissingServletRequestParameterException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorMessage> missingParameter(Exception exception) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }

    //401 Unauthorized
    @ExceptionHandler(UnauthorizedAccountException.class)
    public ResponseEntity<ErrorMessage> unauthorizedUser(Exception exception) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.UNAUTHORIZED.value(), exception.getMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.UNAUTHORIZED);
    }

    //403  Forbidden
    @ExceptionHandler(UnauthorizedAdminException.class)
    public ResponseEntity<ErrorMessage> invalidEventOwner(Exception exception) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.FORBIDDEN.value(), exception.getMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.FORBIDDEN);
    }

    //404 Not Found
    @ExceptionHandler({AccountNotFoundException.class, StatusNotFoundException.class, NoHandlerFoundException.class})
    public ResponseEntity<ErrorMessage> resourceNotFoundException(Exception exception) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }

    //405 Method Not Allowed
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ErrorMessage> methodNotAllowed(Exception exception, HttpServletRequest request){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.METHOD_NOT_ALLOWED.value(), exception.getMessage(), request.getRequestURI() );
        return new ResponseEntity<>(errorMessage,HttpStatus.METHOD_NOT_ALLOWED);
    }

    // 409 Conflict
    @ExceptionHandler(AccountExistsException.class)
    public ResponseEntity<ErrorMessage> existsException(Exception exception) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.CONFLICT.value(), exception.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

    //500 Internal Server Error
    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<ErrorMessage>  internalServerException(Exception exception) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}