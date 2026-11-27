
package com.Batalha;

import java.util.ArrayList;
import java.util.List;

import com.Personagens.Herois.Heroi;

public class Party {

    private List<Heroi> herois;

    public Party() {
        this.herois = new ArrayList<>();
    }


    public void adicionarHeroi(Heroi heroi) {
        this.herois.add(heroi);
    }

   
    public List<Heroi> getHerois() {
        return this.herois;
    }

    
    public int getQuantidade() {
        return herois.size();
    }

    public void removerHeroi(Heroi heroi) {
        this.herois.remove(heroi);
    }

    public void limparParty() {
        this.herois.clear();
    }

    public void exibirParty() {
        if (herois.isEmpty()) {
            System.out.println("A party está vazia.");
            return;
        }

        

        System.out.println("=== Party ===");
        for (Heroi heroi : herois) {
            System.out.println("Nome: " + heroi.getNome() + ", Classe: " + heroi.getClasse() + ", Nível: " + heroi.getNivel() + ", Experiência: " + heroi.getExperiencia());
        }
    }

    public void ganharExperienciaGrupo(double xpTotal) {

        if (herois.isEmpty()) {
            System.out.println("A party está vazia. Nenhum herói para ganhar experiência.");
            return;
        }

        int heroisVivos = 0;

        for (Heroi heroi : herois) {

            if (heroi.estaVivo()) {
                heroi.receberExperiencia(xpTotal);
                heroisVivos++;
            }
        }

        System.out.println(
            heroisVivos + " herói(s) receberam " + xpTotal + " de experiência cada."
        );
    }
}
