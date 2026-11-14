package com.thelegendofjavelda.views;

import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Stream;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.thelegendofjavelda.persistence.GuildaService;
import com.thelegendofjavelda.persistence.CsvSaveService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.grid.dnd.GridDropMode;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

@Route(value = "equipe", layout = MainLayout.class)
public class EquipeView extends VerticalLayout implements BeforeEnterObserver {
    private static final Path DATA = Path.of("data");
    private static final Path GUILDA_CSV = DATA.resolve("guilda.csv");
    private static final Path HEROIS_CSV = DATA.resolve("heroes.csv");
    private HeroResumo arrastado;

    private final CSVFormat formato = CSVFormat.DEFAULT.builder()
            .setHeader()
            .setSkipHeaderRecord(true)
            .get();
    public final CsvSaveService service;

    public EquipeView(GuildaService guildaService, CsvSaveService service) {
        this.service = service;
        add(new H1("Equipe"));

        Grid<HeroResumo> grade = setUpGrid();
        grade.setSelectionMode(Grid.SelectionMode.SINGLE);
        grade.setRowsDraggable(true);
        HeaderRow gradeHeader = grade.prependHeaderRow();
        gradeHeader.join(grade.getColumns().get(0), grade.getColumns().get(1), grade.getColumns().get(2))
                .setText("Guilda");

        Grid<HeroResumo> gradeEquipe = setUpGrid();
        HeaderRow gradeEquipeHeader = gradeEquipe.prependHeaderRow();
        gradeEquipeHeader
                .join(gradeEquipe.getColumns().get(0), gradeEquipe.getColumns().get(1), gradeEquipe.getColumns().get(2))
                .setText("Equipe");
        gradeEquipe.setRowsDraggable(true);

        ArrayList<HeroResumo> guilda = new ArrayList<>(carregarMembros(guildaService, grade, formato, GUILDA_CSV));
        ArrayList<HeroResumo> equipe = new ArrayList<>(
                carregarMembros(guildaService, gradeEquipe, formato, HEROIS_CSV));
        GridListDataView<HeroResumo> gradeView = grade.setItems(guilda);
        GridListDataView<HeroResumo> gradeEquipeView = gradeEquipe.setItems(equipe);
        grade.addDragStartListener(e -> {
            arrastado = e.getDraggedItems().get(0);
            grade.setDropMode(GridDropMode.ON_GRID);
            gradeEquipe.setDropMode(GridDropMode.ON_GRID);
        });
        grade.addDropListener(e -> {
            gradeView.addItem(arrastado);
            gradeEquipeView.removeItem(arrastado);
            salvarEquipe(gradeEquipeView.getItems().toList(), formato);
        });
        grade.addDragEndListener(e -> {
            arrastado = null;
            grade.setDropMode(null);
            gradeEquipe.setDropMode(null);
        });

        gradeEquipe.addDragStartListener(e -> {
            arrastado = e.getDraggedItems().get(0);
            grade.setDropMode(GridDropMode.ON_GRID);
            gradeEquipe.setDropMode(GridDropMode.ON_GRID);
        });
        gradeEquipe.addDropListener(e -> {
            if (equipe.contains(arrastado)) {
                Notification.show("O herói já está na equipe.");
                return;
            }
            gradeEquipeView.addItem(arrastado);
        });
        gradeEquipe.addDragEndListener(e -> {
            arrastado = null;
            grade.setDropMode(null);
            gradeEquipe.setDropMode(null);
        });

        Button salvarButton = new Button("Salvar Equipe");
        salvarButton.addClickListener(event -> {
            if (gradeEquipeView.getItems().toList().isEmpty()) {
                Notification.show("Nenhum membro selecionado para salvar.");
                return;
            }

            salvarEquipe(gradeEquipeView.getItems().toList(), formato);
            grade.setPartNameGenerator(item -> gradeEquipeView.getItems().toList().contains(item) ? "addEquipe" : null);
            grade.getDataProvider().refreshAll();
        });

        HorizontalLayout gradesLayout = new HorizontalLayout(grade, gradeEquipe);
        gradesLayout.setWidthFull();
        add(salvarButton, gradesLayout);
    }

    public static List<HeroResumo> carregarMembros(GuildaService guildaService, Grid<HeroResumo> grade,
            CSVFormat formato, Path arquivoCsv) {
        if (!Files.exists(arquivoCsv)) {
            return null;
        }

        try (CSVParser parser = CSVParser.parse(arquivoCsv, java.nio.charset.StandardCharsets.UTF_8, formato)) {
            List<HeroResumo> membros = new ArrayList<>();
            for (CSVRecord record : parser) {
                int id = Integer.parseInt(record.get("id"));
                String nome = record.get("nome");
                String classe = record.get("classe");
                int nivel = Integer.parseInt(record.get("nivel"));
                double vidaMaxima = Double.parseDouble(record.get("vidaMaxima"));
                double vidaAtual = Double.parseDouble(record.get("vidaAtual"));
                double ataque = Double.parseDouble(record.get("ataque"));
                double defesa = Double.parseDouble(record.get("defesa"));
                membros.add(new HeroResumo(id, nome, classe, nivel, vidaMaxima, vidaAtual, ataque, defesa));
            }
            return membros;
        } catch (Exception e) {
            Notification.show("Erro ao carregar membros: " + e.getMessage());
            return null;
        }
    }

    private static void salvarEquipe(List<HeroResumo> listaEquipe, CSVFormat formato) {
        if (!Files.exists(HEROIS_CSV)) {
            Notification.show("Arquivo da Equipe não encontrado.");
            return;
        }

        try (CSVPrinter printer = new CSVPrinter(
                Files.newBufferedWriter(
                        HEROIS_CSV,
                        java.nio.charset.StandardCharsets.UTF_8,
                        java.nio.file.StandardOpenOption.CREATE,
                        java.nio.file.StandardOpenOption.TRUNCATE_EXISTING,
                        java.nio.file.StandardOpenOption.WRITE),
                formato)) {

            printer.printRecord("id", "nome", "classe", "nivel", "vidaMaxima", "vidaAtual", "ataque", "defesa");

            for (HeroResumo heroi : listaEquipe) {
                printer.printRecord(heroi.id(), heroi.nome(), heroi.classe(), heroi.nivel(), heroi.vidaMaxima(),
                        heroi.vidaAtual(), heroi.ataque(), heroi.defesa());
            }
            Notification.show("Equipe salva com sucesso.");
        } catch (IOException e) {
            Notification.show("Não foi possível salvar a Equipe: " + e.getMessage());
        }
    }

    private static Grid<HeroResumo> setUpGrid() {
        Grid<HeroResumo> grade = new Grid<>();
        grade.addColumn(HeroResumo::nome).setHeader("Nome");
        grade.addColumn(HeroResumo::classe).setHeader("Classe");
        grade.addColumn(HeroResumo::nivel).setHeader("Nível");
        grade.addColumn(HeroResumo::vidaMaxima).setHeader("Vida Máxima");
        grade.addColumn(HeroResumo::vidaAtual).setHeader("Vida Atual");
        grade.addColumn(HeroResumo::ataque).setHeader("Ataque");
        grade.addColumn(HeroResumo::defesa).setHeader("Defesa");
        return grade;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (!service.existeCampanha()) {
            event.rerouteTo("menu");
        }
    }

}
