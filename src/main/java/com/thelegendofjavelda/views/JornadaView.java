package com.thelegendofjavelda.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.card.Card;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

import com.thelegendofjavelda.persistence.CsvSaveService;

@Route(value = "jornada", layout = MainLayout.class)
public class JornadaView extends Div {
    private final CsvSaveService service;

    public JornadaView(CsvSaveService service) {
        this.service = service;
        add(new H1("Jornada"));
        Div layout = new Div();
        layout.getStyle().set("display", "grid")
                .set("grid-template-columns", "1fr 1fr")
                .set("gap", "20px")
                .set("padding", "20px");

        Card Batalha1 = new Card();
        Icon icone1 = new Icon(VaadinIcon.SWORD);
        icone1.setSize("50px");
        Batalha1.setMedia(icone1);
        Batalha1.setTitle("Batalha 1");
        Batalha1.add(new H1("Card exemplo para escolha de batalhas"));

        Card Batalha2 = new Card();
        Icon icone2 = new Icon(VaadinIcon.SHIELD);
        icone2.setSize("50px");
        Batalha2.setMedia(icone2);
        Batalha2.setTitle("Batalha 2");
        Batalha2.add(new H1("Card exemplo para escolha de batalhas"));

        layout.add(Batalha1, Batalha2);

        add(layout);

        // HorizontalLayout Hlayout = new HorizontalLayout();
    }

    public void beforeEnter(BeforeEnterEvent event) {
        if (!service.existeCampanha()) {
            event.rerouteTo("menu");
        }
    }

}
