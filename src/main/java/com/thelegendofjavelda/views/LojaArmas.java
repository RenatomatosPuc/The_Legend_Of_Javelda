package com.thelegendofjavelda.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;

import com.Arsenal.Armas.Arma;
import com.Arsenal.Armas.ColecaoArma;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import com.vaadin.flow.router.Route;

@Route(value = "/loja/armas", layout = MainLayout.class)
public class LojaArmas extends VerticalLayout {
    public LojaArmas() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.addClassName("loja-layout");
        List<Arma> armas = new ColecaoArma().getColecaoArma();
        Grid<Arma> grid = criarGrid();
        grid.setItems(armas);
        layout.add(grid);
        add(grid);
    }

    public Grid<Arma> criarGrid() {
        Grid<Arma> grid = new Grid<>(Arma.class);
        grid.setColumns("nome", "ataque", "durabilidade");
        grid.addClassName("loja-grid");
        return grid;
    }
    
}
