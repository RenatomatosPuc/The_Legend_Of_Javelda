package com.Personagens.Criaturas;

import com.Personagens.Personagem;

public abstract class Criatura extends Personagem {

<<<<<<< Updated upstream
=======
private String RankPerigo;


public Criatura(String nome, int vida, double ataque, String RankPerigo, int  nivel) {
    super(nome,vida,ataque,nivel);
    this.RankPerigo = RankPerigo;
}
public abstract void soltarLoot();
>>>>>>> Stashed changes
}
