
package com.Personagens.Criaturas;

import com.Personagens.Combatente;
import com.Personagens.Personagem;
import com.Exceptions.UltimateOnRechargeException;

public class Goblin extends Criatura {

    public Goblin(int nivel) {

        super(
                "Goblin",
                50 + (nivel - 1) * 5,
                5 + (nivel - 1) * 1,
                10 + (nivel - 1) * 2,
                "Comum",
                7 + (nivel - 1) * 1,
                nivel,
                50 + (nivel - 1) * 5,
                true);

        setEsforco(4);
    }

    // ATAQUE NORMAL
    @Override
    public double atacar(Personagem target) {

        if (target == null || !target.estaVivo()) {

            System.out.println(
                    "O Goblin tentou atacar, mas não encontrou um alvo!");

            return 0;
        }

        double dano = getAtaque();

        System.out.println(
                "O Goblin atacou " + target.getNome() +
                        " causando " + dano + " de dano!");

        target.receberDano(dano);

        return dano;
    }

    // HABILIDADE ESPECIAL
    @Override
    public double usarHabilidadeEspecial(Combatente target) {

        if (target == null || !target.estaVivo()) {

            System.out.println(
                    "O Goblin tentou usar sua habilidade, mas não encontrou um alvo!");

            return 0;
        }

        try {

            consumirEsforco();

            double dano = getAtaque() + 5;

            System.out.println(
                    "O Goblin utilizou seu ataque especial contra " +
                            target.getNome() + "!");

            target.receberDano(dano);

            System.out.println(
                    "O ataque causou " + dano + " de dano!");

            return dano;

        } catch (UltimateOnRechargeException e) {

            System.out.println(
                    "O Goblin está sem esforço para utilizar sua habilidade!");

            return 0;
        }
    }

    // LOOT
    @Override
    public void soltarLoot() {

        System.out.println(
                "O Goblin deixou cair um item comum!");
    }
}
