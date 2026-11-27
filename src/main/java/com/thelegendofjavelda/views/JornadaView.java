package com.thelegendofjavelda.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.card.Card;
import com.vaadin.flow.component.card.CardVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.streams.DownloadHandler;
import com.thelegendofjavelda.persistence.CsvSaveService;

@Route(value = "jornada", layout = MainLayout.class)
public class JornadaView extends Div implements BeforeEnterObserver {
    private int mapaExibido = 0;
    private final CsvSaveService service;

    private final H1 title = new H1("Jornada");

    public JornadaView(CsvSaveService service) {
        addClassName("jornada-view");
        this.mapaExibido = 0;
        this.service = service;
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.add(title);

        Button proxButton = new Button("Próxima Página");
        Button prevButton = new Button("Página Anterior");
        HorizontalLayout navButtons = new HorizontalLayout(prevButton, proxButton);

        if (mapaExibido == 0) {
            prevButton.setEnabled(false);
        }
        if (mapaExibido == 2) {
            proxButton.setEnabled(false);
        }

        proxButton.addClickListener(event -> {
            mapaExibido++;
            mainLayout.removeAll();
            mainLayout.add(title, atualizarMapa(mapaExibido), navButtons);
            if (mapaExibido == 0) {
                prevButton.setEnabled(false);
            } else {
                prevButton.setEnabled(true);
            }
            if (mapaExibido == 2) {
                proxButton.setEnabled(false);
            } else {
                proxButton.setEnabled(true);
            }
        });
        prevButton.addClickListener(event -> {
            mapaExibido--;
            mainLayout.removeAll();
            mainLayout.add(title, atualizarMapa(mapaExibido), navButtons);
            if (mapaExibido == 0) {
                prevButton.setEnabled(false);
            } else {
                prevButton.setEnabled(true);
            }
            if (mapaExibido == 2) {
                proxButton.setEnabled(false);
            } else {
                proxButton.setEnabled(true);
            }
        });
        mainLayout.add(title, atualizarMapa(mapaExibido), navButtons);

        add(mainLayout);

        // HorizontalLayout Hlayout = new HorizontalLayout();
    }

    public Div atualizarMapa(int mapaExibido) {
        Div mapaLayout = new Div();
        mapaLayout.getStyle().set("display", "grid")
                .set("grid-template-columns", "1fr 1fr")
                .set("gap", "20px")
                .set("padding", "20px");
        switch (mapaExibido) {
            case 0:
                Card Batalha1 = new Card();
                DownloadHandler imageHandler = DownloadHandler.forClassResource(getClass(),
                        "/images/batalha_1.avif", "batalha_1.avif");
                Image imagem1 = new Image(imageHandler, "batalha_1.avif");
                Batalha1.addThemeVariants(CardVariant.ELEVATED);
                Batalha1.setMedia(imagem1);
                Batalha1.setTitle("Batalha 1");
                Batalha1.add(new H1("Card exemplo para escolha de batalhas"));

                Card Batalha2 = new Card();
                DownloadHandler imageHandler2 = DownloadHandler.forClassResource(getClass(),
                        "/images/batalha_2.avif", "batalha_2.avif");
                Image imagem2 = new Image(imageHandler2, "batalha_2.avif");
                Batalha2.addThemeVariants(CardVariant.ELEVATED);
                Batalha2.setMedia(imagem2);
                Batalha2.setTitle("Batalha 2");
                Batalha2.add(new H1("Card exemplo para escolha de batalhas"));

                Button batalha1btn = new Button("Iniciar Batalha 1");
                Button batalha2btn = new Button("Iniciar Batalha 2");
                Batalha1.addToFooter(batalha1btn);
                Batalha2.addToFooter(batalha2btn);

                batalha1btn.addClickListener(event -> iniciarBatalha(1));
                batalha2btn.addClickListener(event -> iniciarBatalha(2));
                mapaLayout.add(Batalha1, Batalha2);
                return mapaLayout;
            case 1:
                Card Batalha3 = new Card();
                DownloadHandler imageHandler3 = DownloadHandler.forClassResource(getClass(),
                        "/images/batalha_3.avif", "batalha_3.avif");
                Image imagem3 = new Image(imageHandler3, "batalha_3.avif");
                Batalha3.addThemeVariants(CardVariant.ELEVATED);
                Batalha3.setMedia(imagem3);
                Batalha3.setTitle("Batalha 3");
                Batalha3.add(new H1("Card exemplo para escolha de batalhas"));

                Card Batalha4 = new Card();
                DownloadHandler imageHandler4 = DownloadHandler.forClassResource(getClass(),
                        "/images/batalha_4.avif", "batalha_4.avif");
                Image imagem4 = new Image(imageHandler4, "batalha_4.avif");
                Batalha4.addThemeVariants(CardVariant.ELEVATED);
                Batalha4.setMedia(imagem4);
                Batalha4.setTitle("Batalha 4");
                Batalha4.add(new H1("Card exemplo para escolha de batalhas"));

                Button batalha3btn = new Button("Iniciar Batalha 3");
                Button batalha4btn = new Button("Iniciar Batalha 4");
                Batalha3.addToFooter(batalha3btn);
                Batalha4.addToFooter(batalha4btn);

                batalha3btn.addClickListener(event -> iniciarBatalha(3));
                batalha4btn.addClickListener(event -> iniciarBatalha(4));

                mapaLayout.add(Batalha3, Batalha4);
                return mapaLayout;
            case 2:
                Card Batalha5 = new Card();
                DownloadHandler imageHandler5 = DownloadHandler.forClassResource(getClass(),
                        "/images/batalha_5.avif", "batalha_5.avif");
                Image imagem5 = new Image(imageHandler5, "batalha_5.avif");
                Batalha5.addThemeVariants(CardVariant.ELEVATED);
                Batalha5.setMedia(imagem5);
                Batalha5.setTitle("Batalha 5");
                Batalha5.add(new H1("Card exemplo para escolha de batalhas"));

                Button batalha5btn = new Button("Iniciar Batalha 5");
                Batalha5.addToFooter(batalha5btn);
                batalha5btn.addClickListener(event -> iniciarBatalha(5));

                mapaLayout.add(Batalha5);
                return mapaLayout;
            default:
                Notification.show("Não há mais mapas para exibir. " + mapaExibido);
                return atualizarMapa(mapaExibido);
        }
    }

    public void iniciarBatalha(int batalhaId) {
        Notification.show("Iniciando Batalha...");
        switch (batalhaId) {
            case 1:
                getUI().ifPresent(ui -> ui.navigate("batalha1"));
                break;
            case 2:
                getUI().ifPresent(ui -> ui.navigate("batalha2"));
                break;
            case 3:
                getUI().ifPresent(ui -> ui.navigate("batalha3"));
                break;
            case 4:
                getUI().ifPresent(ui -> ui.navigate("batalha4"));
                break;
            case 5:
                getUI().ifPresent(ui -> ui.navigate("batalha5"));
                break;
            default:
                Notification.show("Batalha inválida.");
        }
    }

    public void beforeEnter(BeforeEnterEvent event) {
        if (!service.existeCampanha()) {
            event.rerouteTo("/");
        }
    }

}
