package com.thelegendofjavelda.views;

import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.Personagens.*;
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

    public GuildaView(CsvSaveService service) {
        this.service = service;
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
        grade.setSelectionMode(Grid.SelectionMode.SINGLE);

        Button adicionar = new Button("Adicionar membro", event -> {
            if (nome.isEmpty() || classe.getValue() == null) {
                Notification.show("Informe o nome e a classe.");
                return;
            }

            adicionarMembro(nome.getValue(), classe.getValue());

            nome.clear();
            carregarMembros(grade, formato);
        });

        Button remover = new Button("Remover membro");
        remover.addClickListener(event -> {
            HeroResumo selecionado = grade.asSingleSelect().getValue();
            if (selecionado == null) {
                Notification.show("Selecione um membro para remover.");
                return;
            }

            if (removerMembro(selecionado.id(), formato)) {
                Notification.show("Membro removido com sucesso.");
            } else {
                Notification.show("Erro ao remover membro.");
            }

            carregarMembros(grade, formato);
        });
        carregarMembros(grade, formato);
        HorizontalLayout btnLayout = new HorizontalLayout();
        btnLayout.add(adicionar, remover);
        add(nome, classe, btnLayout, grade);
    }

    // Implementação para adicionar um membro à guilda
    public static void adicionarMembro(String nome, String classe) {
        int nivel = 1; // Nível inicial do herói
        String membro = nome + "," + classe + "," + nivel;
        double vidaMaxima = 0;
        switch (classe.toLowerCase()) {
            case "guerreiro":
                vidaMaxima = 150; // depois mudar para getVidaMaxima() do heroi
                break;
            case "mago":
                vidaMaxima = 80; // depois mudar para getVidaMaxima() do heroi
                break;
            case "arqueiro":
                vidaMaxima = 100; // depois mudar para getVidaMaxima() do heroi
                break;
            case "clérigo":
                vidaMaxima = 120; // depois mudar para getVidaMaxima() do heroi
                break;
            default:
                Notification.show("Classe inválida.");
                return;
        }
        double vidaAtual = vidaMaxima; // Vida atual inicial igual à vida máxima
        membro += "," + vidaMaxima + "," + vidaAtual;
        double ataque = 0;
        double defesa = 0;
        switch (classe.toLowerCase()) {
            case "guerreiro":
                ataque = 20;
                defesa = 15;
                break;
            case "mago":
                ataque = 25;
                defesa = 10;
                break;
            case "arqueiro":
                ataque = 18;
                defesa = 12;
                break;
            case "clérigo":
                ataque = 15;
                defesa = 20;
                break;
        }
        membro += "," + ataque + "," + defesa;
        int id = Objects.hash(membro);
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

    public static void carregarMembros(Grid<HeroResumo> grade, CSVFormat formato) {
        if (!Files.exists(GUILDA_CSV)) {
            return;
        }

        try (CSVParser parser = CSVParser.parse(GUILDA_CSV, java.nio.charset.StandardCharsets.UTF_8, formato)) {
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
            grade.setItems(membros);
        } catch (Exception e) {
            Notification.show("Erro ao carregar membros da guilda: " + e.getMessage());
        }
    }

    public void beforeEnter(BeforeEnterEvent event) {
        if (!service.existeCampanha()) {
            event.rerouteTo("menu");
        }
    }
}
