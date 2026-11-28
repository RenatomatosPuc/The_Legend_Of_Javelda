package com.Missao;

import java.util.List;
import com.Personagens.Criaturas.Goblin;

import com.Batalha.Dificuldade;

public class MissaoFloresta extends Missao {
    public MissaoFloresta(Dificuldade dificuldade, int experiencia) {
        // O nível do Goblin acompanha o nível definido pela dificuldade.
        super("Floresta dos Sussuros", dificuldade, experiencia, List.of(() -> new Goblin(dificuldade.getValor())));
    }
}
