
package com.Personagens.Herois;

import com.Exceptions.UltimateOnRechargeException;
import com.Personagens.Combatente;

public class Mago extends Heroi {

    private String nome;

    public Mago(int n) {

        super(n, Classe.MAGO);

        this.nome = "Mago";

        super.setVidaMaxima(80 + (n - 1) * 10);
        super.setVidaAtual(super.getVidaMaxima());

        super.setAtaque(15 + (n - 1) * 2);
        super.setDefesa(5 + (n - 1) * 1);
        super.setEsforco(12);
    }

    public void setNome(String nome) {

        if (nome == null || nome.trim().isEmpty()) {
            this.nome = "Mago";
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

            double dano = getAtaque() + 10;

            System.out.println(nome + " utilizou Bola de Fogo!");

            target.receberDano(dano);

            System.out.println(
                    "Bola de Fogo acertou " + target.getNome()
                            + " e causou " + dano + " de dano!");

            return dano;

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
