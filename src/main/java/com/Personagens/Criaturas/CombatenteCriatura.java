package com.Personagens.Criaturas;

import com.Personagens.Combatente;
import com.Personagens.Personagem;

public interface CombatenteCriatura extends Combatente {

    double atacar(Combatente target);

    double ataqueEspecial(Combatente target);

    void soltarLoot();

    double getXp();
}