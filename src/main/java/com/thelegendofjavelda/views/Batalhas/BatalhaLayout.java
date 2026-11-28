package com.thelegendofjavelda.views.Batalhas;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.streams.DownloadHandler;

public class BatalhaLayout extends Div implements RouterLayout {
    DownloadHandler arenaHandler;

    public BatalhaLayout() {
        addClassName("layout-batalha");
        arenaHandler = DownloadHandler.forClassResource(getClass(), "/images/arena/arena.png", "arena.png");
        Image fundo = new Image(arenaHandler, "arena.png");
        fundo.addClassName("arena-fundo");
        fundo.setSizeFull();
        add(fundo);
    }

}
