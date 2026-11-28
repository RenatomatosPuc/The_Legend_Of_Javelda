
package com.Batalha;

import java.util.List;
import java.util.Scanner;

import com.Personagens.Herois.Heroi;
import com.thelegendofjavelda.persistence.CsvSaveService;

public class Cena4 {
    private final CsvSaveService service;
    private List<Heroi> party;

    public Cena4(CsvSaveService service) {
        this.service = service;
        this.party = service.getEquipe();
    }

    public void iniciar(Scanner scanner) {

        if (party == null || party.isEmpty()) {
            System.out.println("A guilda não possui aventureiros disponíveis.");
            return;
        }

        System.out.println("\n==============================================");
        System.out.println("       CENA 4: O PESO DOS SOBREVIVENTES");
        System.out.println("==============================================");

        System.out.println("\nA noite cai sobre a capital.");

        System.out.println("Os sobreviventes retornam à guilda.");
        System.out.println("Alguns soldados estão feridos.");
        System.out.println("Os sinos da cidade continuam tocando.");

        System.out.println("\nNo salão principal, o rei Bytemar observa");
        System.out.println("o fragmento negro recuperado na missão.");

        System.out.println("\nRei Bytemar:");
        System.out.println("\"Não é uma pedra comum.\"");

        System.out.println("\"Os registros antigos mencionam algo semelhante.");
        System.out.println("Um selo utilizado para manter fechadas");
        System.out.println("as passagens das Masmorras do Heap.\"");

        System.out.println("\nO rei se aproxima da equipe.");

        System.out.println("\"Antes de prosseguirmos,");
        System.out.println("precisamos preparar vocês.\"");

        boolean continuar = true;

        while (continuar && !party.isEmpty()) {

            System.out.println("\n==============================================");
            System.out.println("              SALÃO DA GUILDA");
            System.out.println("==============================================");

            System.out.println("1 - Consultar aventureiros");
            System.out.println("2 - Descansar a equipe");
            System.out.println("3 - Continuar a jornada");

            int escolha = lerEscolha(scanner, 1, 3);

            switch (escolha) {

                case 1:

                    System.out.println("\n=== AVENTUREIROS ===");

                    for (Heroi heroi : party) {

                        System.out.println("\nNome: " + heroi.getNome());
                        System.out.println("Classe: " + heroi.getClasse());
                        System.out.println("Nível: " + heroi.getNivel());

                        System.out.println(
                                "Vida: " + heroi.getVidaAtual()
                                        + "/" + heroi.getVidaMaxima());

                        System.out.println("Ataque: " + heroi.getAtaque());
                        System.out.println("Defesa: " + heroi.getDefesa());
                        System.out.println("Esforço: " + heroi.getEsforco());
                        System.out.println("Ouro: " + heroi.getOuro());
                    }

                    break;

                case 2:

                    System.out.println("\nA equipe se recolhe para descansar.");

                    for (Heroi heroi : party) {

                        heroi.setVidaAtual(heroi.getVidaMaxima());

                        System.out.println(
                                heroi.getNome() + " recuperou sua vida.");
                    }

                    System.out.println("\nA equipe está recuperada.");

                    break;

                case 3:

                    continuar = false;

                    break;
            }
        }

        System.out.println("\nO rei entrega o fragmento à equipe.");

        System.out.println("\"Encontrem a entrada das Masmorras.\"");

        System.out.println("\"E descubram o que Nulien despertou.\"");

        System.out.println("\n==============================================");
        System.out.println("              FIM DA CENA 4");
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
                    "Escolha um número entre " + minimo + " e " + maximo + ".");
        }
    }
}
