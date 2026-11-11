package com.thelegendofjavelda.views;

import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.Set;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;

@Route(value = "equipe", layout = MainLayout.class)
public class EquipeView extends VerticalLayout {
    public EquipeView() {
        add(new H1("Equipe"));

        Grid<HeroResumo> grade = new Grid<>();
        grade.addColumn(HeroResumo::nome).setHeader("Nome");
        grade.addColumn(HeroResumo::classe).setHeader("Classe");
        grade.addColumn(HeroResumo::nivel).setHeader("Nível");
        grade.setSelectionMode(Grid.SelectionMode.MULTI);
        grade.setItems();

        Button adicionar = new Button("Adicionar à Equipe", event -> {
            Set<HeroResumo> selecionados = grade.getSelectedItems();
            Notification.show("Selecionados: " + selecionados.size());
        });
        add(grade, adicionar);
    }

}
