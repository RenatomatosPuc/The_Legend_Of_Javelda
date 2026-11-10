package com.thelegendofjavelda;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {
    public MainView() {
        add(new H1("Bem-Vindo ao The Legend of Javelda!"));
        add(new Button("Clique aqui", event -> add(new H2("Olá, mundo!"))));
    }
}
