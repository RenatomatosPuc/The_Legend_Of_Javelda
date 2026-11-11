package com.thelegendofjavelda.views;

import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;

@Route(value = "guilda", layout = MainLayout.class)
public class GuildaView extends VerticalLayout {
    public GuildaView() {
        add(new H1("Guilda"));

        TextField nome = new TextField("Nome");
        Select<String> classe = new Select<>();
        classe.setLabel("Classe");
        classe.setItems("Guerreiro", "Mago", "Arqueiro", "Clérigo");

        List<HeroResumo> membros = new ArrayList<>();
        Grid<HeroResumo> grade = new Grid<>();
        grade.addColumn(HeroResumo::nome).setHeader("Nome");
        grade.addColumn(HeroResumo::classe).setHeader("Classe");
        grade.addColumn(HeroResumo::nivel).setHeader("Nível");

        Button adicionar = new Button("Adicionar", event -> {
            if (nome.isEmpty() || classe.getValue() == null) {
                Notification.show("Informe o nome e a classe.");
                return;
            }
            membros.add(new HeroResumo(nome.getValue(), classe.getValue(), 1));
            grade.setItems(membros);
            nome.clear();
        });
        add(nome, classe, adicionar, grade);
    }

}
