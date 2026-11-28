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
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import com.vaadin.flow.router.Route;

@Route(value = "Batalha_1", layout = MainLayout.class)
public class Batalha_1 extends Div {
    VerticalLayout tela = new VerticalLayout();
    Div arena = new Div();

    public Batalha_1() {
        arena.addClassName("arena");
        Image fundo = new Image("images/Batalha/arena.png", "Arena");
        fundo.addClassName("arena-fundo");

        Div painelInimigo = criarPainelVida("Nome", 100);
        Image spriteInimigo = new Image("images/Batalha/inimigo.png", "Inimigo");

        Div painelEquipe = criarPainelVida("Nome", 100);
        Image spriteEquipe = new Image("images/Batalha/heroi.png", "Herói");

        arena.add(fundo, painelInimigo, spriteInimigo, spriteEquipe, painelEquipe);

        Div mensagem = new Div();
        mensagem.setText("mensagem atual");

        HorizontalLayout escolhas = new HorizontalLayout(
                new Button("Ação 1"),
                new Button("Ação 2"),
                new Button("Ação 3"));

        tela.add(arena, mensagem, escolhas);

    }

    Div criarPainelVida(String nome, int vida) {
        HorizontalLayout cabecalho = new HorizontalLayout(
                new Span(nome),
                new Span("Nível"));

        ProgressBar barra = new ProgressBar();
        barra.setValue(vida / 100.0);

        Div placa = new Div(cabecalho, barra);
        placa.addClassName("placa-vida");
        return placa;
    }

}
