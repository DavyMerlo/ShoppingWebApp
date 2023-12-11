package com.davy.restapi.shared.exceptions;

import org.springframework.http.HttpStatus;

public final class ThrowException {
    public static void objectByIdException(Long id, String objName){
        throw new ApplicationException(
                objName + " not found",
                String.format(objName + " with id=%d not found", id),
                HttpStatus.NOT_FOUND,
                null
        );
    }

    public static void objectException(String objName){
        throw new ApplicationException(
                objName + " not found",
                String.format(objName + " not found"),
                HttpStatus.NOT_FOUND,
                null
        );
    }

    public static void passWordException(String message){
        throw new ApplicationException(
                message,
                String.format(message),
                HttpStatus.NOT_FOUND,
                null
        );
    }
}
