package com.thelegendofjavelda.views;

import com.Arsenal.Armas.Arma;
import com.thelegendofjavelda.persistence.CsvSaveService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.card.Card;
import com.vaadin.flow.component.card.CardVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.streams.DownloadHandler;

@Route(value = "loja", layout = MainLayout.class)
public class LojaView extends VerticalLayout implements BeforeEnterObserver {
    private final CsvSaveService service;

    public LojaView(CsvSaveService service) {
        this.service = service;
        H1 title = new H1("Loja");
        title.addClassName("loja-title");

        HorizontalLayout cardLayout = new HorizontalLayout();
        VerticalLayout mainLayout = new VerticalLayout();

        Card cardArmas = criarCard("Armas", "Compre armas para aumentar o ataque do seu personagem.",
                "/images/loja/Armas.png", "Armas.png");
        Card cardArmaduras = criarCard("Armaduras", "Compre armaduras para aumentar a defesa do seu personagem.",
                "/images/loja/armaduras.png", "armaduras.png");
        Card cardUtilitarios = criarCard("Utilitários",
                "Compre itens utilitários para curar ou aumentar a durabilidade dos equipamentos do seu personagem.",
                "/images/loja/itens.png", "itens.png");
        cardLayout.add(cardArmas, cardArmaduras, cardUtilitarios);
        mainLayout.add(title, cardLayout);
        add(mainLayout);
    }

    public Card criarCard(String titulo, String descricao, String imagemPath, String downloadFileName) {
        Card card = new Card();
        DownloadHandler imageHandler = DownloadHandler.forClassResource(getClass(),
                imagemPath, downloadFileName);
        Image imagem = new Image(imageHandler, downloadFileName);
        card.addThemeVariants(CardVariant.ELEVATED);
        card.setMedia(imagem);
        card.addClassName("loja-card");
        H1 cardTitle = new H1(titulo);
        cardTitle.addClassName("loja-card-title");
        card.add(cardTitle);
        Paragraph cardDescription = new Paragraph(descricao);
        cardDescription.addClassName("loja-card-description");
        card.add(cardDescription);
        Button acessarButton = new Button("Acessar Loja");
        acessarButton.addClassName("loja-card-button");
        acessarButton.addClickListener(event -> {
            Notification.show("Acesso à loja de " + titulo + " realizado com sucesso!");
            if (titulo.equals("Armas")) {
                getUI().ifPresent(ui -> ui.navigate(LojaArmas.class));
            } else if (titulo.equals("Armaduras")) {
                // Navegar para a loja de armaduras
            } else if (titulo.equals("Utilitários")) {
                // Navegar para a loja de utilitários
            }
        });
        card.addToFooter(acessarButton);
        return card;
    }

    public VerticalLayout lojaArmas() {
        VerticalLayout mainLayout = new VerticalLayout();
        Grid<Arma> lojaGrid = new Grid<>();
        return mainLayout;
    }

    public void beforeEnter(BeforeEnterEvent event) {
        if (!service.existeCampanha()) {
            event.rerouteTo("/");
        }
    }

}
