package com.Arsenal.Itens.Pocoes.Cura;

import com.Arsenal.Itens.Utilitario;
import com.Personagens.Personagem;
import com.Exceptions.NoUsageLeftException;

public abstract class PocaoCura extends Utilitario {
    protected String nome;
    protected int cura;

    public PocaoCura(String n, int u, int c, int d) {
        super(n, u, d);
        this.cura = c;
    }

    public int getCura() {
        return this.cura;
    }

    public void usarCura(Personagem alvo) {
        try {
            usarUtilitario(alvo);
            if (alvo.getVidaAtual() + this.cura > alvo.getVidaMaxima()) {
                alvo.setVidaAtual(alvo.getVidaMaxima());
            } else {
                alvo.setVidaAtual(alvo.getVidaAtual() + this.cura);
            }
        } catch (NoUsageLeftException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}