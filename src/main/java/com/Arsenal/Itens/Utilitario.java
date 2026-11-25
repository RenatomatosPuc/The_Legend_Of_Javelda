package com.Arsenal.Itens;

import com.Arsenal.Equipavel;
import com.Personagens.Personagem;
import com.Exceptions.NoUsageLeftException;

public abstract class Utilitario implements Equipavel {
    protected String nome;
    protected int uso;
    protected int slotInventario = 2;
    protected int duracao;

    public Utilitario(String nome, int uso, int duracao) {
        this.nome = nome;
        this.uso = uso;
        this.duracao = duracao;
    }

    public String getNome() {
        return this.nome;
    }

    public void usarUtilitario(Personagem alvo) {
        if (this.uso > 0) {
            this.uso--;
        } else {
            throw new NoUsageLeftException();
        }
    }

    public int getSlotInventario() {
        return this.slotInventario;
    }
}
