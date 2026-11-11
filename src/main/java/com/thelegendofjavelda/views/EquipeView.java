package com.thelegendofjavelda.views;

import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.button.Button;

@Route(value = "equipe", layout = MainLayout.class)
public class EquipeView extends VerticalLayout {
    public EquipeView() {
        add(new H1("Equipe"));
        Button menu = new Button("Menu", event -> getUI().ifPresent(ui -> ui.navigate("")));
        Button guilda = new Button("Guilda", event -> getUI().ifPresent(ui -> ui.navigate("guilda")));
        Button inventario = new Button("Inventário", event -> getUI().ifPresent(ui -> ui.navigate("inventario")));

        add(menu, guilda, inventario);

    }

}
