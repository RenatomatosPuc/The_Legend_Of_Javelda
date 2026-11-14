package com.thelegendofjavelda.views;

import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.thelegendofjavelda.persistence.CsvSaveService;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;

@Route(value = "inventario", layout = MainLayout.class)
public class InventarioView extends VerticalLayout {
    private final CsvSaveService service;

    public InventarioView(CsvSaveService service) {
        this.service = service;
        add(new H1("Inventário"));

        Grid<ArmaResumo> armas = new Grid<>();
        armas.addColumn(ArmaResumo::nome).setHeader("Nome");
        armas.addColumn(ArmaResumo::ataque).setHeader("Ataque");
        armas.setItems(
                new ArmaResumo("Espada de treino", 5),
                new ArmaResumo("Arco de madeira", 3));
        add(armas);

    }

    public void beforeEnter(BeforeEnterEvent event) {
        if (!service.existeCampanha()) {
            event.rerouteTo("menu");
        }
    }

}
