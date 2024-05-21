package com.traini8.dtos;

import lombok.*;
import org.springframework.http.HttpStatus;


//This Class function is to Custom message to user if any error arises
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponseMessage {

    private String message;
    
    private boolean success;

    private HttpStatus status;

}
