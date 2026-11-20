package com.thelegendofjavelda.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;

import com.Arsenal.Itens.ColecaoUtilitario;
import com.Arsenal.Itens.Utilitario;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import com.vaadin.flow.router.Route;

@Route(value = "/loja/itens", layout = MainLayout.class)
public class LojaItens extends VerticalLayout {
    public LojaItens() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.addClassName("loja-layout");
        List<Utilitario> armas = new ColecaoUtilitario().getColecaoUtilitarios();
        Grid<Utilitario> grid = criarGrid();
        grid.setItems(armas);
        layout.add(grid);
        add(grid);
    }

    public Grid<Utilitario> criarGrid() {
        Grid<Utilitario> grid = new Grid<>(Utilitario.class);
        grid.setColumns("nome", "uso");
        grid.addClassName("loja-grid");
        return grid;
    }

}
