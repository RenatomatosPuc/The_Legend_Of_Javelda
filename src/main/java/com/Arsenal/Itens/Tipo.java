package com.Arsenal.Itens;

public enum Tipo {
    ATAQUE(1),
    CURA(2);

    private final int valor;

    Tipo(int valor) {
        this.valor = valor;
    }
}
