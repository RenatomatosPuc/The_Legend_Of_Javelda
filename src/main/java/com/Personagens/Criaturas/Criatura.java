package com.Personagens.Criaturas;

import com.Personagens.Personagem;

public abstract class Criatura extends Personagem {

private String rankPerigo;


public Criatura(String nome, int vidaMaxima, double defesa, double ataque, String rankPerigo, int velocidade, int nivel, int vidaAtual, boolean estaVivo) {
    super(nome, vidaMaxima, defesa, ataque, velocidade, nivel, vidaAtual, estaVivo);
    this.rankPerigo = rankPerigo;
}
public abstract void soltarLoot();

}
