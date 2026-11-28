
package com.Personagens;

public abstract class Personagem implements Combatente {

    private String nome;
    private int nivel;

    private double vidaMaxima;
    private double vidaAtual;

    private double ataque;
    private double defesa;

    private int velocidade;
    private boolean estaVivo;

    private int esforco;
    private int experiencia;

    // CONSTRUTOR
    public Personagem(
        String nome,
        double vidaMaxima,
        double defesa,
        double ataque,
        int velocidade,
        int nivel,
        int esforco,
        int experiencia,
        double vidaAtual,
        boolean estaVivo
    ) {

        this.nome = nome;
        this.vidaMaxima = Math.max(0, vidaMaxima);
        this.vidaAtual = Math.max(0, Math.min(vidaAtual, this.vidaMaxima));

        this.ataque = ataque;
        this.defesa = defesa;

        this.velocidade = velocidade;
        this.nivel = nivel;

        this.esforco = Math.max(0, esforco);
        this.experiencia = experiencia;

        this.estaVivo = estaVivo && this.vidaAtual > 0;
    }

    // GETTERS

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public int getNivel() {
        return this.nivel;
    }

    @Override
    public double getVidaMaxima() {
        return this.vidaMaxima;
    }

    @Override
    public double getVidaAtual() {
        return this.vidaAtual;
    }

    @Override
    public double getAtaque() {
        return this.ataque;
    }

    @Override
    public double getDefesa() {
        return this.defesa;
    }

    public int getVelocidade() {
        return this.velocidade;
    }

    public int getEsforco() {
        return this.esforco;
    }

    public int getExperiencia() {
        return this.experiencia;
    }

    // SETTERS

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public void setVidaMaxima(double vidaMaxima) {

        this.vidaMaxima = Math.max(0, vidaMaxima);

        if (this.vidaAtual > this.vidaMaxima) {
            setVidaAtual(this.vidaMaxima);
        }
    }

    public void setVidaAtual(double vidaAtual) {

        this.vidaAtual = Math.max(
            0,
            Math.min(vidaAtual, this.vidaMaxima)
        );

        this.estaVivo = this.vidaAtual > 0;
    }

    public void setAtaque(double ataque) {
        this.ataque = ataque;
    }

    public void setDefesa(double defesa) {
        this.defesa = defesa;
    }

<<<<<<< HEAD
    public void setEsforco(int esforco) {
        this.esforco = Math.max(esforco, 0);
    }

    // VERIFICA SE O PERSONAGEM ESTÁ VIVO

    @Override
    public boolean estaVivo() {
        return this.estaVivo && this.vidaAtual > 0;
    }

    // EXPERIÊNCIA

    public double receberExperiencia(int experienciaRecebida) {

        if (experienciaRecebida > 0) {
            this.experiencia += experienciaRecebida;
        }

=======
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNivel(int n) {
        this.nivel = n;
    }

    @Override
    public boolean estaVivo() {
        if (this.vidaAtual >= 0)
            return true;
        return false;
    }

    public double receberExperiencia(double experienciaRecebida) {
        this.experiencia += experienciaRecebida;
>>>>>>> 48d4b1c0ffd70b5cbae3ffcb1acbed9062b78479
        return this.experiencia;
    }

    // RECEBER DANO

    @Override
    public double receberDano(double danoRecebido) {

        if (!estaVivo() || danoRecebido <= 0) {
            return this.vidaAtual;
        }

        // A defesa reduz o dano recebido
        double danoNegado = this.defesa * 0.25;

        double danoFinal = Math.max(
            0,
            danoRecebido - danoNegado
        );

        this.vidaAtual = Math.max(
            0,
            this.vidaAtual - danoFinal
        );

        // Verifica se o personagem morreu
        if (this.vidaAtual <= 0) {

            this.vidaAtual = 0;
            this.estaVivo = false;

            System.out.println(
                this.nome + " foi derrotado!"
            );
        }

        return this.vidaAtual;
    }
}
