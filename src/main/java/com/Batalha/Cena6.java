
package com.Batalha;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.Personagens.Criaturas.Criatura;
import com.Personagens.Criaturas.Dragao;

public class Cena6 {

    public void iniciar(Party party, Scanner scanner) {

        if (party == null || party.getHerois().isEmpty()) {
            System.out.println("Não existem heróis para continuar.");
            return;
        }

        System.out.println("\n==============================================");
        System.out.println("          CENA 6: O GUARDIÃO DO ABISMO");
        System.out.println("==============================================");

        System.out.println("\nA grande porta se abre.");

        System.out.println("Do outro lado existe uma câmara gigantesca.");
        System.out.println("Correntes negras atravessam o teto.");
        System.out.println("No centro, uma criatura permanece imóvel.");

        System.out.println("\nUm enorme Dragão.");

        System.out.println("Suas escamas estão cobertas por inscrições.");
        System.out.println("Seus olhos brilham como brasas apagadas.");

        System.out.println("\nAo perceber a presença dos aventureiros,");
        System.out.println("a criatura levanta lentamente a cabeça.");

        System.out.println("\nDragão:");
        System.out.println("\"Mais nomes para o esquecimento.\"");

        System.out.println("\"Já não me lembro de quantos");
        System.out.println("tentaram atravessar esta porta.\"");

        System.out.println("\nO fragmento negro começa a brilhar.");

        System.out.println("As correntes se rompem.");

        System.out.println("\nO guardião desperta!");

        // DIFICULDADE DO CHEFE

        System.out.println("\n==============================================");
        System.out.println("           NÍVEL DO GUARDIÃO");
        System.out.println("==============================================");

        System.out.println("1 - Guardião enfraquecido");
        System.out.println("2 - Guardião desperto");
        System.out.println("3 - Guardião corrompido");

        int escolha = lerEscolha(scanner, 1, 3);

        int nivel;
        int vida;
        double defesa;
        double ataque;

        switch (escolha) {

            case 1:

                nivel = 1;
                vida = 180;
                defesa = 12;
                ataque = 22;

                break;

            case 2:

                nivel = 2;
                vida = 240;
                defesa = 18;
                ataque = 30;

                break;

            default:

                nivel = 3;
                vida = 320;
                defesa = 24;
                ataque = 38;

                break;
        }

        // CRIAÇÃO DO DRAGÃO
        Dragao dragao = new Dragao(
            "Vharz, o Guardião Esquecido",
            vida,
            defesa,
            ataque,
            "S",
            9,
            nivel,
            vida,
            true
        );

        List<Criatura> criaturas = new ArrayList<>();

        criaturas.add(dragao);

        System.out.println("\n==============================================");
        System.out.println("                 CHEFE");
        System.out.println("==============================================");

        System.out.println("VHARZ, O GUARDIÃO ESQUECIDO");

        System.out.println("Nível: " + nivel);
        System.out.println("Vida: " + dragao.getVidaAtual());
        System.out.println("Ataque: " + dragao.getAtaque());
        System.out.println("Defesa: " + dragao.getDefesa());

        System.out.println("\nO Dragão abre suas asas.");

        System.out.println("\"Se desejam encontrar seu mestre...\"");
        System.out.println("\"Terão de passar por mim.\"");

        Batalha batalha = new Batalha(party, criaturas);

        batalha.iniciarBatalha();

        if (party.getHerois().isEmpty()) {

            System.out.println("\nVharz permanece diante do portão.");

            System.out.println("A passagem para as câmaras inferiores");
            System.out.println("continua protegida.");

        } else {

            System.out.println("\nO Guardião finalmente caiu.");

            System.out.println("As correntes negras desaparecem.");
            System.out.println("A grande porta começa a se abrir.");

            System.out.println("\nAtrás dela existe uma escadaria.");

            System.out.println("No último degrau, uma figura aguarda.");

            System.out.println("Ela parece estar esperando pelos aventureiros");
            System.out.println("há centenas de anos.");
        }

        System.out.println("\n==============================================");
        System.out.println("               FIM DA CENA 6");
        System.out.println("==============================================");
    }

    private int lerEscolha(Scanner scanner, int minimo, int maximo) {

        while (true) {

            if (scanner.hasNextInt()) {

                int escolha = scanner.nextInt();
                scanner.nextLine();

                if (escolha >= minimo && escolha <= maximo) {
                    return escolha;
                }

            } else {

                scanner.nextLine();
            }

            System.out.println(
                "Escolha um número entre " + minimo + " e " + maximo + "."
            );
        }
    }
}
