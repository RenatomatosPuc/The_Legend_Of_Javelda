package com.thelegendofjavelda.views;

import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import com.Arsenal.Equipavel;
import com.Arsenal.InventarioJogador;
import com.Arsenal.InventarioPersonagem;
import com.thelegendofjavelda.persistence.CsvSaveService;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;

@Route(value = "inventario", layout = MainLayout.class)
public class InventarioView extends VerticalLayout {
    private final CsvSaveService service;
    private final Path INVENTARIO_CSV = Path.of("data").resolve("inventario.csv");
    private final CSVFormat formato = CSVFormat.DEFAULT.builder()
            .setHeader()
            .setSkipHeaderRecord(true)
            .get();

    public InventarioView(CsvSaveService service) {
        this.service = service;
        add(new H1("Inventário"));
        HorizontalLayout gridLayout = new HorizontalLayout();

        /*
         * Grid<InventarioJogador> gridGeral = new Grid<>(InventarioJogador.class,
         * false);
         * gridGeral.addColumn(InventarioJogador::getNome).setHeader("Nome");
         * gridGeral.addColumn(InventarioJogador::getAtaque).setHeader("Ataque");
         * gridGeral.setItems(
         * );
         * gridGeral.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);
         * add(gridGeral);
         */

        Grid<InventarioPersonagem> gridPersonagem = new Grid<>(InventarioPersonagem.class, false);
        gridPersonagem.addColumn(InventarioPersonagem::getArma).setHeader("Arma");
        gridPersonagem.addColumn(InventarioPersonagem::getArmadura).setHeader("Armadura");
        gridPersonagem.addColumn(InventarioPersonagem::getUtilitario).setHeader("Utilitário");
        HeaderRow headerRow = gridPersonagem.prependHeaderRow();
        headerRow
                .join(gridPersonagem.getColumns().get(0), gridPersonagem.getColumns().get(1),
                        gridPersonagem.getColumns().get(2))
                .setText("Inventário do Personagem");

    }

    public void beforeEnter(BeforeEnterEvent event) {
        if (!service.existeCampanha()) {
            event.rerouteTo("/");
        }
    }

}
