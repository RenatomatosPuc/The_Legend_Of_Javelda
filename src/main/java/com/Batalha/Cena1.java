
package com.Batalha;

import java.util.Scanner;

import com.Personagens.Herois.Heroi;
import com.Personagens.Herois.Arqueiro;
import com.Personagens.Herois.Clerigo;
import com.Personagens.Herois.Mago;
import com.Personagens.Herois.Guerreiro;

public class Cena1 {

    public Party iniciar(Scanner scanner) {

        Party party = new Party();

        System.out.println("\n\n");
        System.out.println("################################################");
        System.out.println("#                                              #");
        System.out.println("#         JAVÁRIA: CRÔNICAS DO VAZIO           #");
        System.out.println("#                                              #");
        System.out.println("################################################");

        System.out.println("\n             PRÓLOGO: A ÚLTIMA NOITE");

        System.out.println("\nHá trezentos anos, o reino de Javária vivia sob");
        System.out.println("a proteção do Grande Compilador.");

        System.out.println("Cada alma possuía um nome.");
        System.out.println("Cada criatura possuía uma origem.");
        System.out.println("Cada existência possuía um lugar no mundo.");

        System.out.println("\nMas tudo aquilo que era considerado imperfeito");
        System.out.println("podia ser apagado durante a chamada Revisão.");

        System.out.println("\nUm antigo mago chamado Nulien tentou impedir");
        System.out.println("que sua irmã fosse apagada da existência.");

        System.out.println("Em busca de respostas, desceu às Masmorras do Heap.");
        System.out.println("Lá encontrou algo que não deveria existir.");

        System.out.println("\nO NULO.");

        System.out.println("Ao tentar controlar aquela força, Nulien perdeu");
        System.out.println("seu corpo, sua identidade e sua própria humanidade.");

        System.out.println("Ele renasceu como o Lich Nulo.");

        System.out.println("\nDesde então, o selo das Masmorras permanece rompido.");
        System.out.println("E aquilo que foi apagado começou a retornar.");

        System.out.println("\n\n==============================================");
        System.out.println("             A QUEDA DE JAVÁRIA");
        System.out.println("==============================================");

        System.out.println("\nA capital amanheceu sob um céu vermelho.");

        System.out.println("Os sinos da cidade tocam sem parar.");
        System.out.println("As torres de proteção estão falhando.");
        System.out.println("Fumaça cobre os telhados e as ruas estão tomadas");
        System.out.println("por moradores tentando escapar.");

        System.out.println("\nUm soldado atravessa a praça gritando:");

        System.out.println("\"AS PORTAS DO DISTRITO NORTE CAÍRAM!\"");
        System.out.println("\"NÃO DEIXEM AS CRIATURAS CHEGAREM À CATEDRAL!\"");

        System.out.println("\nUma explosão de energia atravessa o céu.");
        System.out.println("Por alguns segundos, tudo fica completamente escuro.");

        System.out.println("\nQuando a luz retorna, uma torre inteira");
        System.out.println("parece ter desaparecido.");

        System.out.println("Os moradores que estavam próximos começam");
        System.out.println("a perguntar onde fica a torre que sempre existiu ali.");

        System.out.println("\nNinguém se lembra dela.");

        System.out.println("\n==============================================");
        System.out.println("              A GUILDA DE JAVÁRIA");
        System.out.println("==============================================");

        System.out.println("\nEm meio ao caos, o rei Bytemar reúne os últimos");
        System.out.println("representantes das ordens de aventureiros.");

        System.out.println("\nRei Bytemar:");
        System.out.println("\"Nosso exército não conseguirá proteger todas");
        System.out.println("as regiões do reino.\"");

        System.out.println("\"Precisamos de aventureiros capazes de enfrentar");
        System.out.println("aquilo que nossos soldados não compreendem.\"");

        System.out.println("\nO rei entrega a você o símbolo da Guilda de Javária.");

        System.out.println("\"A partir desta noite, você será responsável");
        System.out.println("por reunir aqueles que ainda podem lutar.\"");

        System.out.println("\n\"Não prometo que todos retornarão.\"");
        System.out.println("\"Mas prometo que seus nomes não serão esquecidos.\"");

        System.out.println("\n==============================================");
        System.out.println("               RECRUTAMENTO");
        System.out.println("==============================================");

        System.out.println("\nOs sobreviventes começam a chegar ao salão da guilda.");

        System.out.println("Alguns procuram proteção.");
        System.out.println("Outros buscam vingança.");
        System.out.println("Há também aqueles que já não possuem");
        System.out.println("um lugar para onde voltar.");

        System.out.println("\nQuantos heróis deseja recrutar? (1 a 4)");

        int quantidade = lerEscolha(scanner, 1, 4);

        for (int i = 1; i <= quantidade; i++) {

            System.out.println("\n----------------------------------------------");
            System.out.println("             RECRUTA " + i);
            System.out.println("----------------------------------------------");

            System.out.println("Escolha a classe:");
            System.out.println("1 - Arqueiro");
            System.out.println("2 - Clérigo");
            System.out.println("3 - Mago");
            System.out.println("4 - Guerreiro");

            int escolha = lerEscolha(scanner, 1, 4);

            System.out.print("\nQual é o nome do aventureiro? ");

            String nome = scanner.nextLine().trim();

            Heroi heroi;

            switch (escolha) {

                case 1:

                    Arqueiro arqueiro = new Arqueiro(1);
                    arqueiro.setNome(nome);
                    heroi = arqueiro;

                    break;

                case 2:

                    Clerigo clerigo = new Clerigo(1);
                    clerigo.setNome(nome);
                    heroi = clerigo;

                    break;

                case 3:

                    Mago mago = new Mago(1);
                    mago.setNome(nome);
                    heroi = mago;

                    break;

                case 4:

                    Guerreiro guerreiro = new Guerreiro(1);
                    guerreiro.setNome(nome);
                    heroi = guerreiro;

                    break;

                default:

                    throw new IllegalStateException("Classe inválida.");
            }

            heroi.setId(i);

            party.adicionarHeroi(heroi);

            System.out.println("\nO aventureiro " + heroi.getNome());
            System.out.println("agora faz parte da Guilda de Javária.");

            System.out.println("Enquanto isso, os sinos continuam tocando...");
        }

        System.out.println("\n==============================================");
        System.out.println("             EQUIPE DA GUILDA");
        System.out.println("==============================================");

        for (Heroi heroi : party.getHerois()) {

            System.out.println(
                "Nome: " + heroi.getNome()
                + " | Classe: " + heroi.getClasse()
                + " | Nível: " + heroi.getNivel()
            );
        }

        System.out.println("\nUm mensageiro invade o salão.");
        System.out.println("Seu uniforme está coberto de poeira.");

        System.out.println("\nMensageiro:");
        System.out.println("\"Meu senhor... encontramos algo na estrada.\"");

        System.out.println("\"Os mortos estão caminhando novamente.\"");

        System.out.println("\nO rei Bytemar olha para sua equipe.");

        System.out.println("\"Parece que sua primeira missão chegou.\"");

        System.out.println("\n==============================================");
        System.out.println("             FIM DA CENA 1");
        System.out.println("==============================================");

        return party;
    }

    public Party iniciar() {
        return iniciar(new Scanner(System.in));
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
