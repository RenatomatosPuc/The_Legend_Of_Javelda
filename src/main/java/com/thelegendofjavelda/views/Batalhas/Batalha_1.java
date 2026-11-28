package com.thelegendofjavelda.views.Batalhas;

import java.util.List;
import java.util.function.Supplier;

import org.jetbrains.kotlin.it.unimi.dsi.fastutil.ints.H;

import com.Batalha.Batalha;
import com.Missao.Missao;
import com.Missao.MissaoFloresta;
import com.Personagens.Criaturas.Criatura;
import com.Personagens.Herois.Heroi;
import com.thelegendofjavelda.persistence.CsvSaveService;
import com.thelegendofjavelda.views.JornadaView;
import com.thelegendofjavelda.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.progressbar.ProgressBarVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.streams.DownloadHandler;

@Route(value = "batalha_1", layout = BatalhaLayout.class)
public class Batalha_1 extends BatalhaLayout {
    VerticalLayout tela = new VerticalLayout();
    Div arena = new Div();

    CsvSaveService service;

    DownloadHandler spriteHandler;
    DownloadHandler spriteEquipeHandler;
    ProgressBar barraInimigo;
    ProgressBar barraEquipe;
    private int vida = 100;
    List<Criatura> criaturas;
    List<Heroi> equipeHerois;
    Batalha batalha;

    Div painelInimigo = criarPainelInimigo("Nome", 100);
    Div painelEquipe = criarPainelEquipe(100);
    Div painelEscolhas = new Div();
    Div mensagem = new Div();

    Image spriteInimigo;
    Image spriteEquipe;

    Button btnAtacar = new Button("Atacar");
    Button btnRodada = new Button("Proxima rodada");
    Button btnSair = new Button("Sair da batalha");

    public Batalha_1(CsvSaveService service) {
        Missao missao_1 = new MissaoFloresta(service.getDificuldade(), 100);

        criaturas = missao_1.getCriaturas();
        equipeHerois = service.getEquipe();
        batalha = new Batalha(equipeHerois, criaturas);

        barraInimigo = new ProgressBar();
        barraEquipe = new ProgressBar();

        barraInimigo.setValue(vida / 100.0);
        barraEquipe.setValue(vida / 100.0);

        barraInimigo.setThemeVariant(ProgressBarVariant.LUMO_ERROR, true);
        barraEquipe.setThemeVariant(ProgressBarVariant.LUMO_ERROR, true);

        spriteHandler = DownloadHandler.forClassResource(getClass(), "/images/arena/sprite_goblin.png",
                "sprite_goblin.png");
        spriteEquipeHandler = DownloadHandler.forClassResource(getClass(), "/images/arena/sprite_equipe.png",
                "sprite_equipe.png");

        spriteInimigo = new Image(spriteHandler, "sprite_goblin.png");
        spriteEquipe = new Image(spriteEquipeHandler, "sprite_equipe.png");

        mensagem.setText("mensagem atual");
        atualizarbarras();

        btnAtacar.addClickListener(event -> {
            batalha.executarRodada();
            atualizarbarras();
            mensagem.setText("Ação 1 executada!");
        });

        btnRodada.addClickListener(event -> {
            barraEquipe.setValue(barraEquipe.getValue() - 0.1);
            mensagem.setText("Ação 2 executada!");
        });

        btnSair.addClickListener(event -> {
            Notification.show("Saindo da batalha...");
            getUI().ifPresent(ui -> ui.navigate(JornadaView.class));
        });

        spriteEquipe.addClassName("grupo-equipe");
        spriteInimigo.addClassName("grupo-inimigo");

        painelEquipe.addClassName("placa-equipe");
        painelInimigo.addClassName("placa-inimigo");

        painelEscolhas.addClassName("painel-escolhas");
        tela.addClassName("tela-batalha");
        arena.addClassName("arena");
        mensagem.addClassName("mensagem-batalha");

        arena.add(painelInimigo, spriteInimigo, spriteEquipe, painelEquipe);

        painelEscolhas.add(btnAtacar);
        painelEscolhas.add(btnRodada);
        painelEscolhas.add(btnSair);

        arena.add(painelEscolhas);
        arena.add(mensagem);

        tela.add(arena);

        add(tela);

    }

    Div criarPainelInimigo(String nome, int vida) {
        HorizontalLayout cabecalho = new HorizontalLayout(
                new Span("Goblin"),
                new Span("Nível"));

        Div placa = new Div(cabecalho, barraInimigo);
        placa.addClassName("placa-vida");
        return placa;
    }

    Div criarPainelEquipe(int vida) {
        HorizontalLayout cabecalho = new HorizontalLayout(
                new Span("Equipe"),
                new Span("Nível"));

        barraEquipe.setValue(vida / 100.0);
        barraEquipe.setThemeVariant(ProgressBarVariant.LUMO_ERROR, true);

        Div placa = new Div(cabecalho, barraEquipe);
        placa.addClassName("placa-vida");
        return placa;
    }

    void atualizarbarras() {
        if (!criaturas.isEmpty()) {
            double alvo = criaturas.get(0).getVidaAtual();
            barraInimigo.setValue(alvo / 100.0);
        }
        if (!equipeHerois.isEmpty()) {
            double alvo = equipeHerois.get(0).getVidaAtual();
            barraEquipe.setValue(alvo / 100.0);
        }
    }

}
