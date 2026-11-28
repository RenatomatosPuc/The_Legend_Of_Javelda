
package com.Personagens.Criaturas;

import com.Personagens.Combatente;
import com.Personagens.Personagem;
import com.Exceptions.UltimateOnRechargeException;

public class LichNulo extends Criatura {

    public LichNulo() {

        super(
                "Nulien, o Lich Nulo",
                500,
                30,
                40,
                "SS",
                12,
                10,
                500,
                true);

        setEsforco(20);
    }

    // ATAQUE NORMAL
    @Override
    public double atacar(Personagem target) {

        if (target == null || !target.estaVivo()) {

            System.out.println("O Lich não encontrou um alvo.");

            return 0;
        }

        double dano = getAtaque();

        System.out.println(
                "\nNulien ergue sua mão e libera energia da morte em direção a " + target.getNome() + "!");

        System.out.println(
                "O Lich atacou " + target.getNome()
                        + " causando " + dano + " de dano!");

        target.receberDano(dano);

        return dano;
    }

    @Override
    public double usarHabilidadeEspecial(Combatente target) {

        if (target == null || !target.estaVivo()) {

            System.out.println("O Lich não encontrou um alvo.");

            return 0;
        }

        try {

            consumirEsforco();

            double dano = getAtaque() + 30;

            System.out.println("\n================================");
            System.out.println("       APAGAMENTO DA EXISTÊNCIA");
            System.out.println("================================");

            System.out.println(
                    "Nulien aponta para " + target.getNome() + ".");

            System.out.println(
                    "\"Você nunca deveria ter existido  não é você que faz as regras daqui.\"");

            target.receberDano(dano);

            System.out.println(
                    "O Vazio atingiu " + target.getNome()
                            + " causando " + dano + " de dano!");

            return dano;

        } catch (UltimateOnRechargeException e) {

            System.out.println("O Lich não possui esforço suficiente.");

            return 0;
        }
    }

    @Override
    public double getXp() {
        return 1000;
    }

    // RECOMPENSA
    @Override
    public void soltarLoot() {

        System.out.println("\nO Lich Nulo foi derrotado.");

        System.out.println("A equipe encontrou:");
        System.out.println("- Fragmento do Vazio");
        System.out.println("- Grimório de Nulien");
        System.out.println("- 1000 XP");
    }
}
