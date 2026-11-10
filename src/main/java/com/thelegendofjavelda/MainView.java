package com.thelegendofjavelda;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.H1;

@Route("")
public class MainView extends VerticalLayout {
    public MainView() {
        add(new Button("Clique aqui", event -> add(new H1("Olá, mundo!"))));
    }
}
