package com.thelegendofjavelda.views;

import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.List;

import com.thelegendofjavelda.persistence.GuildaService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;

@Route(value = "guilda", layout = MainLayout.class)
public class GuildaView extends VerticalLayout {
    private static final Path DATA = Path.of("data");
    private static final Path GUILDA_CSV = DATA.resolve("guilda.csv");
    private static final Path HEROIS_CSV = DATA.resolve("heroes.csv");
    public GuildaView(GuildaService guildaService) {
        add(new H1("Guilda"));

        TextField nome = new TextField("Nome");
        Select<String> classe = new Select<>();
        classe.setLabel("Classe");
        classe.setItems("Guerreiro", "Mago", "Arqueiro", "Clérigo");

        // List<HeroResumo> membros = new ArrayList<>();
        Grid<HeroResumo> grade = new Grid<>();
        grade.addColumn(HeroResumo::nome).setHeader("Nome");
        grade.addColumn(HeroResumo::classe).setHeader("Classe");
        grade.addColumn(HeroResumo::nivel).setHeader("Nível");

        Button adicionar = new Button("Adicionar", event -> {
            if (nome.isEmpty() || classe.getValue() == null) {
                Notification.show("Informe o nome e a classe.");
                return;
            }

            guildaService.adicionar(new HeroResumo(nome.getValue(), classe.getValue(), 1));
            adicionarMembro(nome.getValue(), classe.getValue());
            grade.setItems(guildaService.listarMembros());
            nome.clear();
        });
        add(nome, classe, adicionar, grade);
    }

    // Implementação para adicionar um membro à guilda
    public void adicionarMembro(String nome, String classe) {
        int nivel = 1; // Nível inicial do herói
        String membro = nome + "," + classe + "," + nivel;
        try {
            Files.writeString(GUILDA_CSV, membro + System.lineSeparator(), java.nio.file.StandardOpenOption.APPEND);
        } catch (Exception e) {
            Notification.show("Erro ao adicionar membro à guilda: " + e.getMessage());
        }
    }

}
