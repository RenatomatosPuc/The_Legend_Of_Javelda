package com.Personagens.Criaturas;

import com.Personagens.Personagem;

public class Esqueleto extends Criatura {

    public Esqueleto(String nome, int vidaMaxima, double defesa, double ataque, String rankPerigo, int velocidade,
            int nivel, int vidaAtual, boolean estaVivo) {
        super(nome, vidaMaxima, defesa, ataque, rankPerigo, velocidade, nivel, vidaAtual, estaVivo);
        setEsforco(6);
    }

    public double atacar(Personagem target) {
        System.out.println(
                "O esqueleto atacou " + target.getNome() + " com um ataque de " + this.getAtaque() + " de dano!");
        target.receberDano(this.getAtaque());
        return this.getAtaque();
    }

    public double ataqueEspecial(Personagem target) {
        if (!consumirEsforco()) {
            return 0;
        }
        System.out.println("O esqueleto precisao de um morto : " + target.getNome());
        target.receberDano(this.getAtaque()+5);
        return this.getAtaque() + 5;
    }

    public void soltarLoot() {
        System.out.println("O esqueleto soltou dropou um item!");
    }

}
