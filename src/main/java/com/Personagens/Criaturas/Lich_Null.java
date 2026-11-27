package com.Personagens.Criaturas;

import com.Personagens.Combatente;

public class Lich_Null extends Criatura {

    // falta corrigir o construtor
    public Lich_Null() {
        super("Lich Nulo", 0, 0, 0, "S", 0, 0, 0, 0, 0, true);
    }

    // falta implementar o ataque do Lich Nulo
    @Override
    public double atacar(Combatente target) {
        return 0;
    }

    @Override
    public void soltarLoot() {
        System.out.println("O Lich Nulo soltou um [Fragmento Negro]! XP Concedido: " + getXpConcedido());
    }

}
