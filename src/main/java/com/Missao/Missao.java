package com.Missao;

import java.util.List;
import java.util.function.Supplier;

import com.Batalha.Dificuldade;
import com.Personagens.Criaturas.*;

public abstract class Missao {
    private String nome;
    private Dificuldade dificuldade;
    private List<Supplier<Criatura>> criaturasSuppliers;

    public Missao(String nome, Dificuldade dificuldade, int experiencia, List<Supplier<Criatura>> criaturasSuppliers) {
        this.nome = nome;
        this.dificuldade = dificuldade;
        this.criaturasSuppliers = criaturasSuppliers;
    }
}
