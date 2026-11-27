
package com.Batalha;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.Personagens.Criaturas.Criatura;
import com.Personagens.Criaturas.Goblin;
import com.Personagens.Criaturas.Esqueleto;

public class Cena5 {

    public void iniciar(Party party, Scanner scanner) {

        if (party == null || party.getHerois().isEmpty()) {
            System.out.println("A equipe não pode explorar as masmorras.");
            return;
        }

        System.out.println("\n==============================================");
        System.out.println("          CENA 5: A ENTRADA DO HEAP");
        System.out.println("==============================================");

        System.out.println("\nApós dias de investigação, a equipe encontra");
        System.out.println("uma passagem escondida sob as ruínas de uma antiga catedral.");

        System.out.println("O fragmento negro começa a vibrar.");

        System.out.println("\nUma parede de pedra se move lentamente.");
        System.out.println("Atrás dela existe uma escadaria que desce");
        System.out.println("para uma escuridão que parece não ter fim.");

        System.out.println("\nUm dos aventureiros encontra uma inscrição:");

        System.out.println("\"O que foi descartado permanece abaixo.\"");

        System.out.println("\nA equipe entra nas Masmorras do Heap.");

        System.out.println("O ar é pesado.");
        System.out.println("As paredes parecem cobertas por nomes apagados.");
        System.out.println("Algumas vozes vêm de lugares onde não existe ninguém.");

        // ESCOLHA DA DIFICULDADE

        System.out.println("\n==============================================");
        System.out.println("          PERIGO DAS MASMORRAS");
        System.out.println("==============================================");

        System.out.println("1 - Fácil");
        System.out.println("2 - Médio");
        System.out.println("3 - Difícil");

        int escolha = lerEscolha(scanner, 1, 3);

        Dificuldade dificuldade;

        switch (escolha) {

            case 1:
                dificuldade = Dificuldade.FACIL;
                break;

            case 2:
                dificuldade = Dificuldade.MEDIO;
                break;

            default:
                dificuldade = Dificuldade.DIFICIL;
                break;
        }

        int nivel = dificuldade.getValor();

        List<Criatura> criaturas = new ArrayList<>();

        System.out.println("\nA equipe escuta passos no corredor.");

        // CRIATURAS ENCONTRADAS
        if (dificuldade == Dificuldade.FACIL) {

            Goblin goblin = new Goblin();
            goblin.setNome("Goblin Deformado");

            criaturas.add(goblin);

            Esqueleto esqueleto = new Esqueleto(
                    "Guardião Esquecido",
                    70,
                    7,
                    14,
                    "Comum",
                    6,
                    1,
                    70,
                    true);

            criaturas.add(esqueleto);

        } else if (dificuldade == Dificuldade.MEDIO) {

            for (int i = 1; i <= 2; i++) {

                Criatura goblin = new Goblin();
                goblin.setNome("Goblin Corrompido " + i);

                criaturas.add(goblin);
            }

            criaturas.add(
                    new Esqueleto(
                            "Guardião Esquecido",
                            90,
                            10,
                            18,
                            "Comum",
                            7,
                            2,
                            90,
                            true));

        } else {

            for (int i = 1; i <= 2; i++) {

                Criatura goblin = new Goblin();
                goblin.setNome("Goblin sem Referência " + i);

                criaturas.add(goblin);

                criaturas.add(
                        new Esqueleto(
                                "Esqueleto Fora de Escopo " + i,
                                110,
                                13,
                                23,
                                "Perigoso",
                                8,
                                3,
                                110,
                                true));
            }
        }

        System.out.println("\nA equipe foi cercada!");
        System.out.println("Quantidade de inimigos: " + criaturas.size());

        System.out.println("\n==============================================");
        System.out.println("              COMBATE NO HEAP");
        System.out.println("==============================================");

        Batalha batalha = new Batalha(party, criaturas);

        batalha.iniciarBatalha();

        if (party.getHerois().isEmpty()) {

            System.out.println("\nA equipe caiu nas profundezas.");

            System.out.println("O portão das Masmorras permanece aberto.");

        } else {

            System.out.println("\nA equipe encontra uma grande porta");
            System.out.println("de pedra no fim do corredor.");

            System.out.println("Há uma inscrição em sua superfície:");

            System.out.println("\"Aquele que guarda a passagem");
            System.out.println("não possui mais um coração.\"");
        }

        System.out.println("\n==============================================");
        System.out.println("               FIM DA CENA 5");
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
