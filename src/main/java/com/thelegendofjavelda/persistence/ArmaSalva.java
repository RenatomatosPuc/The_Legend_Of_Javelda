package com.thelegendofjavelda.persistence;

import jakarta.persistence.Embeddable;

@Embeddable
public class ArmaSalva {
    private String nome;
    private int ataque;

    protected ArmaSalva() {
    }

    public ArmaSalva(String nome, int ataque) {
        this.nome = nome;
        this.ataque = ataque;
    }

    public String getNome() {
        return nome;
    }

    public int getAtaque() {
        return ataque;
    }

}
