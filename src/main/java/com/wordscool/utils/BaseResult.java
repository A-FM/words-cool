package com.wordscool.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResult implements Serializable {
    private static final long serialVersionUID = 3221544472113629276L;


    private HttpStatus httpStatus;
    private String  message;
    private Object object;

}
