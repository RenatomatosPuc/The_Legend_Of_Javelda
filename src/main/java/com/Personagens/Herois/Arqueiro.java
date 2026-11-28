
package com.Personagens.Herois;

import com.Exceptions.UltimateOnRechargeException;
import com.Personagens.Combatente;

public class Arqueiro extends Heroi {

    private String nome;

    public Arqueiro(int n) {

        super(n, Classe.ARQUEIRO);

        this.nome = "Arqueiro";

        super.setVidaMaxima(100 + (n - 1) * 10);
        super.setVidaAtual(super.getVidaMaxima());

        super.setAtaque(15 + (n - 1) * 2);
        super.setDefesa(5 + (n - 1) * 1);
        super.setEsforco(10);
        this.nome = nome;
    }

    public void setNome(String nome) {

        if (nome == null || nome.trim().isEmpty()) {
            this.nome = "Arqueiro";
        } else {
            this.nome = nome.trim();
        }
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    // Habilidade especial
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

    // Método exigido pela interface Combatente
    @Override
    public double ataqueEspecial(Combatente target) {
        return usarHabilidadeEspecial(target);
    }
}
