
package com.Personagens;

public interface Combatente {

    String getNome();

    // ATRIBUTOS
    double getVidaAtual();

    double getVidaMaxima();

    double getAtaque();

    double getDefesa();

    int getVelocidade();

    int getNivel();

    int getEsforco();


    boolean estaVivo();


    double receberDano(double danoRecebido);

    double atacar(Combatente target);


    double usarHabilidadeEspecial(Combatente target);


    default double ataqueEspecial(Combatente target) {
        return usarHabilidadeEspecial(target);
    }
}
