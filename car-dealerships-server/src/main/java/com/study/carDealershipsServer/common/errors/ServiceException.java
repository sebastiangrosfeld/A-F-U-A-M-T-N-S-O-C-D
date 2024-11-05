package com.study.carDealershipsServer.common.errors;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ServiceException extends RuntimeException{

    private HttpStatus status;

    public ServiceException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
