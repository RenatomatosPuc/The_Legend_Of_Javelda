package com.Personagens.Criaturas;

public class Dragao {

    public class dragao extends Criatura {
        public Dragao(String nome, int vidaMaxima, double defesa, double ataque, String rankPerigo, int velocidade, int nivel, int vidaAtual, boolean estaVivo) {
            super(nome, vidaMaxima, defesa, ataque, rankPerigo, velocidade, nivel, vidaAtual, estaVivo);
        }

      
        public void soltarLoot() {
       System.out.println("O dragão soltou um olho de dragão!");
        }
    }
    


}
