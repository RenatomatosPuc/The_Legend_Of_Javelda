package com.Personagens;

public interface Combatente {
    String getNome();

    double getVidaAtual();

    double getVidaMaxima();

    double getAtaque();

    double getDefesa();

    boolean estaVivo();

    double atacar();

    double receberDano(double danoRecebido);
}
