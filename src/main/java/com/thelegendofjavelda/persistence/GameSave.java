package com.thelegendofjavelda.persistence;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.List;

@Entity
public class GameSave {
    @Id
    private Long id = 1L;

    private String dificuldade;
    private String nomeGuilda;

    @ElementCollection
    private List<String> membrosId = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "saved_weapons", joinColumns = @JoinColumn(name = "save_id"))
    private List<ArmaSalva> weapons = new ArrayList<>();

    protected GameSave() {
    }

    public GameSave(String dificuldade, String nomeGuilda) {
        this.dificuldade = dificuldade;
        this.nomeGuilda = nomeGuilda;
    }

    public Long getId() {
        return id;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public String getNomeGuilda() {
        return nomeGuilda;
    }

    public List<String> getMembrosId() {
        return membrosId;
    }

    public List<ArmaSalva> getWeapons() {
        return weapons;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    public void setNomeGuilda(String nomeGuilda) {
        this.nomeGuilda = nomeGuilda;
    }

}
