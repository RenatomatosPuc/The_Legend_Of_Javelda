package com.thelegendofjavelda.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.thelegendofjavelda.views.HeroResumo;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;

@Service
@VaadinSessionScope
public class GuildaService {
    private final List<HeroResumo> membros = new ArrayList<>();

    public void adicionar(HeroResumo heroi) {
        membros.add(heroi);
    }

    public List<HeroResumo> listarMembros() {
        return List.copyOf(membros);
    }

    public void removerMembro(HeroResumo selecionado) {
        membros.remove(selecionado);
    }
}