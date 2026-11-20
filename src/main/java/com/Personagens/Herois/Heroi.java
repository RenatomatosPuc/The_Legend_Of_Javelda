package com.Personagens.Herois;

import com.Personagens.Personagem;
import com.Personagens.Criaturas.Criatura;
import com.Arsenal.*;
import com.Exceptions.UltimateOnRechargeException;

public abstract class Heroi extends Personagem {
    private Classe classe;
    private int nivel;
    private double experiencia;
    private InventarioPersonagem inventarioPersonagem;
    private double ouro;
    private int contadorAtaque;
    private String nome;
    private int id;

    // METODOS DE NIVEL

    public Heroi(int n, Classe c) {
        super("", 0, 0, 0, 0, n, esforcoInicial(c), 0, 0, true);
        this.classe = c;
        this.nivel = n;
        this.experiencia = 0;
        this.inventarioPersonagem = new InventarioPersonagem();
        this.ouro = 0;
        this.contadorAtaque = 0;
        this.nome = "";
        this.id = 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getContadorAtaque() {
        return this.contadorAtaque;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Classe getClasse() {
        return this.classe;
    }

    public int getNivel() {
        return this.nivel;
    }

    public InventarioPersonagem getInventarioPersonagem() {
        return this.inventarioPersonagem;
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
        this.contadorAtaque++;
        return danoArma + super.getAtaque();
    }

    @Override
    public double atacar(Personagem target) {
        double dano = atacar();
        target.receberDano(dano);
        return dano;
    }

    private static int esforcoInicial(Classe classe) {
        switch (classe) {
            case GUERREIRO:
                return 4;
            case ARQUEIRO:
            case CLERIGO:
                return 10;
            case MAGO:
                return 12;
            default:
                throw new IllegalArgumentException("Classe de heroi invalida: " + classe);
        }
    }

    // Usa o utilitario
    // E necessario criar os itens para implimentar esse metodo
    /*
     * public void usarItem() {
     * inventarioPersonagem.getUtilitario().usarUtilitario();
     * }
     */

    public abstract double usarHabilidadeEspecial(Criatura target);

    protected void consumirEsforco() throws UltimateOnRechargeException {
        if (getEsforco() <= 0) {
            throw new UltimateOnRechargeException("Esforco insuficiente: habilidade especial cancelada.");
        }

        setEsforco(getEsforco() - 1);
    }

}
