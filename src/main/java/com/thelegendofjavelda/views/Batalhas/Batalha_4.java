package com.thelegendofjavelda.views.Batalhas;

import com.thelegendofjavelda.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "batalha_4", layout = MainLayout.class)
public class Batalha_4 extends VerticalLayout {

    public Batalha_4() {
        // Configurações do layout
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

    }

}
