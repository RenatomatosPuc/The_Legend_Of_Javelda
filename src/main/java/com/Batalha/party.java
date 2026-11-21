package com.Batalha;

import java.util.List;
import java.util.ArrayList;
import com.Personagens.Herois.Heroi;

public class party {
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

        // Dá o valor integral do XP a cada membro da party
        public void ganharExperienciaGrupo(double xpTotal) {
            double xp = xpTotal / herois.size();
            if (herois.isEmpty()) {
                System.out.println("A party está vazia. Nenhum herói para ganhar experiência.");
                return;
            }
            System.out.println("Todos os heróis da party receberam " + xpTotal + " de experiência.");
            herois.stream()
                    .filter(Heroi::estaVivo)
                    .forEach(h -> h.ganharExperiencia(xp));
        }
    }
}
