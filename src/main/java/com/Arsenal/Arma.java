package com.Arsenal;

public class Arma implements Equipavel {

    private String nome;
    private double ataque;
    private double durabilidade;
    private int slotInventario = 0;

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

    public int getSlotInventario() {
        return this.slotInventario;
    }
}
