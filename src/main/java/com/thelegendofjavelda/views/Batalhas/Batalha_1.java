package com.thelegendofjavelda.views.Batalhas;

import org.jetbrains.kotlin.it.unimi.dsi.fastutil.ints.H;

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
    DownloadHandler spriteHandler;
    DownloadHandler spriteEquipeHandler;
    ProgressBar barraInimigo;
    ProgressBar barraEquipe;
    private int vida = 100;

    public Batalha_1() {
        barraInimigo = new ProgressBar();
        barraEquipe = new ProgressBar();
        barraInimigo.setValue(vida / 100.0);
        barraInimigo.setThemeVariant(ProgressBarVariant.LUMO_ERROR, true);
        barraEquipe.setValue(vida / 100.0);
        barraEquipe.setThemeVariant(ProgressBarVariant.LUMO_ERROR, true);

        tela.addClassName("tela-batalha");
        arena.addClassName("arena");

        Div painelInimigo = criarPainelInimigo("Nome", 100);
        spriteHandler = DownloadHandler.forClassResource(getClass(), "/images/arena/sprite_goblin.png",
                "sprite_goblin.png");
        Image spriteInimigo = new Image(spriteHandler, "sprite_goblin.png");
        spriteInimigo.addClassName("grupo-inimigo");
        painelInimigo.addClassName("placa-inimigo");

        Div painelEquipe = criarPainelEquipe(100);
        spriteEquipeHandler = DownloadHandler.forClassResource(getClass(), "/images/arena/sprite_equipe.png",
                "sprite_equipe.png");
        Image spriteEquipe = new Image(spriteEquipeHandler, "sprite_equipe.png");
        spriteEquipe.addClassName("grupo-equipe");
        painelEquipe.addClassName("placa-equipe");

        arena.add(painelInimigo, spriteInimigo, spriteEquipe, painelEquipe);

        Div mensagem = new Div();
        mensagem.setText("mensagem atual");
        mensagem.addClassName("mensagem-batalha");

        Div painelEscolhas = new Div();
        painelEscolhas.addClassName("painel-escolhas");
        Button btn1 = new Button("Ação 1");
        Button btn2 = new Button("Ação 2");
        Button btn3 = new Button("Ação 3");
        btn1.addClickListener(event -> {
            barraInimigo.setValue(barraInimigo.getValue() - 0.1);
            mensagem.setText("Ação 1 executada!");
        });

        painelEscolhas.add(btn1);
        painelEscolhas.add(btn2);
        painelEscolhas.add(btn3);

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

}
