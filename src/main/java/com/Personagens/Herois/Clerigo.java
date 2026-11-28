
package com.Personagens.Herois;

import com.Personagens.Combatente;
import com.Exceptions.UltimateOnRechargeException;

public class Clerigo extends Heroi {

    private String nome;

    public Clerigo(int n) {

        super(n, Classe.CLERIGO);

        this.nome = "Clerigo";

        super.setVidaMaxima(120 + (n - 1) * 10);
        super.setVidaAtual(super.getVidaMaxima());

        super.setAtaque(10 + (n - 1) * 2);
        super.setDefesa(8 + (n - 1) * 1);
        super.setEsforco(8);
    }

    public void setNome(String nome) {

        if (nome == null || nome.trim().isEmpty()) {
            this.nome = "Clerigo";
        } else {
            this.nome = nome.trim();
        }
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    // Habilidade especial provisória
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
