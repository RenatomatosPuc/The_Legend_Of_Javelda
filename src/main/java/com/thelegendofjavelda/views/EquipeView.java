package com.thelegendofjavelda.views;

import java.util.Set;
import java.util.HashSet;

import com.thelegendofjavelda.persistence.GuildaService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "equipe", layout = MainLayout.class)
public class EquipeView extends VerticalLayout {
    public EquipeView(GuildaService guildaService) {
        add(new H1("Equipe"));

        Grid<HeroResumo> grade = new Grid<>();
        Grid.Column<HeroResumo> colunaNome = grade.addColumn(HeroResumo::nome).setHeader("Nome");
        Grid.Column<HeroResumo> colunaClasse = grade.addColumn(HeroResumo::classe).setHeader("Classe");
        Grid.Column<HeroResumo> colunaNivel = grade.addColumn(HeroResumo::nivel).setHeader("Nível");
        grade.setSelectionMode(Grid.SelectionMode.MULTI);
        grade.setItems(guildaService.listarMembros());
        HeaderRow gradeHeader = grade.prependHeaderRow();
        gradeHeader.join(colunaNome, colunaClasse, colunaNivel).setText("Guilda");
        grade.setWidth("25%");
        
        

        Grid<HeroResumo> gradeEquipe = new Grid<>();
        Grid.Column<HeroResumo> colunaNomeEquipe = gradeEquipe.addColumn(HeroResumo::nome).setHeader("Nome");
        Grid.Column<HeroResumo> colunaClasseEquipe = gradeEquipe.addColumn(HeroResumo::classe).setHeader("Classe");
        Grid.Column<HeroResumo> colunaNivelEquipe = gradeEquipe.addColumn(HeroResumo::nivel).setHeader("Nível");
        HeaderRow gradeEquipeHeader = gradeEquipe.prependHeaderRow();
        gradeEquipeHeader.join(colunaNomeEquipe, colunaClasseEquipe, colunaNivelEquipe).setText("Equipe");
        gradeEquipe.setWidth("25%");

        Set<HeroResumo> membrosEquipe = new HashSet<>();

        Button adicionar = new Button("Adicionar à Equipe", event -> {
            Set<HeroResumo> selecionados = grade.getSelectedItems();
            membrosEquipe.addAll(selecionados);
            gradeEquipe.setItems(membrosEquipe);
            Notification.show("Selecionados: " + selecionados.size());
            grade.setPartNameGenerator(item -> 
                membrosEquipe.contains(item) ? "addEquipe" : null
            );
            grade.getDataProvider().refreshAll();
        });
        

        HorizontalLayout gradesLayout = new HorizontalLayout(grade, gradeEquipe);
        VerticalLayout layoutPrincipal = new VerticalLayout(adicionar);
        add(layoutPrincipal);
        add(gradesLayout);
    }

}
