
package com.Personagens.Criaturas;

import com.Personagens.Combatente;
import com.Exceptions.UltimateOnRechargeException;

public class Dragao extends Criatura {

    public Dragao(
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

        setEsforco(8);
    }

    public Dragao(String nome, int nivel) {

        super(
                nome,
                300 + (nivel - 1) * 50,
                25 + (nivel - 1) * 5,
                35 + (nivel - 1) * 6,
                "S",
                9,
                nivel,
                300 + (nivel - 1) * 50,
                true);

        setEsforco(8);
    }

    @Override
    public double atacar(Combatente target) {

        if (target == null || !target.estaVivo()) {

            System.out.println(
                    "O Dragão tentou morder, mas não havia um alvo válido!");

            return 0;
        }

        double danoCausado = getAtaque();

        System.out.println(
                "O Dragão " + getNome() +
                        " mordeu " + target.getNome() +
                        " causando " + danoCausado + " de dano!");

        target.receberDano(danoCausado);

        return danoCausado;
    }

    // HABILIDADE ESPECIAL
    @Override
    public double usarHabilidadeEspecial(Combatente target) {

        if (target == null || !target.estaVivo()) {

            System.out.println(
                    "O Dragão soprou, mas não havia um alvo válido!");

            return 0;
        }

        try {

            consumirEsforco();

            double danoSopro = getAtaque() + 20;

            System.out.println(
                    "O Dragão " + getNome() +
                            " lançou um SOPRO DE JAVA em " +
                            target.getNome() +
                            " causando " + danoSopro + " de dano!");

            target.receberDano(danoSopro);

            return danoSopro;

        } catch (UltimateOnRechargeException e) {

            System.out.println(
                    "O Dragão tentou usar o sopro, mas está sem esforço!");

            return 0;
        }
    }

    public double getXpConcedido() {
        return getNivel() * 15; // Exemplo: XP concedido é 15 vezes o nível do Dragão
    }

    // LOOT

    @Override
    public void soltarLoot() {

        System.out.println(
                "O Dragão soltou um [Olho de Dragão]! XP Concedido: "
                        + getXp());
    }
}
