package com.Personagens.Herois;

import com.Personagens.Personagem;
import com.Arsenal.*;

public abstract class Heroi extends Personagem {
    private Classe classe;
    private int nivel;
    private double experiencia;
    private InventarioPersonagem inventarioPersonagem;
    private double ouro;

    // METODOS DE NIVEL

    public Heroi(int n, Classe c) {
        this.classe = c;
        this.nivel = n;
        this.experiencia = 0;
        this.inventarioPersonagem = new InventarioPersonagem();
        this.ouro = 0;
    }

    // Faz o heroi receber experiencia, e ao chegar em 100, sobe o nivel em 1 e
    // reduz exp em 100 e retorna a exp atual do jogador
    public double ganharExperiencia(double experienciaGanha) {
        this.experiencia = this.experiencia + experienciaGanha;
        if (experiencia >= 100) {
            this.experiencia = this.experiencia - 100;
            subirNivel();
        }
        return this.experiencia;
    }

    // Aumenta o nivel em 1
    public void subirNivel() {
        this.nivel = this.nivel + 1;
    }

    // METODOS DE ACOES

    // Calcula o poder da arma mais a forca do personagem e retorna o dano causado
    public double atacar() {
        double danoArma = 0;
        if (inventarioPersonagem.getArma() != null) {
            danoArma = inventarioPersonagem.getArma().getAtaque();
        }
        return danoArma + super.getAtaque();
    }

    // Usa o utilitario
    public void usarItem() {
        try {
            this.inventarioPersonagem.getUtilitario().usarUtilitario();
        } catch (Exception e) { // ATUALIZAR O EXCEPTION E O CATCH *********

        }
    }

    public abstract void usarHabilidadeEspecial();
}
