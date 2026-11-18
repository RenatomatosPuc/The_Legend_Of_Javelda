package com.Arsenal;

import java.util.ArrayList;

public class InventarioJogador {
    ArrayList<Equipavel> inventarioGeral = new ArrayList<>();

    public InventarioJogador() {

    }

    public Equipavel getItem(String nome) {
        for (Equipavel item : inventarioGeral) {
            if (item.getNome().equals(nome)) {
                return item;
            }
        }
        return null;
    }
}
