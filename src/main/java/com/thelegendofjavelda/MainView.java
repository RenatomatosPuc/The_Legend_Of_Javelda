package com.thelegendofjavelda;

import com.vaadin.flow.component.button.Button;
<<<<<<< HEAD
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.H1;
=======
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
>>>>>>> 7b562d92db18062af7e50b076f94303f799cde53

@Route("")
public class MainView extends VerticalLayout {
    public MainView() {
<<<<<<< HEAD
        add(new Button("Clique aqui", event -> add(new H1("Olá, mundo!"))));
=======
        add(new H1("Bem-Vindo ao The Legend of Javelda!"));
        add(new Button("Clique aqui", event -> add(new H2("Olá, mundo!"))));
>>>>>>> 7b562d92db18062af7e50b076f94303f799cde53
    }
}
