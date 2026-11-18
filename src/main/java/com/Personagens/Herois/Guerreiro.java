package com.Personagens.Herois;

import com.Exceptions.UltimateOnRechargeException;

public class Guerreiro extends Heroi {
    private String nome;

    // Construtor
    public Guerreiro(int n) {
        super(n, Classe.GUERREIRO);
        super.setVidaMaxima(150 + (n - 1) * 10);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    // Nao tem contexto em questao de lore do que faz.
    // (TEMPORARIO ATE O BALANCEAMENTO)
    @Override
    public double usarHabilidadeEspecial() {
        try {
            consumirEsforco();
            return 0;
        } catch (UltimateOnRechargeException e) {
            System.out.println("Erro:" + e.getMessage());
            return 0;
        }
    }
}
