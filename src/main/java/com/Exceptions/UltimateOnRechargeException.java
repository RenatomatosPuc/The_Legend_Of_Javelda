package com.Exceptions;

public class UltimateOnRechargeException extends RuntimeException {
    public UltimateOnRechargeException() {
        super("A habilidade especial ainda esta em recarga");
    }

    public UltimateOnRechargeException(String mensagem) {
        super(mensagem);
    }
}
