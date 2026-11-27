package com.Personagens.Criaturas;

import com.Personagens.Combatente;

public class Lich_Null extends Criatura {

    // falta corrigir o construtor
    public Lich_Null() {
        super("Lich Nulo", 1000, 100, 100, "Perigo", 30, 20, 1000, true);
    }

    // falta implementar o ataque do Lich Nulo
    @Override
    public double atacar(Combatente target) {
        return 0;
    }

    public double getXpConcedido() {
        return getNivel() * 100; // Exemplo: XP concedido é 100 vezes o nível do Lich Nulo
    }

    @Override
    public void soltarLoot() {
        System.out.println("O Lich Nulo soltou um [Fragmento Negro]! XP Concedido: " + getXpConcedido());
    }

}
