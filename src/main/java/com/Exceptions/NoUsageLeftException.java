package com.Exceptions;

public class NoUsageLeftException extends RuntimeException {

    public NoUsageLeftException() {
        System.out.println("Os usos do utilitario acabaram.");
    }

}