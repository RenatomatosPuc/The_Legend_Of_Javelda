package com.thelegendofjavelda.views;

import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.jetbrains.kotlin.it.unimi.dsi.fastutil.booleans.A;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import com.Arsenal.InventarioPersonagem;
import com.Personagens.*;
import com.Personagens.Herois.*;
import com.thelegendofjavelda.persistence.CsvSaveService;

@Route(value = "guilda", layout = MainLayout.class)
public class GuildaView extends VerticalLayout {
    private static final Path DATA = Path.of("data");
    private static final Path GUILDA_CSV = DATA.resolve("guilda.csv");

    private final CSVFormat formato = CSVFormat.DEFAULT.builder()
            .setHeader()
            .setSkipHeaderRecord(true)
            .get();
    private final CsvSaveService service;
    private static List<Heroi> guilda = new ArrayList<>();

    public GuildaView(CsvSaveService service) {
        this.service = service;
        add(new H1("Guilda"));

        TextField nome = new TextField("Nome");
        Select<Classe> classe = new Select<>();
        classe.setLabel("Classe");
        classe.setItems(Classe.values());
        HorizontalLayout gridLayout = new HorizontalLayout();

        Grid<Heroi> gradeHerois = new Grid<>(Heroi.class, false);
        gradeHerois.addColumn(Heroi::getNome).setHeader("Nome");
        gradeHerois.addColumn(Heroi::getClasse).setHeader("Classe");
        gradeHerois.addColumn(Heroi::getNivel).setHeader("Nível");
        gradeHerois.setItemDetailsRenderer(INVENTARIO_RENDERER());
        gradeHerois.setSelectionMode(Grid.SelectionMode.SINGLE);

        // List<HeroResumo> membros = new ArrayList<>();
        /*
         * Grid<HeroResumo> grade = new Grid<>(HeroResumo.class, false);
         * grade.addColumn(HeroResumo::nome).setHeader("Nome");
         * grade.addColumn(HeroResumo::classe).setHeader("Classe");
         * grade.addColumn(HeroResumo::nivel).setHeader("Nível");
         * grade.setSelectionMode(Grid.SelectionMode.SINGLE);
         */
        gridLayout.add(gradeHerois);
        gridLayout.setWidthFull();

        Button adicionar = new Button("Recrutar membro", event -> {
            if (nome.isEmpty() || classe.getValue() == null) {
                Notification.show("Informe o nome e a classe.");
                return;
            }

            adicionarMembro(nome.getValue(), classe.getValue());

            nome.clear();
            carregarGuilda(formato);
        });

        Button remover = new Button("Dispensar membro");
        remover.addClickListener(event -> {
            Heroi selecionado = gradeHerois.asSingleSelect().getValue();
            if (selecionado == null) {
                Notification.show("Selecione um membro para remover.");
                return;
            }

            if (removerMembro(selecionado.getId(), formato)) {
                Notification.show("Membro removido com sucesso.");
            } else {
                Notification.show("Erro ao remover membro.");
            }

            carregarGuilda(formato);
        });

        Button Listar = new Button("Listar membros");
        Listar.addClickListener(event -> {
            gradeHerois.setItems(guilda);
            Notification
                    .show("Membros listados com sucesso. " + carregarGuilda(formato).size() + " membros carregados.");
        });
        carregarGuilda(formato);
        guilda = carregarGuilda(formato);
        HorizontalLayout btnLayout = new HorizontalLayout();
        btnLayout.add(adicionar, remover, Listar);
        add(nome, classe, btnLayout, gridLayout);
    }

    // Implementação para adicionar um membro à guilda
    public static void adicionarMembro(String nome, Classe classe) {
        int nivel = 1; // Nível inicial do herói
        String membro = nome + "," + classe + "," + nivel;
        double vidaMaxima = 0;
        double ataque = 0;
        double defesa = 0;
        Heroi heroi;
        switch (classe) {
            case GUERREIRO:
                heroi = new Guerreiro(nivel);
                break;
            case MAGO:
                heroi = new Mago(nivel);
                break;
            case ARQUEIRO:
                heroi = new Arqueiro(nivel);
                break;
            case CLERIGO:
                heroi = new Clerigo(nivel);
                break;
            default:
                Notification.show("Classe inválida.");
                return;
        }
        heroi.setNome(nome);
        vidaMaxima = heroi.getVidaMaxima();
        ataque = heroi.getAtaque();
        defesa = heroi.getDefesa();
        guilda.add(heroi);

        double vidaAtual = vidaMaxima; // Vida atual inicial igual à vida máxima
        membro += "," + vidaMaxima + "," + vidaAtual;
        membro += "," + ataque + "," + defesa;
        int id = Objects.hash(membro);
        heroi.setId(id);
        String linha = id + "," + membro;
        try {
            Files.writeString(GUILDA_CSV, linha + System.lineSeparator(), java.nio.file.StandardOpenOption.APPEND);
        } catch (Exception e) {
            Notification.show("Erro ao adicionar membro à guilda: " + e.getMessage());
        }
    }

