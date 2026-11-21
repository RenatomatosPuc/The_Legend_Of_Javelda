package com.Personagens.Criaturas;

import com.Personagens.Combatente;
import com.Personagens.Personagem; 

public interface CombatenteCriatura extends Combatente {

    // Ataque básico do monstro contra um alvo
    double atacar(Combatente target);

    // Ataque especial ou habilidade de mob (consumindo esforço/cooldown)
    double ataqueEspecial(Combatente target);

    // Executa a lógica de drop de itens ao morrer
    void soltarLoot();

    // Retorna a quantidade de XP total que este monstro concede à Party ao ser derrotado
    double getXpConcedido();
}