package com.Missao;

import java.util.List;
import com.Personagens.Criaturas.Goblin;

import com.Batalha.Dificuldade;

public class MissaoFloresta extends Missao {
    public MissaoFloresta(Dificuldade dificuldade, int experiencia) {
        super("Floresta dos Sussuros", dificuldade, experiencia, List.of(() -> new Goblin(dificuldade.getValor())));
    }
}
