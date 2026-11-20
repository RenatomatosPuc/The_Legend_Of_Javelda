package com.Arsenal.Itens;

import java.util.ArrayList;

public class ColecaoUtilitario {
    private ArrayList<Utilitario> colecaoUtilitarios = new ArrayList<>();

    public ColecaoUtilitario() {
        this.colecaoUtilitarios.add(new Utilitario("Poção de Vida", 20));
        this.colecaoUtilitarios.add(new Utilitario("Poção de Mana", 30));
        this.colecaoUtilitarios.add(new Utilitario("Poção de Força", 15));
        this.colecaoUtilitarios.add(new Utilitario("Poção de Agilidade", 10));
    }

    public ArrayList<Utilitario> getColecaoUtilitarios() {
        return this.colecaoUtilitarios;
    }

}
