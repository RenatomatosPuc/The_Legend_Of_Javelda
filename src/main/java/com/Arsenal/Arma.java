package com.Arsenal;

public class Arma {

    private String nome;
    private double ataque;
    private double durabilidade;

    public Arma(String nome, double ataque, double durabilidade) {
        this.nome = nome;
        this.ataque = ataque;
        this.durabilidade = durabilidade;
    }

    public String getNome() {
        return nome;
    }

    public double getAtaque() {
        return ataque;
    }

    public double getDurabilidade() {
        return durabilidade;
    }
}
