package com.thelegendofjavelda.views.Batalhas;

import java.util.List;
import java.util.function.Supplier;

import org.jetbrains.kotlin.it.unimi.dsi.fastutil.ints.H;

import com.Batalha.Acao;
import com.Batalha.Batalha;
import com.Missao.Missao;
import com.Missao.MissaoFloresta;
import com.Personagens.Personagem;
import com.Personagens.Criaturas.Criatura;
import com.Personagens.Herois.Heroi;
import com.thelegendofjavelda.persistence.CsvSaveService;
import com.thelegendofjavelda.views.JornadaView;
import com.thelegendofjavelda.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
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
    List<Criatura> inimigosAtivos;
    List<Heroi> equipeAtiva;
    private int vida = 100;
    List<Criatura> criaturas;
    List<Heroi> equipeHerois;
    Batalha batalha;

    Div painelEscolhas = new Div();
    Div mensagem = new Div();
    Div painelInimigo;
    Div painelEquipe;

    Image spriteInimigo;
    Image spriteEquipe;

    Button btnAtacar = new Button("Atacar");
    Button btnHabilidade = new Button("Usar habilidade");
    Button btnItem = new Button("Usar item");
    Button btnSair = new Button("Fugir");

    private final ComboBox<Personagem> seletorAlvo = new ComboBox<>("Selecionar alvo");
    Button confirmarAlvo = new Button("Confirmar alvo");
    private Acao acaoPendente;

    public Batalha_1(CsvSaveService service) {
        // A missão gera criaturas novas; a equipe vem do save da campanha.
        Missao missao_1 = new MissaoFloresta(service.getDificuldade(), 100);

        criaturas = missao_1.getCriaturas();
        equipeHerois = service.getEquipe();
        batalha = new Batalha(equipeHerois, criaturas);

        // As barras são criadas antes dos painéis que as exibem.
        seletorAlvo.setVisible(false);
        confirmarAlvo.setVisible(false);
        painelEscolhas.add(seletorAlvo, confirmarAlvo);

        barraInimigo = new ProgressBar();
        barraEquipe = new ProgressBar();

        barraInimigo.setThemeVariant(ProgressBarVariant.LUMO_ERROR, true);
        barraEquipe.setThemeVariant(ProgressBarVariant.LUMO_ERROR, true);

        painelInimigo = criarPainelInimigo("Nome", 100);
        painelEquipe = criarPainelEquipe(100);

        spriteHandler = DownloadHandler.forClassResource(getClass(), "/images/arena/sprite_goblin.png",
                "sprite_goblin.png");
        spriteEquipeHandler = DownloadHandler.forClassResource(getClass(), "/images/arena/sprite_equipe.png",
                "sprite_equipe.png");

        spriteInimigo = new Image(spriteHandler, "sprite_goblin.png");
        spriteEquipe = new Image(spriteEquipeHandler, "sprite_equipe.png");

        mensagem.setText("mensagem atual");
        atualizarbarras();

        btnAtacar.addClickListener(event -> {
            prepararEscolhaDeAlvo(Acao.ATACAR);
            atualizarbarras();
            mensagem.setText("Ação 1 executada!");
        });

        btnHabilidade.addClickListener(event -> {
            prepararEscolhaDeAlvo(Acao.HABILIDADE);
            atualizarbarras();
            mensagem.setText("Ação 2 executada!");
        });

        btnItem.addClickListener(event -> {
            prepararEscolhaDeAlvo(Acao.ITEM);
            atualizarbarras();
            mensagem.setText("Ação 3 executada!");
        });

        btnSair.addClickListener(event -> {
            Notification.show("Saindo da batalha...");
            getUI().ifPresent(ui -> ui.navigate(JornadaView.class));
        });

        confirmarAlvo.addClickListener(event -> {
            // A ação só é executada depois que o jogador confirma um alvo.
            Personagem alvoSelecionado = seletorAlvo.getValue();
            if (alvoSelecionado == null) {
                Notification.show("Selecione um alvo antes de confirmar.");
                return;
            }
            batalha.executarAcaoHeroiAtual(acaoPendente, alvoSelecionado);
            Notification.show("Ação " + acaoPendente + " executada no alvo " + alvoSelecionado.getNome() + "!"
                    + alvoSelecionado.getVidaAtual());
            atualizarbarras();

            seletorAlvo.clear();
            seletorAlvo.setVisible(false);
            confirmarAlvo.setVisible(false);
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
        painelEscolhas.add(btnHabilidade);
        painelEscolhas.add(btnItem);
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
        // As barras refletem os valores atuais dos combatentes após uma ação.
        inimigosAtivos = batalha.getCriaturas();
        equipeAtiva = batalha.getHerois();
        if (!inimigosAtivos.isEmpty()) {
            double vidaAtual = inimigosAtivos.get(0).getVidaAtual();
            double vidaMaxima = inimigosAtivos.get(0).getVidaMaxima();
            barraInimigo.setValue(vidaAtual / vidaMaxima);
        }
        if (!equipeAtiva.isEmpty()) {
            double vidaAtualTotal = equipeHerois.stream()
                    .mapToDouble(Heroi::getVidaAtual)
                    .sum();
            double vidaMaximaTotal = equipeHerois.stream()
                    .mapToDouble(Heroi::getVidaMaxima)
                    .sum();
            barraEquipe.setValue(vidaAtualTotal / vidaMaximaTotal);
        }
    }

    private void prepararAcaoPendente(Acao acao) {
        acaoPendente = acao;
        List<Personagem> alvosDisponiveis = batalha.getAlvosValidos(acao, batalha.getHeroiAtual());
        seletorAlvo.setItems(alvosDisponiveis);
        seletorAlvo.setItemLabelGenerator(Personagem::getNome);
    }

    private void prepararEscolhaDeAlvo(Acao acao) {
        // Atualiza as opções do seletor conforme a ação escolhida.
        acaoPendente = acao;
        List<Personagem> alvosDisponiveis = batalha.getAlvosValidos(acao, batalha.getHeroiAtual());
        seletorAlvo.clear();
        seletorAlvo.setItems(alvosDisponiveis);

        boolean temAlvos = !alvosDisponiveis.isEmpty();
        seletorAlvo.setVisible(temAlvos);
        confirmarAlvo.setVisible(temAlvos);

        if (!temAlvos) {
            Notification.show("Não há alvos disponíveis para esta ação.");
        }
    }

}
