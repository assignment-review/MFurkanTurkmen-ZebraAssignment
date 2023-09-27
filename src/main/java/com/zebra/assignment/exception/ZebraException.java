package com.zebra.assignment.exception;

import lombok.Getter;


@Getter
public class ZebraException extends RuntimeException {
    private final ErrorType errorType;
    public ZebraException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public ZebraException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }


}
