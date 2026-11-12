package com.Arsenal;

public class Armadura implements Equipavel {
    private String nome;
    private double defesa;
    private double durabilidade;
    private int slotInventario = 1;

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

    public int getSlotInventario() {
        return this.slotInventario;
    }
}
