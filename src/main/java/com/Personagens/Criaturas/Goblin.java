package com.Personagens.Criaturas;

import com.Personagens.Combatente;

public class Goblin extends Criatura {

    public Goblin(String nome, int vidaMaxima, double defesa, double ataque, String rankPerigo, int velocidade,
            int nivel, int vidaAtual, boolean estaVivo) {
        super(nome, vidaMaxima, defesa, ataque, rankPerigo, velocidade, nivel, vidaAtual, estaVivo);
        // TODO Auto-generated constructor stub
    }

    public void setNome(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setNome'");
    }

    @Override
    public double atacar(Combatente target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atacar'");
    }

    @Override
    public void soltarLoot() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'soltarLoot'");
    }

}
