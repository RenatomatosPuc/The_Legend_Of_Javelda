package com.Missao;

import com.Batalha.Dificuldade;

public abstract class Missao {
    private String nome;
    private Dificuldade dificuldade;

    public Missao(String nome, Dificuldade dificuldade, int experiencia) {
        this.nome = nome;
        this.dificuldade = dificuldade;
    }
}
