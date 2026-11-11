package com.thelegendofjavelda.views;

import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.button.Button;

@Route(value = "inventario", layout = MainLayout.class)
public class InventarioView extends VerticalLayout {
    public InventarioView() {
        add(new H1("Inventário"));
    }

}
