
package com.Batalha;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.Personagens.Criaturas.Criatura;
import com.Personagens.Criaturas.Goblin;
import com.Personagens.Herois.Heroi;
import com.Personagens.Criaturas.Esqueleto;
import com.thelegendofjavelda.persistence.CsvSaveService;

public class Cena2 {
    private final CsvSaveService service;
    private List<Heroi> party;

    public Cena2(CsvSaveService service) {
        this.service = service;
    }

    public void iniciar(Scanner scanner) {
        this.party = service.getEquipe();

        if (party == null || party.isEmpty()) {

            System.out.println("Não existem heróis disponíveis para a missão.");
            return;
        }

        System.out.println("\n\n==============================================");
        System.out.println("             CENA 2: O PRIMEIRO SANGUE");
        System.out.println("==============================================");

        System.out.println("\nA manhã chega à capital.");

        System.out.println("A chuva ainda não cessou.");
        System.out.println("Os soldados recolhem os destroços das ruas.");
        System.out.println("Algumas casas permanecem vazias.");
        System.out.println("Ninguém sabe explicar o que aconteceu com seus moradores.");

        System.out.println("\nNo salão da guilda, o rei Bytemar aguarda.");

        System.out.println("\nRei Bytemar:");
        System.out.println("\"Recebemos dois relatos durante a madrugada.\"");

        System.out.println("\"Uma floresta inteira perdeu seus habitantes.");
        System.out.println("No cemitério real, os mortos começaram a despertar.\"");

        System.out.println("\n\"Não sabemos se os acontecimentos estão relacionados.");
        System.out.println("Mas ambos apresentam sinais da mesma corrupção.\"");

        // ESCOLHA DA MISSÃO

        System.out.println("\n==============================================");
        System.out.println("                MISSÕES");
        System.out.println("==============================================");

        System.out.println("\n1 - Floresta dos Sussurros");
        System.out.println("    Goblins sem referência estão atacando viajantes.");

        System.out.println("\n2 - Cemitério dos Esquecidos");
        System.out.println("    Esqueletos fora de escopo estão despertando.");

        System.out.println("\nEscolha sua missão:");

        int missao = lerEscolha(scanner, 1, 2);

        // ESCOLHA DA DIFICULDADE

        System.out.println("\n==============================================");
        System.out.println("                DIFICULDADE");
        System.out.println("==============================================");

        System.out.println("\n1 - Fácil");
        System.out.println("    Poucos inimigos e nível reduzido.");

        System.out.println("\n2 - Médio");
        System.out.println("    Mais inimigos e maior resistência.");

        System.out.println("\n3 - Difícil");
        System.out.println("    Muitos inimigos e nível elevado.");

        int escolhaDificuldade = lerEscolha(scanner, 1, 3);

        Dificuldade dificuldade;

        switch (escolhaDificuldade) {

            case 1:
                dificuldade = Dificuldade.FACIL;
                break;

            case 2:
                dificuldade = Dificuldade.MEDIO;
                break;

            case 3:
                dificuldade = Dificuldade.DIFICIL;
                break;

            default:
                throw new IllegalStateException("Dificuldade inválida.");
        }

        int nivelInimigo = dificuldade.getValor();
        int quantidadeInimigos = dificuldade.getValor();

        String nomeDificuldade;

        switch (dificuldade) {

            case FACIL:
                nomeDificuldade = "FÁCIL";
                break;

            case MEDIO:
                nomeDificuldade = "MÉDIO";
                break;

            default:
                nomeDificuldade = "DIFÍCIL";
                break;
        }

        System.out.println("\nDificuldade selecionada: " + nomeDificuldade);

        // CRIAÇÃO DOS INIMIGOS

        List<Criatura> criaturas = new ArrayList<>();

        if (missao == 1) {

            System.out.println("\n==============================================");
            System.out.println("            FLORESTA DOS SUSSURROS");
            System.out.println("==============================================");

            System.out.println("\nA equipe deixa a capital ao amanhecer.");

            System.out.println("A estrada está coberta por cinzas.");
            System.out.println("As árvores parecem inclinadas na direção dos viajantes.");
            System.out.println("Não há pássaros. Não há vento.");

            System.out.println("\nDepois de algumas horas, a equipe encontra");
            System.out.println("uma carroça abandonada.");

            System.out.println("Ao lado dela existe uma placa:");
            System.out.println("\"NÃO RESPONDA SE OUVIR SEU PRÓPRIO NOME.\"");

            System.out.println("\nUma voz surge entre as árvores.");

            System.out.println("\"Finalmente... alguém consegue nos enxergar.\"");

            System.out.println("\nCriaturas começam a sair da floresta!");

            for (int i = 1; i <= quantidadeInimigos; i++) {

                Goblin goblin = new Goblin(nivelInimigo);

                goblin.setNome("Goblin sem Referência " + i);

                criaturas.add(goblin);
            }

        } else {

            System.out.println("\n==============================================");
            System.out.println("            CEMITÉRIO DOS ESQUECIDOS");
            System.out.println("==============================================");

            System.out.println("\nA equipe chega ao antigo cemitério real.");

            System.out.println("As lápides estão completamente vazias.");
            System.out.println("Algumas sepulturas estão abertas.");
            System.out.println("Os nomes foram arrancados até mesmo das pedras.");

            System.out.println("\nUm dos heróis encontra uma inscrição:");

            System.out.println("\"Aqui descansa alguém que nunca existiu.\"");

            System.out.println("\nO chão começa a tremer.");

            System.out.println("Mãos de ossos surgem entre a terra.");
            System.out.println("Antigos guardiões levantam suas armas.");

            System.out.println("\nOs Esqueletos Fora de Escopo despertaram!");

            for (int i = 1; i <= quantidadeInimigos; i++) {

                int vida = 60 + (nivelInimigo - 1) * 20;

                double defesa = 5 + (nivelInimigo - 1) * 2;

                double ataque = 12 + (nivelInimigo - 1) * 3;

                Esqueleto esqueleto = new Esqueleto(
                        "Esqueleto Fora de Escopo " + i,
                        vida,
                        defesa,
                        ataque,
                        "Comum",
                        5 + nivelInimigo,
                        nivelInimigo,
                        vida,
                        true);

                criaturas.add(esqueleto);
            }
        }

        // INFORMAÇÕES DO COMBATE

        System.out.println("\n==============================================");
        System.out.println("             PREPARAÇÃO PARA O COMBATE");
        System.out.println("==============================================");

        System.out.println("\nMissão: " +
                (missao == 1
                        ? "Floresta dos Sussurros"
                        : "Cemitério dos Esquecidos"));

        System.out.println("Dificuldade: " + nomeDificuldade);
        System.out.println("Inimigos encontrados: " + criaturas.size());

        System.out.println("\nA equipe prepara suas armas.");

        System.out.println("\"Não deixem que essas coisas");
        System.out.println("cheguem perto de vocês!\"");

        // INICIAR BATALHA

        Batalha batalha = new Batalha(
                party,
                criaturas);

        batalha.iniciarBatalha();

        // ENCERRAMENTO NARRATIVO

        System.out.println("\n==============================================");
        System.out.println("             FIM DA MISSÃO");
        System.out.println("==============================================");

        if (party.isEmpty()) {

            System.out.println("\nA equipe foi completamente derrotada.");

            System.out.println("A região permanece sob domínio da corrupção.");

            System.out.println("O rei Bytemar recebe a notícia.");
            System.out.println("Mais nomes serão escritos nas Crônicas dos Mortos.");

        } else {

            System.out.println("\nOs sobreviventes conseguem retornar à estrada.");

            System.out.println("Antes de deixar a região, encontram");
            System.out.println("um fragmento de pedra completamente negra.");

            System.out.println("Ele apresenta o símbolo das Masmorras do Heap.");

            System.out.println("\nO fragmento começa a sussurrar:");

            System.out.println("\"Ele ainda se lembra de vocês.\"");
        }
    }

    // Permite iniciar sem fornecer Scanner
    public void iniciar() {
        iniciar(new Scanner(System.in));
    }

    // VALIDAÇÃO
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
