package com.Personagens.Herois;

import com.Personagens.Personagem;

public abstract class Heroi extends Personagem {
    private Arma armamento;
    private Armadura armadura;
    private Utilitario item;
    private int nivel;
    private Classe classe;
    private double ouro;
    private Inventario inventario;
    private double experiencia;
    private double valorBuff;

    // METODOS DE NIVEL

    public double ganharExperiencia(double experienciaGanha) { // Faz o heroi receber experiencia, e ao chegar em 100,
                                                               // sobe o nivel em 1 e reduz exp em 100
        this.experiencia = this.experiencia + experienciaGanha;
        if (experiencia >= 100) {
            this.experiencia = this.experiencia - 100;
            subirNivel();
        }
        return this.experiencia; // Retrna a experiencia atual |*POR ENQUANTO*|
    }

    public void subirNivel() { // Aumenta o nivel em 1
        this.nivel = this.nivel + 1;
    }

    // METODOS DE ACOES

    public double atacar() {

        if (this.armamento != null) {
            danoArma = this.armamento.getAtaque();

        } else {
            double danoArma = 0;
        }
        return danoArma + super.getAtaque(); // rolar o dano do ataque (sujeito a alterações)
    }

    public double usarItem() {
        try {
            this.item.usarUtilitario();
        } catch (ExceptionBOLADONA e) { // ATUALIZAR O EXCEPTION E O CATCH *********

        }
    }

    // METODOS DE EQUIPAMENTO:

    public void equiparArmadura(Armadura armadura) {
        try {
            this.armadura = armadura;

        } catch (ExceptionBOLADONA e) { // ATUALIZAR O EXCEPTION E O CATCH *********

        }
    }

    public void equiparArma(Armamento arma) {
        try {
            this.armamento = arma;
        } catch (ExceptionBOLADONA e) { // ATUALIZAR O EXCEPTION E O CATCH *********

        }
    }

    public void equiparItem(Utilitario item) {
        try {
            this.item = item;
        } catch (ExceptionBOLADONA e) { // ATUALIZAR O EXCEPTION E O CATCH *********

        }
    }

    public abstract void usarHabilidadeEspecial();
}
