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

    // Metodo PlaceHolder pra usar o utilitario
    // Mudem depois
    public void usarUtilitario() throws Exception {
        if (uso > 0) {
            uso--;
        } else {
            throw new Exception("O utilitário " + nome + " não pode mais ser usado.");
        }
    }

    public int getSlotInventario() {
        return this.slotInventario;
    }

}
