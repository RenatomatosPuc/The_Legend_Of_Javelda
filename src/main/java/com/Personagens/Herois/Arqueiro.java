package com.Personagens.Herois;

import com.Exceptions.UltimateOnRechargeException;
import com.Personagens.Criaturas.Criatura;

public class Arqueiro extends Heroi {
    private String nome;

    public Arqueiro(int n) {
        super(n, Classe.ARQUEIRO);
        super.setVidaMaxima(100 + (n - 1) * 10);
        super.setVidaAtual(super.getVidaMaxima());
        super.setEsforco(10);
    }

    // Nao tem contexto em questao de lore do que faz.
    // (TEMPORARIO ATE O BALANCEAMENTO)
    @Override
    public double usarHabilidadeEspecial(Criatura target) {
        try {
            consumirEsforco();
            return 0;
        } catch (UltimateOnRechargeException e) {
            System.out.println("Erro:" + e.getMessage());
            return 0;
        }
    }

}
