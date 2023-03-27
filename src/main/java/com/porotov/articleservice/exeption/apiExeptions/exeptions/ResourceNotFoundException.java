package com.porotov.articleservice.exeption.apiExeptions.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**@author Alexandr Porotov
 * @version  pre-alpha
 * */
@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
