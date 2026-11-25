package com.Personagens;

import com.Arsenal.InventarioPersonagem;
import com.Personagens.Criaturas.Criatura;

public abstract class Personagem implements Combatente {
    private String nome;
    private int nivel;
    private double vidaMaxima;
    private double vidaAtual;
    private double ataque;
    private double defesa;
    private int velocidade;
    private boolean estaVivo;
    private int esforco;
    private int experiencia;

    public Personagem(String nome, int vidaMaxima, double defesa, double ataque, int velocidade, int nivel, int esforco,
            int experiencia,
            int vidaAtual, boolean estaVivo) {
        this.nome = nome;
        this.vidaMaxima = vidaMaxima;
        this.vidaAtual = vidaAtual;
        this.ataque = ataque;
        this.defesa = defesa;
        this.velocidade = velocidade;
        this.estaVivo = estaVivo;
        this.nivel = nivel;
        this.esforco = esforco;
        this.experiencia = experiencia;
    }

    @Override
    public double getAtaque() {
        return this.ataque;
    }

    public int getEsforco() {
        return esforco;
    }

    public void setEsforco(int esforco) {
        this.esforco = Math.max(esforco, 0);
    }

    public int getExperiencia() {
        return experiencia;
    }

    @Override
    public int getNivel() {
        return this.nivel;
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

    public void setVidaMaxima(double vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }

    public void setVidaAtual(double vidaAtual) {
        this.vidaAtual = vidaAtual;
    }

    public void setAtaque(double ataque) {
        this.ataque = ataque;
    }

    public void setDefesa(double defesa) {
        this.defesa = defesa;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNivel(int n) {
        this.nivel = n;
    }

    @Override
    public boolean estaVivo() {
        if (this.vidaAtual >= 0)
            return true;
        return false;
    }

    public double receberExperiencia(double experienciaRecebida) {
        this.experiencia += experienciaRecebida;
        return this.experiencia;
    }

    @Override
    public double receberDano(double danoRecebido) { // da o dano e retorna a vida atual
        double danoNegado = danoRecebido - this.defesa * 0.25;
        if (danoRecebido > this.defesa * 0.25) {
            this.vidaAtual = this.vidaAtual - danoNegado;
            return this.vidaAtual; // a defesa reduz o dano em 1 quarto
        } else {
            this.vidaAtual = this.vidaAtual - 0;
        }

        if (this.vidaAtual <= 0) {
            this.vidaAtual = 0;
            this.estaVivo = false;
        }
        return this.vidaAtual; // dano;
    }

    public InventarioPersonagem getInventarioPersonagem() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInventarioPersonagem'");
    }

    public double usarHabilidadeEspecial(Combatente target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'usarHabilidadeEspecial'");
    }

    public double usarHabilidadeEspecial(Criatura target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'usarHabilidadeEspecial'");
    }

    // public abstract Batalha acao();
}
