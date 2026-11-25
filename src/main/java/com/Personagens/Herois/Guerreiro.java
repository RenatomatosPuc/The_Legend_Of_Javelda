package com.Personagens.Herois;
import com.Personagens.Combatente;
import com.Exceptions.UltimateOnRechargeException;

public class Guerreiro extends Heroi {
    private String nome;

    // Construtor
    public Guerreiro(int n) {
        super(n, Classe.GUERREIRO);
        super.setVidaMaxima(150 + (n - 1) * 10);
        super.setAtaque(20 + (n - 1) * 2);
        super.setDefesa(10 + (n - 1) * 1);
        super.setEsforco(12);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    // Nao tem contexto em questao de lore do que faz.
    // (TEMPORARIO ATE O BALANCEAMENTO)
    @Override
    public double usarHabilidadeEspecial(Combatente target) {
        try {
            consumirEsforco();
            return 0;
        } catch (UltimateOnRechargeException e) {
            System.out.println("Erro:" + e.getMessage());
            return 0;
        }
    }
}
