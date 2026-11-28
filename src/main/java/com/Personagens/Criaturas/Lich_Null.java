
package com.Personagens.Criaturas;

import com.Personagens.Combatente;
<<<<<<< HEAD
import com.Exceptions.UltimateOnRechargeException;
=======

public class Lich_Null extends Criatura {

    // falta corrigir o construtor
    public Lich_Null() {
        super("Lich Nulo", 1000, 100, 100, "Perigo", 30, 20, 1000, true);
    }

    // falta implementar o ataque do Lich Nulo
    @Override
    public double atacar(Combatente target) {
        return 0;
    }

    public double getXpConcedido() {
        return getNivel() * 100; // Exemplo: XP concedido é 100 vezes o nível do Lich Nulo
    }

    @Override
    public void soltarLoot() {
        System.out.println("O Lich Nulo soltou um [Fragmento Negro]! XP Concedido: " + getXpConcedido());
    }
>>>>>>> 48d4b1c0ffd70b5cbae3ffcb1acbed9062b78479

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
            true
        );

        setEsforco(20);
    }

    // ATAQUE NORMAL
    @Override
    public double atacar(Combatente target) {

        if (target == null || !target.estaVivo()) {

            System.out.println("O Lich não encontrou um alvo.");

            return 0;
        }

        double dano = getAtaque();

        System.out.println(
            "\nNulien ergue sua mão e libera energia da morte em direção a " + target.getNome() + "!"
        );

        System.out.println(
            "O Lich atacou " + target.getNome()
            + " causando " + dano + " de dano!"
        );

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
                "Nulien aponta para " + target.getNome() + "."
            );

            System.out.println(
                "\"Você nunca deveria ter existido  não é você que faz as regras daqui.\""
            );

            target.receberDano(dano);

            System.out.println(
                "O Vazio atingiu " + target.getNome()
                + " causando " + dano + " de dano!"
            );

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
