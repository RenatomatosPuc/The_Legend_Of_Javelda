
package com.Personagens.Herois;

import com.Personagens.Combatente;
import com.Exceptions.UltimateOnRechargeException;

public class Guerreiro extends Heroi {

    private String nome;

    public Guerreiro(int n) {

        super(n, Classe.GUERREIRO);

        this.nome = "Guerreiro";

        super.setVidaMaxima(150 + (n - 1) * 10);
        super.setVidaAtual(super.getVidaMaxima());

        super.setAtaque(20 + (n - 1) * 2);
        super.setDefesa(10 + (n - 1) * 1);
        super.setEsforco(12);
    }

    public void setNome(String nome) {

        if (nome == null || nome.trim().isEmpty()) {
            this.nome = "Guerreiro";
        } else {
            this.nome = nome.trim();
        }
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public double usarHabilidadeEspecial(Combatente target) {

        if (target == null || !target.estaVivo()) {
            System.out.println("Alvo não encontrado!");
            return 0;
        }

        try {

            consumirEsforco();

            System.out.println(nome + " utilizou sua habilidade especial!");

            return 0;

        } catch (UltimateOnRechargeException e) {

            System.out.println("Erro: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public double ataqueEspecial(Combatente target) {
        return usarHabilidadeEspecial(target);
    }

}
