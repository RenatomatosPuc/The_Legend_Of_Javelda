
package com.Batalha;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.Personagens.Criaturas.Criatura;
import com.Personagens.Criaturas.Lich_Null;

public class Cena7 {

    public void iniciar(Party party, Scanner scanner) {

        if (party == null || party.getHerois().isEmpty()) {

            System.out.println("Não existem aventureiros para enfrentar o Lich.");
            return;
        }

        System.out.println("\n\n##############################################");
        System.out.println("#                                            #");
        System.out.println("#             O ÚLTIMO REGISTRO             #");
        System.out.println("#                                            #");
        System.out.println("##############################################");

        System.out.println("\nA equipe chega à última câmara das Masmorras.");

        System.out.println("Não existem portas.");
        System.out.println("Não existem paredes.");
        System.out.println("Apenas um espaço escuro, coberto por nomes suspensos.");

        System.out.println("\nNo centro da câmara existe um trono.");

        System.out.println("Uma figura está sentada nele.");

        System.out.println("\nLich Nulo:");
        System.out.println("\"Então vocês chegaram até mim.\"");

        System.out.println("\"Passei trezentos anos esperando.\"");

        System.out.println("\nUm dos aventureiros pergunta:");

        System.out.println("\"Por que está fazendo isso com Javária?\"");

        System.out.println("\nO Lich se levanta.");

        System.out.println("\"Vocês chamam de equilíbrio aquilo que é apenas controle.\"");

        System.out.println("\"O Grande Compilador escolhe quem merece existir.\"");

        System.out.println("\"Minha irmã foi apagada.");
        System.out.println("Milhares de pessoas desapareceram.");
        System.out.println("E todos vocês aceitaram isso como uma ordem natural.\"");

        System.out.println("\nEle aponta para os nomes suspensos.");

        System.out.println("\"Eu apenas quero devolver ao mundo aquilo");
        System.out.println("que lhe foi roubado.\"");

        System.out.println("\nO fragmento negro começa a flutuar.");

        System.out.println("O chão se abre sob os aventureiros.");

        System.out.println("\nNulien:");
        System.out.println("\"Mas, para restaurar os esquecidos,");
        System.out.println("precisarei apagar tudo o que veio depois.\"");

        System.out.println("\nO trono se desfaz.");

        System.out.println("O Lich ergue seu grimório.");

        System.out.println("\n\"Que a última revisão comece.\"");

        // PREPARAÇÃO DO CHEFE
        Lich_Null lich = new Lich_Null();

        List<Criatura> inimigos = new ArrayList<>();

        inimigos.add(lich);

        System.out.println("\n==============================================");
        System.out.println("                 CHEFE FINAL");
        System.out.println("==============================================");

        System.out.println("NULIEN, O LICH NULO");

        System.out.println("Nível: " + lich.getNivel());
        System.out.println("Vida: " + lich.getVidaAtual());
        System.out.println("Ataque: " + lich.getAtaque());
        System.out.println("Defesa: " + lich.getDefesa());

        System.out.println("\nA batalha pelo destino de Javária começa!");

        Batalha batalha = new Batalha(party, inimigos);

        batalha.iniciarBatalha();

        // RESULTADO FINAL
        if (party.getHerois().isEmpty()) {

            System.out.println("\n==============================================");
            System.out.println("             O VAZIO VENCEU");
            System.out.println("==============================================");

            System.out.println("\nNulien retorna ao trono.");

            System.out.println("Um a um, os registros do reino começam a desaparecer.");

            System.out.println("As cidades perdem seus nomes.");
            System.out.println("As histórias deixam de existir.");
            System.out.println("Javária desaparece das páginas da realidade.");

            System.out.println("\nO Lich finalmente encontra silêncio.");

            return;
        }

        System.out.println("\n==============================================");
        System.out.println("             O ÚLTIMO REGISTRO");
        System.out.println("==============================================");

        System.out.println("\nO grimório cai das mãos de Nulien.");

        System.out.println("O Vazio começa a desaparecer.");

        System.out.println("Pela primeira vez em trezentos anos,");
        System.out.println("a câmara fica em silêncio.");

        System.out.println("\nAntes de desaparecer, Nulien olha para os aventureiros.");

        System.out.println("\"Eu só queria que ela fosse lembrada...\"");

        System.out.println("\nSeu corpo se desfaz em pequenas partículas.");

        System.out.println("Uma última página cai no chão.");

        System.out.println("Nela existe apenas um nome:");

        System.out.println("O nome da irmã de Nulien.");

        System.out.println("\n==============================================");
        System.out.println("              O DESTINO DO REINO");
        System.out.println("==============================================");

        System.out.println("\nO Grande Compilador começa a despertar.");

        System.out.println("Sua voz ecoa pela câmara:");

        System.out.println("\"A Revisão foi interrompida.\"");

        System.out.println("\"Mas a ordem ainda precisa ser restaurada.\"");

        System.out.println("\nVocê terá de decidir o destino de Javária.");

        System.out.println("1 - Restaurar o selo do Grande Compilador.");
        System.out.println("2 - Destruir o sistema das Revisões.");

        int finalEscolhido = lerEscolha(scanner, 1, 2);

        if (finalEscolhido == 1) {

            System.out.println("\n==============================================");
            System.out.println("              FINAL: A ORDEM");
            System.out.println("==============================================");

            System.out.println("\nOs aventureiros restauram o selo.");

            System.out.println("A corrupção desaparece das terras.");

            System.out.println("O reino finalmente pode reconstruir suas cidades.");

            System.out.println("Entretanto, nas profundezas,");
            System.out.println("o Grande Compilador continua observando.");

            System.out.println("\nAs Revisões talvez nunca tenham terminado.");

        } else {

            System.out.println("\n==============================================");
            System.out.println("              FINAL: A LIBERDADE");
            System.out.println("==============================================");

            System.out.println("\nOs aventureiros destroem o núcleo do sistema.");

            System.out.println("O Grande Compilador deixa de existir.");

            System.out.println("Os registros esquecidos retornam lentamente.");

            System.out.println("Pela primeira vez, nenhuma entidade");
            System.out.println("decidirá quem merece permanecer no mundo.");

            System.out.println("\nJavária terá de aprender a existir sozinha.");
        }

        System.out.println("\n==============================================");
        System.out.println("             FIM DA CAMPANHA");
        System.out.println("==============================================");

        System.out.println("\nOs nomes dos aventureiros permanecerão");
        System.out.println("nas Crônicas de Javária.");
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
