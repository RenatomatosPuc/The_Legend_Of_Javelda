package com.thelegendofjavelda;

import com.thelegendofjavelda.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {
    public MainView() {
        add(new H1("The Legend of Javelda!"));
        add(new Paragraph("Escolha uma opção para começar sua jornada!"));

        Button novoJogo = new Button("novo Jogo", event -> getUI().ifPresent(ui -> ui.navigate("guilda")));
        Button continuar = new Button("Continuar", event -> Notification.show("Em breve!"));
        Button dificuldade = new Button("Dificuldade");
        Button sair = new Button("Sair", event -> System.exit(0));
        add(continuar, novoJogo, dificuldade, sair);

        // add(new Button("Clique aqui", event -> add(new H2("Olá, mundo!"))));
    }
}
