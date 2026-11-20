package com.Personagens;

import com.Personagens.Criaturas.Criatura;

public interface Combatente {
    String getNome();

    double getVidaAtual();

    double getVidaMaxima();

    double getAtaque();

    double getDefesa();

    boolean estaVivo();

    double atacar(Personagem target);

    double receberDano(double danoRecebido);

    int getNivel();

    int getEsforco();

    double usarHabilidadeEspecial(Criatura target);
}
