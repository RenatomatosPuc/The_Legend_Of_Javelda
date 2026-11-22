
package com.Batalha;

import com.Personagens.Herois.Heroi;

public class Cena3 {

    public void finalizar(Party party, int totalRecrutados) {

        System.out.println("\n\n==============================================");
        System.out.println("           CENA 3: AS CRÔNICAS");
        System.out.println("==============================================");

        if (party == null || party.getHerois().isEmpty()) {

            System.out.println("\nA noite retorna à capital.");

            System.out.println("O salão da guilda permanece silencioso.");
            System.out.println("Nenhum aventureiro retornou da missão.");

            System.out.println("\nO rei Bytemar recebe o relatório.");

            System.out.println("\"Mais uma equipe perdida...\"");
            System.out.println("\"Quantos ainda teremos de sacrificar?\"");

            System.out.println("\nOs nomes dos aventureiros são registrados");
            System.out.println("nas Crônicas dos Mortos.");

            System.out.println("\nA Guilda de Javária sofreu sua primeira derrota.");

            System.out.println("\nO Lich Nulo continua nas profundezas.");

            System.out.println("\n==============================================");
            System.out.println("             MISSÃO FRACASSADA");
            System.out.println("==============================================");

            return;
        }

        int sobreviventes = party.getQuantidade();

        int mortos = Math.max(
            0,
            totalRecrutados - sobreviventes
        );

        System.out.println("\nA equipe retorna à capital.");

        System.out.println("Os portões da cidade são abertos.");
        System.out.println("Os soldados observam os sobreviventes em silêncio.");

        System.out.println("\nO rei Bytemar aguarda no salão principal.");

        System.out.println("Rei Bytemar:");
        System.out.println("\"Vocês conseguiram retornar.\"");

        System.out.println("\"Mas vejo que nem todos estão entre nós.\"");

        // RELATÓRIO DA EXPEDIÇÃO
        System.out.println("\n==============================================");
        System.out.println("          RELATÓRIO DA EXPEDIÇÃO");
        System.out.println("==============================================");

        System.out.println("Heróis recrutados: " + totalRecrutados);
        System.out.println("Sobreviventes: " + sobreviventes);
        System.out.println("Heróis perdidos: " + mortos);

        System.out.println("\nSobreviventes da expedição:");

        for (Heroi heroi : party.getHerois()) {

            System.out.println(
                heroi.getNome()
                + " | " + heroi.getClasse()
                + " | Nível " + heroi.getNivel()
                + " | Ouro: " + heroi.getOuro()
            );
        }

        // RECOMPENSA EM OURO
        double ouroRecompensa = 30;

        System.out.println("\n==============================================");
        System.out.println("                RECOMPENSAS");
        System.out.println("==============================================");

        for (Heroi heroi : party.getHerois()) {

            heroi.adicionarOuro(ouroRecompensa);

            System.out.println(
                heroi.getNome()
                + " recebeu " + ouroRecompensa
                + " moedas de ouro."
            );

            System.out.println(
                "Ouro acumulado: " + heroi.getOuro()
            );
        }

        // DESCOBERTA
        System.out.println("\n==============================================");
        System.out.println("              O FRAGMENTO");
        System.out.println("==============================================");

        System.out.println("\nEnquanto a equipe descansa,");
        System.out.println("um dos aventureiros entrega ao rei");
        System.out.println("um fragmento encontrado na região.");

        System.out.println("\nA pedra é completamente negra.");

        System.out.println("Não reflete a luz das tochas.");
        System.out.println("Não apresenta inscrições conhecidas.");

        System.out.println("\nO rei Bytemar aproxima a mão.");

        System.out.println("Por um instante, todas as tochas se apagam.");

        System.out.println("\nUma voz atravessa o salão:");

        System.out.println("\"Vocês ainda estão no registro.\"");

        System.out.println("\"Por enquanto.\"");

        System.out.println("\nA pedra se parte ao meio.");

        System.out.println("O rei recua.");

        System.out.println("\nRei Bytemar:");
        System.out.println("\"Isso veio das Masmorras do Heap.\"");

        System.out.println("\"Precisamos descobrir o que está acontecendo.\"");

        System.out.println("\n==============================================");
        System.out.println("              FIM DA CENA 3");
        System.out.println("==============================================");
    }

    // Compatibilidade com chamadas antigas
    public void finalizar(Party party) {

        int quantidade = party == null
            ? 0
            : party.getQuantidade();

        finalizar(party, quantidade);
    }
}
