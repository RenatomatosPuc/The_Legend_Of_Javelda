package com.Batalha;

public enum Dificuldade {
    FACIL(1),
    MEDIO(2),
    DIFICIL(3);

    private final int valor;

    Dificuldade(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
