package com.thelegendofjavelda.persistence;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

@Service
public class CsvSaveService {
    private static final Path DATA = Path.of("data");
    private static final Path HEROIS_CSV = DATA.resolve("heroes.csv");
    private static final Path INVENTARIO_CSV = DATA.resolve("inventario.csv");
    private static final Path BATALHA_CSV = DATA.resolve("batalha.csv");

    private static final String HEROIS_HEADER = "id,nome,classe,nivel,vida_maxima,vida_atual,ataque,defesa,na_equipe";
    private static final String INVENTARIO_HEADER = "id,tipo,nome,ataque,defesa,quantidade,heroi_id";
    private static final String BATALHA_HEADER = "tipo,dificuldade,mapa_atual,batalha_atual,campanha_concluida,id_batalha,resultado";
    private static final Set<String> DIFICULDADES = Set.of("facil", "normal", "dificil");

    public boolean existeCampanha() {
        return Files.isRegularFile(HEROIS_CSV) && Files.isRegularFile(INVENTARIO_CSV)
                && Files.isRegularFile(BATALHA_CSV);
    }

    public CampanhaSalva iniciarNovaCampanha(String dificuldade) {
        if (dificuldade == null || !DIFICULDADES.contains(dificuldade.toLowerCase())) {
            throw new IllegalArgumentException("Escolha uma dificuldade válida: facil, normal ou dificil");
        }

        String estadoInicial = BATALHA_HEADER
                + System.lineSeparator()
                + "CAMPANHA," + dificuldade.toLowerCase() + ",1,1,false,0,0"
                + System.lineSeparator();

        try {
            Files.createDirectories(DATA);
            Files.writeString(HEROIS_CSV, HEROIS_HEADER + System.lineSeparator());
            Files.writeString(INVENTARIO_CSV, INVENTARIO_HEADER + System.lineSeparator());
            Files.writeString(BATALHA_CSV, estadoInicial);
            return new CampanhaSalva(dificuldade.toLowerCase(), 1, 1, false);
        } catch (IOException erro) {
            throw new UncheckedIOException(
                    "Não foi possivel criar os arquivos da campanha em " + DATA, erro);
        }

    }
}
