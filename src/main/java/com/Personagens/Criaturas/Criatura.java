package com.Personagens.Criaturas;

import com.Personagens.Personagem;

public abstract class Criatura extends Personagem {

private String RankPerigo;


public Criatura(String nome, int vida, int forca, String RankPerigo, int  nivel) {
    super(nome,vida,forca,nivel);
    this.RankPerigo = RankPerigo;
}
public abstract void soltarLoot();
}
