
package com.Personagens.Herois;

import com.Personagens.Personagem;
import com.Personagens.Combatente;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

import com.Arsenal.InventarioPersonagem;
import com.Arsenal.Itens.Utilitario;
import com.Exceptions.UltimateOnRechargeException;

public abstract class Heroi extends Personagem implements CombatenteHerois {

    private Classe classe;
    private double experiencia;
    private InventarioPersonagem inventarioPersonagem;
    private double ouro;
    private int contadorAtaque;
    private int id;

    public Heroi(int n, Classe c) {

        super("", 0, 0, 0, 0, n, esforcoInicial(c), 0, 0, true);

        this.classe = c;
        this.experiencia = 0;
        this.inventarioPersonagem = new InventarioPersonagem();
        this.ouro = 0;
        this.contadorAtaque = 0;
        this.id = 0;
    }

    // IDENTIFICAÇÃO

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        super.setNome(nome);
    }

    public void setNivel(int n) {
        super.setNivel(n);
    }

    public int getId() {
        return this.id;
    }

    public Classe getClasse() {
        return this.classe;
    }

    public int getContadorAtaque() {
        return this.contadorAtaque;
    }

    public Utilitario getItemSelecionado() {
        return this.inventarioPersonagem.getUtilitario();
    }

    // OURO

    public double getOuro() {
        return this.ouro;
    }

    public void adicionarOuro(double quantidade) {
        this.ouro += quantidade;
    }

    public void gastarOuro(double quantidade) {
        if (quantidade <= this.ouro) {
            this.ouro -= quantidade;
        } else {
            System.out.println("O herói " + getNome() + " não tem ouro suficiente!");
        }
    }

    @Override
    public InventarioPersonagem getInventarioPersonagem() {
        return this.inventarioPersonagem;
    }

    // EXPERIÊNCIA

    @Override
    public double receberExperiencia(double experienciaGanha) {
        this.experiencia += experienciaGanha;

        while (this.experiencia >= 100) {

            this.experiencia -= 100;

            subirNivel();

            System.out.println(
                    "O herói " + getNome() +
                            " subiu para o nível " + getNivel() + "!");
        }

        return this.experiencia;
    }

    public void subirNivel() {

        setNivel(getNivel() + 1);

        setVidaMaxima(getVidaMaxima() + 15);

        setVidaAtual(getVidaMaxima());

        setAtaque(getAtaque() + 3);

        setDefesa(getDefesa() + 2);
    }

    // COMBATE

    public double danoAtaque() {
        double danoArma = 0;

        if (inventarioPersonagem != null &&
                inventarioPersonagem.getArma() != null) {

            danoArma = inventarioPersonagem.getArma().getAtaque();
        }

        this.contadorAtaque++;

        return danoArma + getAtaque();
    }

    @Override
    public double atacar(Personagem target) {

        if (target == null || !target.estaVivo()) {

            System.out.println(
                    getNome() + " tentou atacar, mas não há um alvo válido!");

            return 0;
        }

        double dano = danoAtaque();
        // Aplica o dano ao alvo antes de registrar a vida restante.
        target.receberDano(dano);
        System.out.println(getNome() + " atacou " + target.getNome() + " causando " + dano + " de dano!");
        System.out.println(target.getNome() + " agora tem " + target.getVidaAtual() + " de vida.");
        System.out.println("-------------------------------------------------");
        return dano;

    }

    @Override
    public abstract double usarHabilidadeEspecial(Combatente target);

    protected void consumirEsforco() throws UltimateOnRechargeException {

        if (getEsforco() <= 0) {

            throw new UltimateOnRechargeException(
                    "Esforço insuficiente: habilidade especial cancelada.");
        }

        setEsforco(getEsforco() - 1);
    }

    private static int esforcoInicial(Classe classe) {

        switch (classe) {

            case GUERREIRO:
                return 12;

            case ARQUEIRO:
                return 10;

            case CLERIGO:
                return 8;

            case MAGO:
                return 12;

            default:
                throw new IllegalArgumentException(
                        "Classe de herói inválida: " + classe);
        }
    }
    // Usa o utilitario

    public void usarItemSelecionado(Personagem alvo) {
    }

}
