package com.thelegendofjavelda.views.Batalhas;

import com.thelegendofjavelda.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "Batalha_3", layout = MainLayout.class)
public class Batalha_3 extends VerticalLayout {
    public Batalha_3() {
        // Configurações da tela de batalha
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        // Adicione os componentes da batalha aqui
        // Por exemplo, você pode adicionar imagens, botões, etc.
    }

}
