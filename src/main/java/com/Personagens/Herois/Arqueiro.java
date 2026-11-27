package com.Personagens.Herois;

import com.Personagens.Criaturas.Criatura;
import com.Personagens.Personagem;
import com.Exceptions.UltimateOnRechargeException;
import com.Personagens.Combatente;
import com.Arsenal.InventarioPersonagem;

public class Arqueiro extends Heroi {
    private String nome;

    public Arqueiro(int n, String nome) {
        super(n, Classe.ARQUEIRO);
        super.setVidaMaxima(100 + (n - 1) * 10);
        super.setVidaAtual(super.getVidaMaxima());
        super.setAtaque(15 + (n - 1) * 2);
        super.setDefesa(5 + (n - 1) * 1);
        super.setEsforco(10);
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

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
