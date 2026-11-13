package com.Personagens.Herois;

import com.Exceptions.UltimateOnRechargeException;

public class Arqueiro extends Heroi {
    public Arqueiro(int n) {
        super(n, Classe.ARQUEIRO);
    }

    // Habilidade especial da o dobro de Dano, nao tem contexto em questao de lore
    // do que faz.
    // (TEMPORARIO ATE O BALANCEAMENTO)
    @Override
    public double usarHabilidadeEspecial() {
        try {
            return 0;
        } catch (UltimateOnRechargeException e) {
            System.out.println("Erro:" + e.getMessage());
            return 0;
        }
    }

}
