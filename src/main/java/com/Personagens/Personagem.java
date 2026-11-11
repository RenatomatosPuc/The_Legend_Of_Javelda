package com.Personagens;

public abstract class Personagem implements Combatente {
    private String nome;
    private int nivel;
    private double vidaMaxima;
    private double vidaAtual;
    private double ataque;
    private double defesa;
    private int velocidade;
    private boolean estaVivo;

    @Override
    public double getAtaque() {
        return this.ataque;
    }

    @Override
    public double getDefesa() {
        return this.defesa;
    }

    public int getVelocidade() {
        return this.velocidade;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public double getVidaAtual() {
        return this.vidaAtual;
    }

    @Override
    public double getVidaMaxima() {
        return this.vidaMaxima;
    }

    @Override
    public boolean estaVivo() {
        if (this.vidaAtual >= 0)
            return true;
        return false;
    }

    @Override
    public double receberDano(double danoRecebido) { // da o dano e retorna a vida atual
        if (danoRecebido > this.defesa * 0.25)
            return this.vidaAtual = this.vidaAtual + this.defesa - danoRecebido; // a defesa reduz o dano em 1 quarto
                                                                                 // dela. Ou seja,
                                                                                 // ao ter 40 de defesa, tanka 10 de
                                                                                 // dano;
        return this.vidaAtual - 1;
    }

    // public abstract Batalha acao();
}
