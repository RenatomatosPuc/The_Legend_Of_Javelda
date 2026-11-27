package com.thelegendofjavelda.views;

import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Stream;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.thelegendofjavelda.persistence.CsvSaveService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
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

    public final CsvSaveService service;

    public EquipeView(CsvSaveService service) {
        addClassName("equipe-view");
        this.service = service;
        add(new H1("Equipe"));

        Grid<HeroResumo> grade = setUpGrid();
        grade.setSelectionMode(Grid.SelectionMode.SINGLE);
        grade.setRowsDraggable(true);
        HeaderRow gradeHeader = grade.prependHeaderRow();
        gradeHeader.join(grade.getColumns().get(0), grade.getColumns().get(1), grade.getColumns().get(2))
                .setText("Guilda");
        grade.addThemeVariants(GridVariant.ROW_STRIPES);

        Grid<HeroResumo> gradeEquipe = setUpGrid();
        HeaderRow gradeEquipeHeader = gradeEquipe.prependHeaderRow();
        gradeEquipeHeader
                .join(gradeEquipe.getColumns().get(0), gradeEquipe.getColumns().get(1), gradeEquipe.getColumns().get(2))
                .setText("Equipe");
        gradeEquipe.setRowsDraggable(true);
        gradeEquipe.addThemeVariants(GridVariant.ROW_STRIPES);

        ArrayList<HeroResumo> guilda = new ArrayList<>();
        ArrayList<HeroResumo> equipe = new ArrayList<>();
        try {
            equipe.addAll(
                    carregarMembros(HEROIS_CSV));
            guilda.addAll(
                    carregarMembros(GUILDA_CSV));
        } catch (IOException e) {
            Notification.show("Erro ao carregar membros da guilda: " + e.getMessage());
        }
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
            salvarEquipe(gradeEquipeView.getItems().toList());
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

            salvarEquipe(gradeEquipeView.getItems().toList());
            grade.setPartNameGenerator(item -> gradeEquipeView.getItems().toList().contains(item) ? "addEquipe" : null);
            grade.getDataProvider().refreshAll();
        });

        HorizontalLayout gradesLayout = new HorizontalLayout(grade, gradeEquipe);
        gradesLayout.setWidthFull();
        add(salvarButton, gradesLayout);
    }

    public static List<HeroResumo> carregarMembros(Path arquivoCsv) throws IOException {
        if (!Files.exists(arquivoCsv)) {
            return List.of();
        }

        List<String> linhas = Files.readAllLines(arquivoCsv, java.nio.charset.StandardCharsets.UTF_8);

        String cabecalhoEsperado = "id,nome,classe,nivel,vidaMaxima,vidaAtual,ataque,defesa";
        if (linhas.isEmpty() || !linhas.get(0).equals(cabecalhoEsperado)) {
            throw new IOException("Cabeçalho inválido no arquivo: " + arquivoCsv);
        }

        List<HeroResumo> membros = new ArrayList<>();
        for (int i = 1; i < linhas.size(); i++) {
            String linha = linhas.get(i);
            if (linha.isBlank()) {
                continue;
            }
            String[] campos = linha.split(",", -1);
            if (campos.length != 8) {
                throw new IOException("Formato inválido na linha " + (i + 1) + " do arquivo: " + arquivoCsv);
            }
            try {
                int id = Integer.parseInt(campos[0]);
                String nome = campos[1];
                String classe = campos[2];
                int nivel = Integer.parseInt(campos[3]);
                double vidaMaxima = Double.parseDouble(campos[4]);
                double vidaAtual = Double.parseDouble(campos[5]);
                double ataque = Double.parseDouble(campos[6]);
                double defesa = Double.parseDouble(campos[7]);
                membros.add(new HeroResumo(id, nome, classe, nivel, vidaMaxima, vidaAtual, ataque, defesa));
            } catch (NumberFormatException e) {
                throw new IOException("Erro ao analisar número no arquivo: " + arquivoCsv + " na linha " + (i + 1), e);
            }
        }
        return membros;
    }

    private static void salvarEquipe(List<HeroResumo> listaEquipe) {
        try (BufferedWriter writer = Files.newBufferedWriter(
                HEROIS_CSV,
                java.nio.charset.StandardCharsets.UTF_8,
                java.nio.file.StandardOpenOption.CREATE,
                java.nio.file.StandardOpenOption.TRUNCATE_EXISTING,
                java.nio.file.StandardOpenOption.WRITE)) {
            writer.write("id,nome,classe,nivel,vidaMaxima,vidaAtual,ataque,defesa");
            writer.newLine();
            for (HeroResumo heroi : listaEquipe) {
                writer.write(heroi.id() + "," +
                        heroi.nome() + "," +
                        heroi.classe() + "," +
                        heroi.nivel() + "," +
                        heroi.vidaMaxima() + "," +
                        heroi.vidaAtual() + "," +
                        heroi.ataque() + "," +
                        heroi.defesa());
                writer.newLine();
            }
        } catch (IOException e) {
            Notification.show("Não foi possível salvar a Equipe: " + e.getMessage());
            return;
        }

        Notification.show("Equipe salva com sucesso.");
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

    public void beforeEnter(BeforeEnterEvent event) {
        if (!service.existeCampanha()) {
            event.rerouteTo("/");
        }
    }

}
