package com.Arsenal.Armas;

import java.util.ArrayList;

public class ColecaoArma {
    ArrayList<Arma> colecaoArma = new ArrayList<>();

    public ColecaoArma() {
        // Espadas -Guerreiro
        this.colecaoArma.add(new Espada("Espada de Ferro", 10, 25));
        this.colecaoArma.add(new Espada("Espada de diamante", 20, 40));
        this.colecaoArma.add(new Espada("Garras de Dragao", 40, 80));

        // Arcos -Arqueiro
        this.colecaoArma.add(new Arco("Estilingue", 13, 25));
        this.colecaoArma.add(new Arco("Arco de batalha", 30, 40));
        this.colecaoArma.add(new Arco("Chuva divina", 50, 80));

        // Varinha -Mago
        this.colecaoArma.add(new Varinha("Galho de Arvore", 19, 25));
        this.colecaoArma.add(new Varinha("Vara de Aluminio", 35, 40));
        this.colecaoArma.add(new Varinha("Chifre de unicornio", 65, 80));

        // Cajado -Clerigo

        this.colecaoArma.add(new Cajado("Dente de Leao", 4, 25, 10));
        this.colecaoArma.add(new Cajado("Cajado Simples", 10, 40, 30));
        this.colecaoArma.add(new Cajado("Cetro de Espirito", 13, 80, 50));
    }

    public ArrayList<Arma> getColecaoArma() {
        return this.colecaoArma;
    }
}