package com.thelegendofjavelda.views.Batalhas;

import com.thelegendofjavelda.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "batalha_6", layout = MainLayout.class)
public class Batalha_6 extends VerticalLayout {

    public Batalha_6() {
        // Configurações da tela de batalha
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        // Adicione os componentes da batalha aqui
        // Por exemplo, você pode adicionar imagens, botões, etc.
    }

}
