package com.Personagens;

public interface Combatente {
    String getNome();

    // Guilda getGuilda();

    double getVidaAtual();

    double getVidaMaxima();

    double getAtaque();

    double getDefesa();

    boolean estaVivo();

    double receberDano(double danoRecebido);

    // Arma getArmamento();

    // Armadura getArmadura();

    // Batalha Acao();
}
