package com.thelegendofjavelda.views;

import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.button.Button;

@Route(value = "guilda", layout = MainLayout.class)
public class GuildaView extends VerticalLayout {
    public GuildaView() {
        add(new H1("Guilda"));
        Button menu = new Button("Menu", event -> getUI().ifPresent(ui -> ui.navigate("")));
        Button equipe = new Button("Equipe", event -> getUI().ifPresent(ui -> ui.navigate("equipe")));
        Button inventario = new Button("Inventário", event -> getUI().ifPresent(ui -> ui.navigate("inventario")));

        add(menu, equipe, inventario);
    }

}
