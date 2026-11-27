package com.thelegendofjavelda.views;

import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
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
import org.apache.commons.csv.CSVRecord;

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
            try {
                carregarGuilda();
            } catch (IOException e) {
                Notification.show("Não foi possível carregar a Guilda: " + e.getMessage());
            }
        });

        Button remover = new Button("Dispensar membro");
        remover.addClickListener(event -> {
            Heroi selecionado = gradeHerois.asSingleSelect().getValue();
            if (selecionado == null) {
                Notification.show("Selecione um membro para remover.");
                return;
            }

            try {
                if (removerMembro(selecionado.getId())) {
                    Notification.show("Membro removido com sucesso.")
                            .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                }
            } catch (IOException e) {
                Notification.show("Não foi possível atualizar a Guilda: " + e.getMessage());
            }

            try {
                carregarGuilda();
            } catch (IOException e) {
                Notification.show("Não foi possível carregar a Guilda: " + e.getMessage());
            }
        });

        Button Listar = new Button("Listar membros");
        Listar.addClickListener(event -> {
            gradeHerois.setItems(guilda);
        });
        try {
            carregarGuilda();
            guilda = carregarGuilda();
        } catch (IOException e) {
            Notification.show("Não foi possível carregar a Guilda: " + e.getMessage());
        }
        HorizontalLayout btnLayout = new HorizontalLayout();
        btnLayout.add(adicionar, remover, Listar);
        add(nome, classe, btnLayout, gridLayout);
    }

    // Implementação para adicionar um membro à guilda
    public static void adicionarMembro(String nome, Classe classe) {
        int nivel = 1; // Nível inicial do herói
        String membro = nome + ",";
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
        double vidaMaxima = heroi.getVidaMaxima();
        double ataque = heroi.getAtaque();
        double defesa = heroi.getDefesa();
        guilda.add(heroi);

        double vidaAtual = vidaMaxima; // Vida atual inicial igual à vida máxima
        membro += classe + "," + nivel + "," + vidaMaxima + "," + vidaAtual;
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

    static boolean removerMembro(int selecionadoId) throws IOException {
        List<String> linhas = Files.readAllLines(GUILDA_CSV, java.nio.charset.StandardCharsets.UTF_8);

        if (linhas.isEmpty()) {
            Notification.show("Arquivo da guilda está vazio.");
            return false;
        }

        String cabecalho = "id,nome,classe,nivel,vidaMaxima,vidaAtual,ataque,defesa";

        if (!linhas.get(0).equals(cabecalho)) {
            Notification.show("Cabeçalho do arquivo da guilda está incorreto.");
            return false;
        }

        List<String> linhasMantidas = new ArrayList<>();
        linhasMantidas.add(linhas.get(0)); // Preserva o cabeçalho
        boolean encontrado = false;
        for (int i = 1; i < linhas.size(); i++) {
            String linha = linhas.get(i);
            int fimDoId = linha.indexOf(',');
            String textoId = linha.substring(0, fimDoId);
            int id = Integer.parseInt(textoId);
            if (id == selecionadoId) {
                encontrado = true;
                continue; // Pula o membro a ser removido
            }
            linhasMantidas.add(linha);
        }
        if (!encontrado) {
            Notification.show("Membro não encontrado.");
            return false;
        }
        try {
            Files.write(GUILDA_CSV, linhasMantidas, java.nio.charset.StandardCharsets.UTF_8);
        } catch (IOException e) {
            Notification.show("Não foi possível atualizar a Guilda: " + e.getMessage());
            return false;
        }
        return true;
    }

    public static List<Heroi> carregarGuilda() throws IOException {
        if (!Files.exists(GUILDA_CSV)) {
            return List.of();
        }

        List<String> linhas = Files.readAllLines(GUILDA_CSV, java.nio.charset.StandardCharsets.UTF_8);

        if (linhas.isEmpty()) {
            Notification.show("Arquivo da guilda está vazio.");
            return List.of();
        }

        String cabecalho = "id,nome,classe,nivel,vidaMaxima,vidaAtual,ataque,defesa";

        if (!linhas.get(0).equals(cabecalho)) {
            Notification.show("Cabeçalho do arquivo da guilda está incorreto.");
            return List.of();
        }

        List<Heroi> membros = new ArrayList<>();
        for (int i = 1; i < linhas.size(); i++) {
            String linha = linhas.get(i);

            if (linha.isBlank()) {
                continue; // Pula linhas em branco
            }
            String[] campos = linha.split(",", -1);

            if (campos.length != 8) {
                Notification.show("Linha " + (i + 1) + " do arquivo da guilda está corrompida.");
                continue; // Pula linhas com número incorreto de campos
            }
            String idTexto = campos[0];
            int id = Integer.parseInt(idTexto);
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
                    return List.of();
            }
            heroi.setNome(nome);
            heroi.setNivel(nivel);
            heroi.setVidaMaxima(vidaMaxima);
            heroi.setVidaAtual(vidaAtual);
            heroi.setAtaque(ataque);
            heroi.setDefesa(defesa);
            heroi.setId(id);
            membros.add(heroi);
        }
        return membros;

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
            event.rerouteTo("/");
        }
    }
}
