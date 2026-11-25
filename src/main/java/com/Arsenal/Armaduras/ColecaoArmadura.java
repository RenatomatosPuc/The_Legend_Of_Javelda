package com.Arsenal.Armaduras;

import java.util.ArrayList;

public class ColecaoArmadura {
    ArrayList<Armadura> colecaoArmadura = new ArrayList<>();

    public ColecaoArmadura() {
        this.colecaoArmadura.add(new Armadura("Armadura de Palha", 0.5, 3));
        this.colecaoArmadura.add(new Armadura("Armadura de Borracha", 3, 10));
        this.colecaoArmadura.add(new Armadura("Armadura de Cobre", 7, 20));
        this.colecaoArmadura.add(new Armadura("Cota de Malha", 10, 25));
        this.colecaoArmadura.add(new Armadura("Armadura de Ferro", 13, 30));
        this.colecaoArmadura.add(new Armadura("Armadura de Viking", 17, 40));
        this.colecaoArmadura.add(new Armadura("Armadura de Diamante", 20, 1000));
        this.colecaoArmadura.add(new Armadura("Armadura de Guerreiro Antigo", 25, 60));
        this.colecaoArmadura.add(new Armadura("Armadura de Sangue de Dragao", 30, 70));
        this.colecaoArmadura.add(new Armadura("Armadura de SemiDeus", 50, 100));
    }
}
