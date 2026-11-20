package com.thelegendofjavelda.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;

import com.Arsenal.Armaduras.Armadura;
import com.Arsenal.Armaduras.ColecaoArmadura;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import com.vaadin.flow.router.Route;

@Route(value = "/loja/armaduras", layout = MainLayout.class)
public class LojaArmaduras extends VerticalLayout {
    public LojaArmaduras() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.addClassName("loja-layout");
        List<Armadura> armas = new ColecaoArmadura().getColecaoArmadura();
        Grid<Armadura> grid = criarGrid();
        grid.setItems(armas);
        layout.add(grid);
        add(grid);
    }

    public Grid<Armadura> criarGrid() {
        Grid<Armadura> grid = new Grid<>(Armadura.class);
        grid.setColumns("nome", "ataque", "durabilidade");
        grid.addClassName("loja-grid");
        return grid;
    }

}
