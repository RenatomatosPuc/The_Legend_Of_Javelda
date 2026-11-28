package com.Arsenal.Itens.Pocoes.Ataque;

import com.Arsenal.Itens.Tipo;
import com.Arsenal.Itens.Utilitario;
import com.Personagens.Personagem;
import com.Exceptions.NoUsageLeftException;

public abstract class PocaoAtaque extends Utilitario {
    protected String nome;
    protected int ataque;
    protected Tipo tipo = Tipo.ATAQUE;

    public PocaoAtaque(String n, int u, int a, int d, Tipo t) {
        super(n, u, d, t);
        this.ataque = a;
    }

    public int getAtaque() {
        return this.ataque;
    }

    public Tipo getTipo() {
        return this.tipo;
    }

    public void usarPocao(Personagem alvo) {
        try {
            usarUtilitario(alvo);
            alvo.setAtaque(alvo.getAtaque() + this.ataque);
        } catch (NoUsageLeftException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}