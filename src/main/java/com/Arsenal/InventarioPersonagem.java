package com.Arsenal;

public class InventarioPersonagem {

    // Slots fixos: [0] = arma, [1] = armadura, [2] = utilitário
    private Equipavel[] inventario = new Equipavel[3];

    // Construtor 1: inventario vazio
    public InventarioPersonagem() {

    }

    // Construtor2: caso queira comecar com os itens equipados
    public InventarioPersonagem(Arma a, Armadura d, Utilitario u) {
        this.inventario[0] = a;
        this.inventario[1] = d;
        this.inventario[2] = u;
    }

    // Getters
    public Arma getArma() {
        return (Arma) this.inventario[0];
    }

    public Armadura getArmadura() {
        return (Armadura) this.inventario[1];
    }

    public Utilitario getUtilitario() {
        return (Utilitario) this.inventario[2];
    }

    // Equipa a arma se o slot 0 estiver livre
    public void equiparArma(Arma a) {
        if (this.inventario[0] == null) {
            this.inventario[0] = a;
            return;
        }
        System.out.println("Você já possui uma arma equipada.");
    }

    // Equipa a armadura se o slot 1 estiver livre
    public void equiparArmadura(Armadura d) {
        if (this.inventario[1] == null) {
            this.inventario[1] = d;
            return;
        }
        System.out.println("Você já possui uma armadura equipada.");
    }

    // Equipa o utilitário se o slot 2 estiver livre
    public void equiparUtilitario(Utilitario u) {
        if (this.inventario[2] == null) {
            this.inventario[2] = u;
            return;
        }
        System.out.println("Você já possui um utilitario equipado.");
    }

    // Remove e devolve a arma (ou null se não houver)
    public Arma removerArma() {
        if (this.inventario[0] == null) {
            System.out.println("Você não possui nenhuma arma equipada.");
        }
        Arma armaRetorno = (Arma) this.inventario[0];
        this.inventario[0] = null;
        return armaRetorno;
    }

    // Remove e devolve a armadura (ou null se não houver)
    public Armadura removerArmadura() {
        if (this.inventario[1] == null) {
            System.out.println("Você não possui nenhuma armadura equipada.");
        }
        Armadura armaduraRetorno = (Armadura) this.inventario[1];
        this.inventario[1] = null;
        return armaduraRetorno;
    }

    // Remove e devolve a utilitaria (ou null se não houver)
    public Utilitario removerUtilitario() {
        if (this.inventario[2] == null) {
            System.out.println("Você não possui nenhuma arma equipada.");
        }
        Utilitario utilitarioRetorno = (Utilitario) this.inventario[2];
        this.inventario[2] = null;
        return utilitarioRetorno;
    }
}
