package com.Batalha;

import com.Personagens.Combatente;
import com.Personagens.Personagem;
import com.Personagens.Criaturas.Criatura;
import com.Personagens.Herois.Heroi;
import com.vaadin.flow.component.notification.Notification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Batalha {

    // Mantém os combatentes, o registro e o estado da batalha atual.
    private Equipe equipeOriginal;
    private final List<Heroi> party;
    private final List<Criatura> criaturas;
    private final List<String> log = new ArrayList<>();
    private int indexHeroiAtual = 0;

    private double xpTotal;
    private boolean xpDistribuido = false;

    public Batalha(List<Heroi> herois, List<Criatura> criaturas) {
        if (herois == null || criaturas == null) {
            throw new IllegalArgumentException("Heróis e criaturas não podem ser nulos.");
        }
        this.party = new ArrayList<>(herois);
        this.criaturas = new ArrayList<>(criaturas);
    }

    // A batalha termina quando um dos lados não tem mais combatentes ativos.
    public boolean terminou() {
        return party.isEmpty() || criaturas.isEmpty();
    }

    public boolean equipeVenceu() {
        return terminou() && !party.isEmpty();
    }

    public List<Heroi> getHerois() {
        return Collections.unmodifiableList(party);
    }

    // Retorna o herói que deve escolher uma ação neste momento.
    public Heroi getHeroiAtual() {
        pularHeroisDerrotados();
        if (party.isEmpty() || criaturas.isEmpty()) {
            return null;
        }
        return party.get(indexHeroiAtual);
    }

    public List<Criatura> getCriaturas() {
        return Collections.unmodifiableList(criaturas);
    }

    public double getXpTotal() {
        return xpTotal;
    }

    public List<String> getLog() {
        return Collections.unmodifiableList(log);
    }

    // Remove da equipe de combate os heróis derrotados a partir do índice atual.
    public void pularHeroisDerrotados() {
        while (indexHeroiAtual < party.size() && !party.get(indexHeroiAtual).estaVivo()) {
            party.remove(indexHeroiAtual);
        }
    }

    // Roda a batalha inteira (útil no console)
    public void iniciarBatalha() {
        if (terminou()) {
            registrar("Não há combate possível.");
            return;
        }
        registrar("A BATALHA COMEÇOU!");
        while (!terminou()) {
            executarRodada();
        }
    }

    // Executa a ação escolhida para o herói atual e avança o fluxo dos turnos.
    public void executarAcaoHeroiAtual(Acao acao, Personagem alvo) {
        Heroi heroiAtual = getHeroiAtual();
        if (heroiAtual == null || alvo == null) {
            return;
        }

        switch (acao) {
            case ATACAR -> {
                heroiAtual.atacar(alvo);
            }

            case HABILIDADE -> {
                heroiAtual.usarHabilidadeEspecial(alvo);
            }

            case ITEM -> {
                registrar(heroiAtual.getNome() + " usou o item " + heroiAtual.getItemSelecionado().getNome() + " em "
                        + alvo.getNome() + ".");
                heroiAtual.usarItemSelecionado(alvo);
            }
        }
        processarCriaturasDerrotadas();

        indexHeroiAtual++;
        pularHeroisDerrotados();

        if (!terminou() && indexHeroiAtual >= party.size()) {
            turnoCriaturas();
            indexHeroiAtual = 0;
            pularHeroisDerrotados();
        }

        if (terminou()) {
            finalizar();
        }
    }

    // Concede XP e loot e remove da lista qualquer criatura derrotada.
    public void processarCriaturasDerrotadas() {
        for (Criatura criatura : new ArrayList<>(criaturas)) {
            if (!criatura.estaVivo()) {
                double xp = criatura.getXp();
                xpTotal += xp;
                registrar(criatura.getNome() + " foi derrotada! (+" + xp + " XP)");
                criatura.soltarLoot();
                criaturas.remove(criatura);
            }
        }
    }

    // Monta os alvos que podem ser escolhidos para a ação informada.
    public List<Personagem> getAlvosValidos(Acao acao, Heroi heroi) {
        List<Personagem> alvos = new ArrayList<>();
        switch (acao) {
            case ATACAR -> {
                criaturas.stream()
                        .filter(Criatura::estaVivo)
                        .forEach(alvos::add);

            }
            case HABILIDADE -> {
                if (heroi.getClasse() == com.Personagens.Herois.Classe.CLERIGO) {
                    party.stream()
                            .filter(Heroi::estaVivo)
                            .forEach(alvos::add);
                } else {
                    criaturas.stream()
                            .filter(Criatura::estaVivo)
                            .forEach(alvos::add);
                }
            }
            case ITEM -> {
                if (heroi.getItemSelecionado().getTipo() == com.Arsenal.Itens.Tipo.CURA) {
                    party.stream()
                            .filter(Heroi::estaVivo)
                            .forEach(alvos::add);
                } else {
                    criaturas.stream()
                            .filter(Criatura::estaVivo)
                            .forEach(alvos::add);
                }
            }
        }
        return alvos;
    }

    // Uma rodada: o Vaadin pode chamar isso a cada clique
    public void executarRodada() {
        if (terminou()) {
            finalizar();
            return;
        }
        turnoHerois();
        if (!terminou()) {
            turnoCriaturas();
        }
        if (terminou()) {
            finalizar();
        }
    }

    private void turnoHerois() {
        registrar("=== TURNO DOS HERÓIS ===");

        for (Heroi heroi : new ArrayList<>(party)) {
            if (criaturas.isEmpty())
                break;

            if (!heroi.estaVivo()) {
                party.remove(heroi);
                continue;
            }

            Criatura alvo = criaturas.get(0);
            double dano = heroi.atacar(alvo);
            registrar(heroi.getNome() + " atacou " + alvo.getNome()
                    + " causando " + dano + " de dano.");

            if (!alvo.estaVivo()) {
                double xp = alvo.getXp();
                xpTotal += xp;
                registrar(alvo.getNome() + " foi derrotada! (+" + xp + " XP)");
                alvo.soltarLoot();
                criaturas.remove(alvo);
            }
        }
    }

    // Cada criatura viva realiza sua ação contra a equipe.
    private void turnoCriaturas() {
        registrar("=== TURNO DAS CRIATURAS ===");

        for (Criatura criatura : new ArrayList<>(criaturas)) {
            if (party.isEmpty())
                break;

            Heroi alvo = party.get(0);
            double dano = criatura.atacar(alvo);
            registrar(criatura.getNome() + " atacou " + alvo.getNome()
                    + " causando " + dano + " de dano.");
            registrar(alvo.getNome() + " agora tem " + alvo.getVidaAtual() + " de vida.");

            if (!alvo.estaVivo()) {
                registrar(alvo.getNome() + " foi derrotado!");
                party.remove(alvo);
            }
        }
    }

    // Registra o resultado e distribui a experiência da vitória uma única vez.
    private void finalizar() {
        if (xpDistribuido)
            return;
        xpDistribuido = true;

        if (party.isEmpty()) {
            registrar("A EQUIPE FOI DERROTADA!");
            return;
        }

        registrar("A EQUIPE VENCEU A BATALHA!");

        if (xpTotal <= 0)
            return;

        if (equipeOriginal != null) {
            equipeOriginal.ganharExperienciaGrupo(xpTotal);
        } else {
            for (Heroi heroi : party) {
                if (heroi.estaVivo()) {
                    heroi.receberExperiencia(xpTotal);
                }
            }
        }
        registrar("Cada herói sobrevivente recebeu " + xpTotal + " XP.");
    }

    // Guarda a mensagem no histórico da batalha.
    private void registrar(String mensagem) {
        log.add(mensagem);
    }
}
