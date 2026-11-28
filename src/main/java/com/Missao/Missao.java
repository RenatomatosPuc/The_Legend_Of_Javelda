package com.Missao;

import java.util.List;
import java.util.function.Supplier;

import com.Batalha.Dificuldade;
import com.Personagens.Criaturas.*;

public abstract class Missao {
    private String nome;
    private Dificuldade dificuldade;
    // Cada fornecedor cria uma instância nova de criatura quando a missão começa.
    private final List<Supplier<Criatura>> criaturasSuppliers;

    public Missao(String nome, Dificuldade dificuldade, int experiencia, List<Supplier<Criatura>> criaturasSuppliers) {
        this.nome = nome;
        this.dificuldade = dificuldade;
        this.criaturasSuppliers = criaturasSuppliers;
    }

    // Gera a lista de criaturas para uma nova execução da missão.
    public List<Criatura> getCriaturas() {
        return criaturasSuppliers.stream().map(Supplier::get).toList();
    }
}
