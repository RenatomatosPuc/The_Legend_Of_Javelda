package com.Batalha;

import com.Personagens.Criaturas.Criatura;
import com.Personagens.Herois.Heroi;
import java.util.ArrayList;
import java.util.List;

public class Batalha {

    private List<Heroi> party;
    private List<Criatura> Criaturas;

    public Batalha(List<Heroi> party, List<Criatura> Criaturas) {
        this.party = new ArrayList<>(party);
        this.Criaturas = new ArrayList<>(Criaturas);
    }

    public void iniciarBatalha() {
        System.out.println("A batalha começou!");
        while (!party.isEmpty() && !Criaturas.isEmpty()) {
            for (Heroi heroi : new ArrayList<>(party)) {
                if (!Criaturas.isEmpty()) {
                    Criatura criatura = Criaturas.get(0);
                    heroi.atacar(criatura);
                    if (!criatura.estaVivo()) {
                        System.out.println(criatura.getNome() + " foi derrotada!");
                        Criaturas.remove(criatura);
                    }
                }
            }

            for (Criatura criatura : new ArrayList<>(Criaturas)) {
                if (!party.isEmpty()) {
                    Heroi heroi = party.get(0);
                    criatura.atacar(heroi);
                    if (!heroi.estaVivo()) {
                        System.out.println(heroi.getNome() + " foi derrotado!");
                        party.remove(heroi);
                    }
                }
            }
        }

        if (party.isEmpty()) {
            System.out.println("A equipe foi derrotada!");
        } else {
            System.out.println("A equipe venceu a batalha!");
        }
    }

}