    public static boolean removerMembro(int selecionadoId, CSVFormat formato) {
        List<List<String>> restantes = new ArrayList<>();
        boolean encontrado = false;
        try (CSVParser parser = CSVParser.parse(GUILDA_CSV, java.nio.charset.StandardCharsets.UTF_8, formato)) {
            for (CSVRecord record : parser) {
                int id = Integer.parseInt(record.get("id"));
                if (id == selecionadoId) {
                    encontrado = true;
                    continue; // Pula o membro a ser removido
                }
                List<String> campos = new ArrayList<>();
                for (String campo : record) {
                    campos.add(campo);
                }
                restantes.add(campos);
            }
        } catch (IOException e) {
            Notification.show("Não foi possível ler a Guilda: " + e.getMessage());
            return false;
        }
        if (!encontrado) {
            Notification.show("Membro não encontrado.");
            return false;
        }
        try (CSVPrinter printer = new CSVPrinter(
                Files.newBufferedWriter(
                        GUILDA_CSV,
                        java.nio.charset.StandardCharsets.UTF_8,
                        java.nio.file.StandardOpenOption.CREATE,
                        java.nio.file.StandardOpenOption.TRUNCATE_EXISTING,
                        java.nio.file.StandardOpenOption.WRITE),
                formato)) {

            printer.printRecord("id", "nome", "classe", "nivel", "vidaMaxima", "vidaAtual", "ataque", "defesa");

            for (List<String> campos : restantes) {
                printer.printRecord(campos);
            }
            return true;
        } catch (IOException e) {
            Notification.show("Não foi possível atualizar a Guilda: " + e.getMessage());
            return false;
        }
    }

    public static void carregarMembros(Grid<Heroi> grade, CSVFormat formato) {
        if (!Files.exists(GUILDA_CSV)) {
            return;
        }

        try (CSVParser parser = CSVParser.parse(GUILDA_CSV, java.nio.charset.StandardCharsets.UTF_8, formato)) {
            List<Heroi> membros = new ArrayList<>();
            for (CSVRecord record : parser) {
                int id = Integer.parseInt(record.get("id"));
                String nome = record.get("nome");
                String classe = record.get("classe");
                int nivel = Integer.parseInt(record.get("nivel"));
                double vidaMaxima = Double.parseDouble(record.get("vidaMaxima"));
                double vidaAtual = Double.parseDouble(record.get("vidaAtual"));
                double ataque = Double.parseDouble(record.get("ataque"));
                double defesa = Double.parseDouble(record.get("defesa"));

                /* membros.add(new Heroi(id, Classe.valueOf(classe))); */
            }
            grade.setItems(membros);
        } catch (Exception e) {
            Notification.show("Erro ao carregar membros da guilda: " + e.getMessage());
        }
    }

    public List<Heroi> carregarGuilda(CSVFormat formato) {
        if (!Files.exists(GUILDA_CSV)) {
            return null;
        }

        try (CSVParser parser = CSVParser.parse(GUILDA_CSV, java.nio.charset.StandardCharsets.UTF_8, formato)) {
            List<Heroi> membros = new ArrayList<>();
            for (CSVRecord record : parser) {
                String nome = record.get("nome");
                String classe = record.get("classe");
                int nivel = Integer.parseInt(record.get("nivel"));
                double vidaMaxima = Double.parseDouble(record.get("vidaMaxima"));
                double vidaAtual = Double.parseDouble(record.get("vidaAtual"));
                double ataque = Double.parseDouble(record.get("ataque"));
                double defesa = Double.parseDouble(record.get("defesa"));
                Heroi heroi;
                switch (Classe.valueOf(classe)) {
                    case GUERREIRO:
                        heroi = new Guerreiro(nivel);
                        break;
                    case MAGO:
                        heroi = new Mago(nivel);
                        break;
                    case ARQUEIRO:
                        heroi = new Arqueiro(nivel);
                        break;
                    case CLERIGO:
                        heroi = new Clerigo(nivel);
                        break;
                    default:
                        Notification.show("Classe inválida.");
                        return null;
                }
                heroi.setNome(nome);
                heroi.setNivel(nivel);
                heroi.setVidaMaxima(vidaMaxima);
                heroi.setVidaAtual(vidaAtual);
                heroi.setAtaque(ataque);
                heroi.setDefesa(defesa);

                membros.add(heroi);
            }
            return membros;
        } catch (Exception e) {
            Notification.show("Erro ao carregar membros da guilda: " + e.getMessage());
            return null;
        }
    }

    private static ComponentRenderer<InventarioFormLayout, Heroi> INVENTARIO_RENDERER() {
        return new ComponentRenderer<>(InventarioFormLayout::new, InventarioFormLayout::setInventario);
    }

    private static class InventarioFormLayout extends FormLayout {
        private final TextField arma = new TextField("Arma");
        private final TextField armadura = new TextField("Armadura");
        private final TextField item = new TextField("Item");

        public InventarioFormLayout() {
            Stream.of(arma, armadura, item).forEach(field -> {
                field.setReadOnly(true);
                add(field);
            });

            setResponsiveSteps(new ResponsiveStep("0", 3));
            setColspan(arma, 3);
            setColspan(armadura, 3);
            setColspan(item, 3);
        }

        public void setInventario(Heroi heroi) {
            try {
                arma.setValue(
                        String.valueOf(heroi.getInventarioPersonagem().getArma()) != null
                                ? heroi.getInventarioPersonagem().getArma().getNome()
                                : "");
                armadura.setValue(
                        String.valueOf(heroi.getInventarioPersonagem().getArmadura()) != null
                                ? heroi.getInventarioPersonagem().getArmadura().getNome()
                                : "");
                item.setValue(
                        String.valueOf(heroi.getInventarioPersonagem().getUtilitario()) != null
                                ? heroi.getInventarioPersonagem().getUtilitario().getNome()
                                : "");
            } catch (Exception e) {
                Notification.show("Erro ao carregar inventário: " + e.getMessage());
            }
        }
    }

    public void beforeEnter(BeforeEnterEvent event) {
        if (!service.existeCampanha()) {
            event.rerouteTo("menu");
        }
    }
}
