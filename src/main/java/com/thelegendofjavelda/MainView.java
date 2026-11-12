package com.thelegendofjavelda;

import com.thelegendofjavelda.persistence.CsvSaveService;
import com.thelegendofjavelda.views.MainLayout;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
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
        
        ConfirmDialog confirmaNovoJogo = new ConfirmDialog();
        confirmaNovoJogo.setHeader("Já existe uma campanha em andamento!");
        confirmaNovoJogo.setText("Deseja realmente começar um novo jogo?");
        confirmaNovoJogo.setCancelable(true);
        confirmaNovoJogo.setConfirmText("Sim");
        confirmaNovoJogo.setConfirmButtonTheme("error primary");
        confirmaNovoJogo.addConfirmListener(event -> System.out.println("Novo jogo confirmado!")); // adicionar evento correto


        Button novoJogo = new Button("novo Jogo");
        novoJogo.setDisableOnClick(true);
        Button continuar = new Button("Continuar");
        continuar.addClickListener(event -> {
            if(csvSaveService.existeCampanha()) {
                csvSaveService.carregarCampanha();
                getUI().ifPresent(ui -> ui.navigate("jornada"));
            } else {
                Notification.show("Não há campanha em andamento.");
            }
        });
        if(csvSaveService.existeCampanha()) {
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
            if(comboDificuldade.getValue() != null) {
                if(csvSaveService.existeCampanha()) {
                    confirmaNovoJogo.open();
                } else {
                    csvSaveService.iniciarNovaCampanha(comboDificuldade.getValue());
                    getUI().ifPresent(ui -> ui.navigate("guilda"));
                }
            }
        });

        add(continuar, novoJogo, comboDificuldade, sair);

        // add(new Button("Clique aqui", event -> add(new H2("Olá, mundo!"))));
    }
}
