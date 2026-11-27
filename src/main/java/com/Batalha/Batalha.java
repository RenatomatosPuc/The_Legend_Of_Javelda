
package com.Batalha;

import com.Personagens.Criaturas.Criatura;
import com.Personagens.Herois.Heroi;

import java.util.ArrayList;
import java.util.List;

public class Batalha {

    private Party partyOriginal;
    private List<Heroi> party;
    private List<Criatura> criaturas;

    private double xpTotal;

    // CONSTRUTOR COM PARTY
    public Batalha(Party party, List<Criatura> criaturas) {

        if (party == null || criaturas == null) {
            throw new IllegalArgumentException(
                "Party e criaturas não podem ser nulas."
            );
        }

        this.partyOriginal = party;
        this.party = party.getHerois();
        this.criaturas = new ArrayList<>(criaturas);
        this.xpTotal = 0;
    }

    // CONSTRUTOR PARA COMPATIBILIDADE COM LISTAS ANTIGAS
    public Batalha(List<Heroi> party, List<Criatura> criaturas) {

        if (party == null || criaturas == null) {
            throw new IllegalArgumentException(
                "Party e criaturas não podem ser nulas."
            );
        }

        this.partyOriginal = null;
        this.party = party;
        this.criaturas = new ArrayList<>(criaturas);
        this.xpTotal = 0;
    }

    // INICIAR BATALHA
    public void iniciarBatalha() {

        if (party.isEmpty()) {
            System.out.println("Não existem heróis para lutar!");
            return;
        }

        if (criaturas.isEmpty()) {
            System.out.println("Não existem criaturas para enfrentar!");
            return;
        }

        System.out.println("\n================================");
        System.out.println("        A BATALHA COMEÇOU!");
        System.out.println("================================");

        while (!party.isEmpty() && !criaturas.isEmpty()) {

            // TURNO DOS HERÓIS
            System.out.println("\n========== TURNO DOS HERÓIS ==========");

            for (Heroi heroi : new ArrayList<>(party)) {

                if (criaturas.isEmpty()) {
                    break;
                }

                if (!heroi.estaVivo()) {
                    party.remove(heroi);
                    continue;
                }

                Criatura criatura = criaturas.get(0);

                heroi.atacar(criatura);

                // VERIFICAR MORTE DA CRIATURA
                if (!criatura.estaVivo()) {

                    System.out.println(
                        "\n" + criatura.getNome() + " foi derrotada!"
                    );

                    // XP CONCEDIDO PELA CRIATURA
                    double xpRecebido = criatura.getXp();

                    xpTotal += xpRecebido;

                    System.out.println(
                        "Experiência obtida: " + xpRecebido + " XP"
                    );

                    // ENTREGA DO LOOT
                    criatura.soltarLoot();

                    criaturas.remove(criatura);
                }
            }

         
            if (criaturas.isEmpty() || party.isEmpty()) {
                break;
            }

            System.out.println("\n========== TURNO DAS CRIATURAS ==========");

            for (Criatura criatura : new ArrayList<>(criaturas)) {

                if (party.isEmpty()) {
                    break;
                }

                Heroi heroi = party.get(0);

                criatura.atacar(heroi);

          
                if (!heroi.estaVivo()) {

                    System.out.println(
                        "\n" + heroi.getNome() + " foi derrotado!"
                    );

                    party.remove(heroi);
                }
            }
        }

        // RESULTADO DA BATALHA
        System.out.println("\n================================");

        if (party.isEmpty()) {

            System.out.println("A EQUIPE FOI DERROTADA!");

            System.out.println(
                "As criaturas continuam ameaçando Javária."
            );

        } else {

            System.out.println("A EQUIPE VENCEU A BATALHA!");

            System.out.println(
                "Os sobreviventes conseguiram derrotar os inimigos."
            );

            distribuirExperiencia();
        }

        System.out.println("================================");
    }

    // DISTRIBUIR XP
    private void distribuirExperiencia() {

        System.out.println("\n========== RECOMPENSA DE XP ==========");

        if (xpTotal <= 0) {

            System.out.println(
                "Nenhuma experiência foi concedida pelas criaturas."
            );

            return;
        }

        if (partyOriginal != null) {

            // Cada herói vivo recebe o XP integral
            partyOriginal.ganharExperienciaGrupo(xpTotal);

        } else {

            for (Heroi heroi : party) {

                if (heroi.estaVivo()) {
                    heroi.ganharExperiencia(xpTotal);
                }
            }

            System.out.println(
                "Cada herói sobrevivente recebeu "
                + xpTotal + " XP."
            );
        }

        System.out.println("XP total obtido dos inimigos: " + xpTotal);
    }
}
