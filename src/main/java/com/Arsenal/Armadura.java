package com.Arsenal;

public class Armadura {
    private String nome;
    private double defesa;
    private double durabilidade;

    public Armadura(String nome, double defesa, double durabilidade) {
        this.nome = nome;
        this.defesa = defesa;
        this.durabilidade = durabilidade;
    }

    public String getNome() {
        return nome;
    }

    public double getDefesa() {
        return defesa;
    }

    public double getDurabilidade() {
        return durabilidade;
    }
}
