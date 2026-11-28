
package com.Personagens.Criaturas;

import com.Personagens.Combatente;
import com.Exceptions.UltimateOnRechargeException;

public class Esqueleto extends Criatura {

    public Esqueleto(
            String nome,
            int vidaMaxima,
            double defesa,
            double ataque,
            String rankPerigo,
            int velocidade,
            int nivel,
            int vidaAtual,
            boolean estaVivo) {

        super(
                nome,
                vidaMaxima,
                defesa,
                ataque,
                rankPerigo,
                velocidade,
                nivel,
                vidaAtual,
                estaVivo);

        setEsforco(6);
    }

    public Esqueleto(String nome, int nivel) {

        super(
                nome,
                80 + (nivel - 1) * 10,
                10 + (nivel - 1) * 2,
                15 + (nivel - 1) * 3,
                "C",
                8,
                nivel,
                80 + (nivel - 1) * 10,
                true);

        setEsforco(6);
    }

    public Esqueleto(int nivel) {

        super(
                "Esqueleto",
                80 + (nivel - 1) * 10,
                10 + (nivel - 1) * 2,
                15 + (nivel - 1) * 3,
                "C",
                8,
                nivel,
                80 + (nivel - 1) * 10,
                true);

        setEsforco(6);
    }

    @Override
    public double atacar(Combatente target) {

        if (target == null || !target.estaVivo()) {

            System.out.println(
                    "O Esqueleto tentou atacar, mas não encontrou um alvo!");

            return 0;
        }

        double dano = getAtaque();

        System.out.println(
                "O Esqueleto " + getNome() +
                        " atacou " + target.getNome() +
                        " causando " + dano + " de dano!");

        target.receberDano(dano);

        return dano;
    }

    @Override
    public double usarHabilidadeEspecial(Combatente target) {

        if (target == null || !target.estaVivo()) {

            System.out.println(
                    "O Esqueleto tentou usar sua habilidade, mas não encontrou um alvo!");

            return 0;
        }

        try {

            consumirEsforco();

            double dano = getAtaque() + 5;

            System.out.println(
                    "O Esqueleto " + getNome() +
                            " utilizou sua habilidade especial contra " +
                            target.getNome() + "!");

            target.receberDano(dano);

            System.out.println(
                    "O ataque causou " + dano + " de dano!");

            return dano;

        } catch (UltimateOnRechargeException e) {

            System.out.println(
                    "O Esqueleto está sem esforço para utilizar sua habilidade!");

            return 0;
        }
    }

    @Override
    public void soltarLoot() {

        System.out.println(
                "O Esqueleto soltou um item!");
    }
}
