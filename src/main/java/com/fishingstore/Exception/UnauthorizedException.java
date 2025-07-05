package com.fishingstore.Exception;

public class UnauthorizedException extends Exception {

    String message = "Username or password is already exist";

    public UnauthorizedException(String message) {
        super(message);
    }
}
