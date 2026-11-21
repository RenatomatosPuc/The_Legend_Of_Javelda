package com.Personagens;

import com.Arsenal.InventarioPersonagem;
import com.Personagens.Criaturas.Criatura;

public interface Combatente {
    // Identificação e Estatísticas Base
    String getNome();

    double getVidaAtual();

    double getVidaMaxima();

    double getAtaque();

    double getDefesa();

    int getVelocidade();

    int getNivel();

    int getEsforco();

    boolean estaVivo();

    double receberDano(double danoRecebido);

    double usarHabilidadeEspecial(Combatente target);

    double atacar(Combatente target);

    double usarHabilidadeEspecial(Criatura target);

    double ataqueEspecial(Combatente target);
}