package com.thelegendofjavelda.persistence;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class GameSaveService {
    private final GameSaveRepositorio repositorio;

    public GameSaveService(GameSaveRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public Optional<GameSave> continuar() {
        return repositorio.findById(1L);
    }

    public GameSave salvar(GameSave save) {
        return repositorio.save(save);
    }

}
