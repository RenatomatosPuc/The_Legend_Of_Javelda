package com.Personagens.Criaturas;

import com.Personagens.Combatente;
import com.Personagens.Personagem;

public abstract class Criatura extends Personagem {

    private String rankPerigo;
    private double xpConcedido;

    public Criatura(String nome, int vidaMaxima, double defesa, double ataque, String rankPerigo, int velocidade,
            int nivel, int vidaAtual, boolean estaVivo) {
        super(nome, vidaMaxima, defesa, ataque, velocidade, nivel, 5, 0, vidaAtual, estaVivo);
        this.rankPerigo = rankPerigo;
    }

    public Criatura(String nome, int vidaMaxima, double defesa, double ataque, String rankPerigo, int velocidade,int esforco, int experiencia,
            int nivel, int vidaAtual, boolean estaVivo) {
        super(nome, vidaMaxima, defesa, ataque, velocidade, nivel, esforco, experiencia, vidaAtual, estaVivo);
        this.rankPerigo = rankPerigo;
        this.xpConcedido = 0;
    }


    public double getXp() {
        return this.xpConcedido;
    }

    protected boolean consumirEsforco() {
        if (getEsforco() <= 0) {
            System.out.println("Esforco insuficiente: ataque especial cancelado.");
            return false;
        }

        setEsforco(getEsforco() - 1);
        return true;
    }

    public abstract void soltarLoot();


    public String getRankPerigo() {
        return this.rankPerigo;
    }

    public double ataqueEspecial(Combatente target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ataqueEspecial'");
    }
}
