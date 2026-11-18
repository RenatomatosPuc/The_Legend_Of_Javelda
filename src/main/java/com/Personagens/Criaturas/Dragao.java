package com.Personagens.Criaturas;

import com.Personagens.Personagem;

public class Dragao extends Criatura {

    public Dragao() {
        super("Dragao Anderson", 200, 100, 90, "S", 20);
    }

    public void ataque(Personagem target) {
        System.out.println("fogos de operacoes em java");
        target.receberDano(getAtaque() / 2);
    }

    public void soltarLoot() {
        System.out.println("soltou olho de Dragao");
    }
}
