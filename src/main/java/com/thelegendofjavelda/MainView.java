package com.thelegendofjavelda;

import java.io.UncheckedIOException;
import java.util.Optional;

import com.thelegendofjavelda.persistence.CampanhaSalva;
import com.thelegendofjavelda.persistence.CsvSaveService;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {
    private Span status;

    public MainView(CsvSaveService csvSaveService) {
        add(new H1("The Legend of Javelda!"));
        add(new Paragraph("Escolha uma opção para começar sua jornada!"));

        status = new Span();
        status.setVisible(false);

        Button novoJogo = new Button("novo Jogo");
        novoJogo.setDisableOnClick(true);
        Button continuar = new Button("Continuar");
        continuar.addClickListener(event -> {
            try {
                Optional<CampanhaSalva> campanha = csvSaveService.carregarCampanha();
                if (campanha.isEmpty()) {
                    Notification.show("Não há campanha em andamento.");
                    continuar.setEnabled(false);
                    return;
                }
                getUI().ifPresent(ui -> ui.navigate("jornada"));
            } catch (UncheckedIOException | IllegalStateException e) {
                Notification.show("Erro ao carregar a campanha: " + e.getMessage());
                continuar.setEnabled(false);
            }
        });
        if (csvSaveService.existeCampanha()) {
            continuar.setEnabled(true);
        } else {
            continuar.setEnabled(false);
        }
        ComboBox<String> comboDificuldade = new ComboBox<>("Dificuldade");
        comboDificuldade.setLabel("Dificuldade");
        comboDificuldade.setPlaceholder("Selecione a dificuldade");
        comboDificuldade.setItems("Fácil", "Médio", "Difícil");
        Button sair = new Button("Sair", event -> System.exit(0));

        novoJogo.addClickListener(event -> {
            if (comboDificuldade.getValue() != null) {
                if (csvSaveService.existeCampanha()) {
                    confirmaNovoJogo().open();
                    confirmaNovoJogo().addConfirmListener(e -> {
                        csvSaveService.iniciarNovaCampanha(String.valueOf(comboDificuldade.getValue()));
                        getUI().ifPresent(ui -> ui.navigate("guilda"));
                    });
                    confirmaNovoJogo().addCancelListener(e -> {
                        novoJogo.setEnabled(true);
                    });
                } else {
                    try {
                        csvSaveService.iniciarNovaCampanha(String.valueOf(comboDificuldade.getValue()));
                        getUI().ifPresent(ui -> ui.navigate("guilda"));
                    } catch (UncheckedIOException e) {
                        Notification.show("Erro ao iniciar nova campanha: " + e.getMessage());
                        novoJogo.setEnabled(true);
                    }
                }
            } else {
                erroDificuldade().open();
                novoJogo.setEnabled(true);

            }
        });

        add(continuar, novoJogo, comboDificuldade, sair);

        // add(new Button("Clique aqui", event -> add(new H2("Olá, mundo!"))));
    }

    public static Notification erroDificuldade() {
        Notification erroDificuldade = new Notification();
        erroDificuldade.addThemeVariants(NotificationVariant.ERROR);
        Icon erroIcon = VaadinIcon.WARNING.create();
        HorizontalLayout erroLayout = new HorizontalLayout(erroIcon, new Text("Nenhuma dificuldade Selecionada!"));
        erroLayout.setAlignItems(Alignment.CENTER);
        erroDificuldade.add(erroLayout);
        erroDificuldade.setDuration(3000);
        erroDificuldade.setPosition(Position.BOTTOM_STRETCH);
        return erroDificuldade;
    }

    public static ConfirmDialog confirmaNovoJogo() {
        ConfirmDialog confirmaNovoJogo = new ConfirmDialog();
        confirmaNovoJogo.setHeader("Já existe uma campanha em andamento!");
        confirmaNovoJogo.setText("Deseja realmente começar um novo jogo?");
        confirmaNovoJogo.setCancelable(true);
        confirmaNovoJogo.setConfirmText("Sim");
        confirmaNovoJogo.setConfirmButtonTheme("error primary");
        return confirmaNovoJogo;
    }

}
