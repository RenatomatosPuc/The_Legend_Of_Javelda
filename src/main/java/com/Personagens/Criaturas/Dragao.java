package com.Personagens.Criaturas;

import com.Personagens.Personagem;

import criaturas;

public class Dragao extends Criatura {

public Dragao(){


super("Dragao Anderson", 200, 100, S, 20);
}    

public void ataque(Personagem target){
    System.out.println()


   target.receberDano(this.forca/2);
}
public void soltarLoot() {
        System.out.println("soltou olho de Dragao" );
    }
}
