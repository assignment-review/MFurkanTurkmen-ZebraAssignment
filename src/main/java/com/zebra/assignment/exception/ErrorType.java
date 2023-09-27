package com.myjobsposting.myjobsposting.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {

    INTERNAL_SERVER_ERROR(1000,"An Unknown error has occurred on the server", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST_ERROR(1001,"Bad Request",HttpStatus.BAD_REQUEST),

    // ALREADY EXISTS
    MAIL_ALREADY_EXISTS(1101,"Mail Already Exists",HttpStatus.BAD_REQUEST),
    USERNAME_ALREADY_EXISTS(1102,"Username Already Exists",HttpStatus.BAD_REQUEST),

    // NOT FOUND
    ID_NOT_FOUND(1201,"Id Not Found",HttpStatus.BAD_REQUEST),
    MAIL_NOT_FOUND(1202,"Mail Not Found",HttpStatus.BAD_REQUEST),
    FCM_TOKEN_NOT_FOUND(1203,"FCM Token Not Found",HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1304,"User Not Found",HttpStatus.BAD_REQUEST),

    // CANNOT BE EMPTY
    USERNAME_CANNOT_BE_EMPTY(1401,"Username Cannot Be Empty",HttpStatus.BAD_REQUEST),
    EMAİL_CANNOT_BE_EMPTY(1402,"Email Cannot Be Empty",HttpStatus.BAD_REQUEST),
    NAME_CANNOT_BE_EMPTY(1401,"Name Cannot Be Empty",HttpStatus.BAD_REQUEST),
    SURNAME_CANNOT_BE_EMPTY(1401,"Surname Cannot Be Empty",HttpStatus.BAD_REQUEST),
    PASSWORD_CANNOT_BE_EMPTY(1401,"Password Cannot Be Empty",HttpStatus.BAD_REQUEST),

    // WRONG
    TOKEN_IS_WRONG(1501,"Token is wrong",HttpStatus.BAD_REQUEST),
    PASSWORD_IS_WRONG(1502,"Password is wrong",HttpStatus.BAD_REQUEST),
    EMAIL_IS_WRONG(1503,"Email Is Wrong",HttpStatus.BAD_REQUEST),
    CVTYPE_IS_WRONG(1504,"Please Upload Your Resume In Pdf Format.",HttpStatus.BAD_REQUEST),
    AVATARTYPE_IS_WRONG(1504,"Please Upload Your Avatar png,jpg,jpeg.",HttpStatus.BAD_REQUEST),

    // OTHER
    PASSWORDS_DONT_MATCH(1600,"Password And RePassword Don't Match",HttpStatus.BAD_REQUEST);

    int code;
    String message;
    HttpStatus httpStatus;
}