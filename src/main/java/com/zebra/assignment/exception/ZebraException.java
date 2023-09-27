package com.myjobsposting.myjobsposting.exception;

import lombok.Getter;


@Getter
public class MyJobsPostingException extends RuntimeException {
    private final ErrorType errorType;
    public MyJobsPostingException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public MyJobsPostingException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }


}
