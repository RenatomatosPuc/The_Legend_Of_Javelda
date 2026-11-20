package com.Personagens.Criaturas;

import com.Personagens.Personagem;

public class Dragao extends Criatura {
    public Dragao(String nome, int vidaMaxima, double defesa, double ataque, String rankPerigo, int velocidade,
            int nivel, int vidaAtual, boolean estaVivo) {
        super(nome, vidaMaxima, defesa, ataque, rankPerigo, velocidade, nivel, vidaAtual, estaVivo);
        setEsforco(8);
    }

    public double atacar(Personagem target) {
        try {
            System.out.println(
                    "O dragão mordeu " + target.getNome() + " com um ataque de " + this.getAtaque() + " de dano!");
            target.receberDano(this.getAtaque());
        } catch (NullPointerException e) {
            System.out.println("O dragão mordeu, mas ninguem pegou, alvo vazio");
        }
        return this.getAtaque();
    }

    public double ataqueEspecial(Personagem target) {
        try {
            if (!consumirEsforco()) {
                return 0;
            }
            System.out.println("O dragão lançou um sopro de java de dano! em: " + target.getNome());
            target.receberDano(this.getAtaque() + 20);
        } catch (NullPointerException e) {
            System.out.println("O dragão soprou,soprou, mas ninguem pegou, alvo vazio");
        }
        return this.getAtaque() + 20;
    }

    public void soltarLoot(Personagem target) {
        // personagem recebe a esperiencia do dragao e dropa um item

        System.out.println("O dragão soltou um olho de dragão!" + " e o jogador recebeu " + this.getExperiencia()
                + " de experiência!");
        target.receberExperiencia(this.getExperiencia());
    }

    // metodo temperario para que pare de dar erro por causa do metodo
    // usarHabilidadeEspecial da classe Combatente
    public double usarHabilidadeEspecial(Criatura target) {
        return ataqueEspecial(target);
    }
}
