package com.Personagens.Herois;

import com.Personagens.Combatente;
import com.Exceptions.UltimateOnRechargeException;

public class Clerigo extends Heroi {
    private String nome;

    public Clerigo(int n, String nome) {
        super(n, Classe.CLERIGO);
        super.setVidaMaxima(120 + (n - 1) * 10);
        super.setAtaque(10 + (n - 1) * 2);
        super.setDefesa(8 + (n - 1) * 1);
        super.setEsforco(8);
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    // Nao tem contexto em questao de lore do que faz.
    // (TEMPORARIO ATE O BALANCEAMENTO)
    @Override
    public double usarHabilidadeEspecial(Combatente target) {
        try {
            consumirEsforco();
            return 0;
        } catch (UltimateOnRechargeException e) {
            System.out.println("Erro:" + e.getMessage());
            return 0;
        }
    }

}
