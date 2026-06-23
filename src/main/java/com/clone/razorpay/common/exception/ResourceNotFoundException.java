package com.clone.razorpay.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Resource not found in database")
@Getter
public class ResourceNotFoundException extends RuntimeException{
    private final String resourceName;
    private final Object identifier;

    public ResourceNotFoundException(String resourceName, Object identifier){
        super(resourceName + " not found: " + identifier);
        this.resourceName = resourceName;
        this.identifier = identifier;
    }
}
