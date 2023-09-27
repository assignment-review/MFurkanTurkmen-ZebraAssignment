package com.zebra.assignment.exception;

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
    BOOK_ALREADY_EXISTS(1101,"There is already a book for this ISBN",HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_EXISTS(1102,"Email is already exists",HttpStatus.BAD_REQUEST),
    // NOT FOUND
    BOOK_NOT_FOUND_ID(1201,"No book with this ID has been found.",HttpStatus.BAD_REQUEST),
    BOOK_NOT_FOUND_ISBN(1202,"No book with this ISBN has been found.",HttpStatus.BAD_REQUEST),
    BOOK_NOT_FOUND_ID_ISBN(1203,"No book with this ID or ISBN has been found.",HttpStatus.BAD_REQUEST),
    DEBT_NOT_FOUND(1204,"Debt not found",HttpStatus.BAD_REQUEST),
    BORROWER_NOT_FOUND(1205,"Member Not Found",HttpStatus.BAD_REQUEST),
    // CANNOT BE
    BOOK_CANNOT_BE_DELETED(1301,"book cannot be deleted because it is borrowed",HttpStatus.BAD_REQUEST);

    // OTHER


    int code;
    String message;
    HttpStatus httpStatus;
}