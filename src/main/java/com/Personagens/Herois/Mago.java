package com.Personagens.Herois;

import com.Exceptions.UltimateOnRechargeException;
import com.Personagens.Combatente;
import com.Personagens.Criaturas.Criatura;

public class Mago extends Heroi {
    private String nome;

    public Mago(int n) {
        super(n, Classe.MAGO);
        super.setVidaMaxima(80 + (n - 1) * 10);
        super.setAtaque(15 + (n - 1) * 2);
        super.setDefesa(5 + (n - 1) * 1);
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

    public double usarHabilidadeEspecial(Criatura target) {
        try {
            System.out.println("Usando bola de fogo: " + this.getNome());

            target.receberDano(this.getAtaque() + 10);
            System.out.println("Bola de fogo acertou " + target.getNome() + " e causou dano!");
            consumirEsforco();
            return 0;
        } catch (UltimateOnRechargeException e) {
            System.out.println("alvo  não encontrado: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public double usarHabilidadeEspecial(Combatente target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'usarHabilidadeEspecial'");
    }
}
