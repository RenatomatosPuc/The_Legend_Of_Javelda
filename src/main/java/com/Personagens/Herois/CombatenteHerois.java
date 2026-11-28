package com.Personagens.Herois;

import com.Personagens.Combatente;
import com.Personagens.Personagem;
import com.Arsenal.InventarioPersonagem;

public interface CombatenteHerois extends Combatente {
    

    double atacar(Combatente target);

    double usarHabilidadeEspecial(Combatente target);


    InventarioPersonagem getInventarioPersonagem();


    double ganharExperiencia(double experienciaGanha);


    void subirNivel();
}