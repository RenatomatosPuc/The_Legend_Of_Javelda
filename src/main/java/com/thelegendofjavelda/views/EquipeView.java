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

import com.Personagens.Herois.Arqueiro;
import com.Personagens.Herois.Classe;
import com.Personagens.Herois.Clerigo;
import com.Personagens.Herois.Guerreiro;
import com.Personagens.Herois.Heroi;
import com.Personagens.Herois.Mago;
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
    private Heroi arrastado;

    public final CsvSaveService service;

    public EquipeView(CsvSaveService service) {
        addClassName("equipe-view");
        this.service = service;
        add(new H1("Equipe"));

        Grid<Heroi> grade = setUpGrid();
        grade.setSelectionMode(Grid.SelectionMode.SINGLE);
        grade.setRowsDraggable(true);
        HeaderRow gradeHeader = grade.prependHeaderRow();
        gradeHeader.join(grade.getColumns().get(0), grade.getColumns().get(1), grade.getColumns().get(2))
                .setText("Guilda");
        grade.addThemeVariants(GridVariant.ROW_STRIPES);

        Grid<Heroi> gradeEquipe = setUpGrid();
        HeaderRow gradeEquipeHeader = gradeEquipe.prependHeaderRow();
        gradeEquipeHeader
                .join(gradeEquipe.getColumns().get(0), gradeEquipe.getColumns().get(1), gradeEquipe.getColumns().get(2))
                .setText("Equipe");
        gradeEquipe.setRowsDraggable(true);
        gradeEquipe.addThemeVariants(GridVariant.ROW_STRIPES);

        ArrayList<Heroi> guilda = new ArrayList<>();
        ArrayList<Heroi> equipe = new ArrayList<>();
        try {
            equipe.addAll(
                    carregarMembros(HEROIS_CSV));
            guilda.addAll(
                    carregarMembros(GUILDA_CSV));
        } catch (IOException e) {
            Notification.show("Erro ao carregar membros da guilda: " + e.getMessage());
        }
        GridListDataView<Heroi> gradeView = grade.setItems(guilda);
        GridListDataView<Heroi> gradeEquipeView = gradeEquipe.setItems(equipe);
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

    public static List<Heroi> carregarMembros(Path arquivoCsv) throws IOException {
        if (!Files.exists(arquivoCsv)) {
            return List.of();
        }

        List<String> linhas = Files.readAllLines(arquivoCsv, java.nio.charset.StandardCharsets.UTF_8);

        String cabecalhoEsperado = "id,nome,classe,nivel,vidaMaxima,vidaAtual,ataque,defesa";
        if (linhas.isEmpty() || !linhas.get(0).equals(cabecalhoEsperado)) {
            throw new IOException("Cabeçalho inválido no arquivo: " + arquivoCsv);
        }

        List<Heroi> membros = new ArrayList<>();
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
                Heroi heroi;
                switch (Classe.valueOf(classe)) {
                    case GUERREIRO:
                        heroi = new Guerreiro(nivel, nome);
                        break;
                    case MAGO:
                        heroi = new Mago(nivel, nome);
                        break;
                    case ARQUEIRO:
                        heroi = new Arqueiro(nivel, nome);
                        break;
                    case CLERIGO:
                        heroi = new Clerigo(nivel, nome);
                        break;
                    default:
                        Notification.show("Classe inválida.");
                        return List.of();
                }
                heroi.setNivel(nivel);
                heroi.setVidaMaxima(vidaMaxima);
                heroi.setVidaAtual(vidaAtual);
                heroi.setAtaque(ataque);
                heroi.setDefesa(defesa);
                heroi.setId(id);
                membros.add(heroi);
            } catch (NumberFormatException e) {
                throw new IOException("Erro ao analisar número no arquivo: " + arquivoCsv + " na linha " + (i + 1), e);
            }
        }
        return membros;
    }

    private static void salvarEquipe(List<Heroi> listaEquipe) {
        try (BufferedWriter writer = Files.newBufferedWriter(
                HEROIS_CSV,
                java.nio.charset.StandardCharsets.UTF_8,
                java.nio.file.StandardOpenOption.CREATE,
                java.nio.file.StandardOpenOption.TRUNCATE_EXISTING,
                java.nio.file.StandardOpenOption.WRITE)) {
            writer.write("id,nome,classe,nivel,vidaMaxima,vidaAtual,ataque,defesa");
            writer.newLine();
            for (Heroi heroi : listaEquipe) {
                writer.write(heroi.getId() + "," +
                        heroi.getNome() + "," +
                        heroi.getClasse() + "," +
                        heroi.getNivel() + "," +
                        heroi.getVidaMaxima() + "," +
                        heroi.getVidaAtual() + "," +
                        heroi.getAtaque() + "," +
                        heroi.getDefesa());
                writer.newLine();
            }
        } catch (IOException e) {
            Notification.show("Não foi possível salvar a Equipe: " + e.getMessage());
            return;
        }

        Notification.show("Equipe salva com sucesso.");
    }

    private static Grid<Heroi> setUpGrid() {
        Grid<Heroi> grade = new Grid<>();
        grade.addColumn(Heroi::getNome).setHeader("Nome");
        grade.addColumn(Heroi::getClasse).setHeader("Classe");
        grade.addColumn(Heroi::getNivel).setHeader("Nível");
        grade.addColumn(Heroi::getVidaMaxima).setHeader("Vida Máxima");
        grade.addColumn(Heroi::getVidaAtual).setHeader("Vida Atual");
        grade.addColumn(Heroi::getAtaque).setHeader("Ataque");
        grade.addColumn(Heroi::getDefesa).setHeader("Defesa");
        return grade;
    }

    public void beforeEnter(BeforeEnterEvent event) {
        if (!service.existeCampanha()) {
            event.rerouteTo("/");
        }
    }

}
