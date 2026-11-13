package com.Arsenal;

public class Utilitario implements Equipavel {
    private String nome;
    private double uso;
    private int slotInventario = 2;

    public Utilitario(String nome, double uso) {
        this.nome = nome;
        this.uso = uso;
    }

    public String getNome() {
        return nome;
    }

    public double getUso() {
        return uso;
    }

    // Usa o utilitario
    // E necessario criar os itens para implimentar esse metodo
    /*
     * public void usarUtilitario()
     */

    public int getSlotInventario() {
        return this.slotInventario;
    }

}
