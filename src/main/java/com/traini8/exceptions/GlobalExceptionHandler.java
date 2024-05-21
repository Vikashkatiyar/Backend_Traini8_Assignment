package com.traini8.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.traini8.dtos.ApiResponseMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Whenever a exception arises in our program the springboot will look for those Exception if they are defined here .
//@RestControllerAdvice helps springboot to identify where exception will be defined .

@RestControllerAdvice
public class GlobalExceptionHandler {

    Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //@ExceptionHandler helps to trigger the specific exception that has occured
    //MethodArgumentNotValidException will be thrown  when the method arguments fail validation.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String , Object>> methodArgumentNotValidExceptionhandler(MethodArgumentNotValidException ex)
    {
        List<ObjectError> allErrors=ex.getBindingResult().getAllErrors(); //This method return all error in a method as a list.
        Map<String,Object> response=new HashMap<>();
        allErrors.stream().forEach(objectError->{
            String message=objectError.getDefaultMessage();
            String field=((FieldError) objectError).getField();
            response.put(field,message);
        });

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    //ResourceNotFoundException exception will occur when the TrainingCenter data cannot be found in database
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseMessage> ResourceNotFoundExceptionalHandler(ResourceNotFoundException ex)
    {
        logger.info(ex.getMessage());
        ApiResponseMessage apiMessage= ApiResponseMessage.builder().message(ex.getMessage()).success(false).status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<ApiResponseMessage>(apiMessage,HttpStatus.NOT_FOUND);
    }

}
