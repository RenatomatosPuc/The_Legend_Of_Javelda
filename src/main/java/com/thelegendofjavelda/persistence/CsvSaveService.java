package com.thelegendofjavelda.persistence;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CsvSaveService {
    private static final Path DATA = Path.of("data");
    private static final Path HEROIS_CSV = DATA.resolve("heroes.csv");
    private static final Path GUILDA_CSV = DATA.resolve("guilda.csv");
    private static final Path INVENTARIO_CSV = DATA.resolve("inventario.csv");
    private static final Path BATALHA_CSV = DATA.resolve("batalha.csv");

    private static final String HEROIS_HEADER = "id,nome,classe,nivel,vida_maxima,vida_atual,ataque,defesa";
    private static final String GUILDA_HEADER = "id,nome,classe,nivel,vida_maxima,vida_atual,ataque,defesa";
    private static final String INVENTARIO_HEADER = "id,tipo,nome,ataque,defesa,quantidade,heroi_id";
    private static final String BATALHA_HEADER = "tipo,dificuldade,mapa_atual,batalha_atual,campanha_concluida,id_batalha,resultado";
    private static final Set<String> DIFICULDADES = Set.of("fácil", "médio", "difícil");

    public boolean existeCampanha() {
        return Files.isRegularFile(HEROIS_CSV) && Files.isRegularFile(GUILDA_CSV) && Files.isRegularFile(INVENTARIO_CSV)
                && Files.isRegularFile(BATALHA_CSV);
    }

    public CampanhaSalva iniciarNovaCampanha(String dificuldade) {
        if (dificuldade == null || !DIFICULDADES.contains(dificuldade.toLowerCase())) {
            throw new IllegalArgumentException("Escolha uma dificuldade valida: facil, normal ou dificil");
        }

        String estadoInicial = BATALHA_HEADER
                + System.lineSeparator()
                + "CAMPANHA," + dificuldade.toLowerCase() + ",1,1,false,0,0"
                + System.lineSeparator();

        try {
            Files.createDirectories(DATA);
            Files.writeString(HEROIS_CSV, HEROIS_HEADER + System.lineSeparator());
            Files.writeString(GUILDA_CSV, GUILDA_HEADER + System.lineSeparator());
            Files.writeString(INVENTARIO_CSV, INVENTARIO_HEADER + System.lineSeparator());
            Files.writeString(BATALHA_CSV, estadoInicial);
            return new CampanhaSalva(dificuldade.toLowerCase(), 1, 1, false);
        } catch (IOException erro) {
            throw new UncheckedIOException(
                    "Não foi possivel criar os arquivos da campanha em " + DATA, erro);
        }

    }

    public Optional<CampanhaSalva> carregarCampanha() { // codigo gerado por VSCODE, verificar se está correto
        if (!existeCampanha()) {
            return Optional.empty();
        }

        try {
            validarCabecalho(HEROIS_CSV, HEROIS_HEADER);
            validarCabecalho(GUILDA_CSV, GUILDA_HEADER);
            validarCabecalho(INVENTARIO_CSV, INVENTARIO_HEADER);
            List<String> linhas = Files.readAllLines(BATALHA_CSV, StandardCharsets.UTF_8);
            validarPrimeiraLinha(BATALHA_CSV, linhas, BATALHA_HEADER);
            return lerEstadoCampanha(linhas);
        } catch (IOException erro) {
            throw new UncheckedIOException(
                    "Não foi possivel ler os arquivos da campanha em " + DATA, erro);
        }
    }

    private void validarCabecalho(Path arquivo, String cabecalhoEsperado) throws IOException {
        List<String> linhas = Files.readAllLines(arquivo, StandardCharsets.UTF_8);
        validarPrimeiraLinha(arquivo, linhas, cabecalhoEsperado);
    }

    private void validarPrimeiraLinha(Path arquivo, List<String> linhas, String cabecalhoEsperado) {
        if (linhas.isEmpty() || !linhas.get(0).equals(cabecalhoEsperado)) {
            throw new IllegalStateException("Arquivo " + arquivo + " corrompido ou com cabeçalho inválido.");
        }
    }

    private Optional<CampanhaSalva> lerEstadoCampanha(List<String> linhas) {
        CampanhaSalva encontrada = null;

        for (int i = 1; i < linhas.size(); i++) {
            String linha = linhas.get(i);
            if (linha.isBlank()) {
                continue;
            }
            String[] campos = linha.split(",", -1);
            if (campos.length != 7) {
                throw new IllegalStateException("Arquivo " + BATALHA_CSV + " corrompido ou com formato inválido.");
            }
            if (!campos[0].equals("CAMPANHA")) {
                continue;
            }
            if (encontrada != null) {
                throw new IllegalStateException("Arquivo " + BATALHA_CSV + " corrompido ou com formato inválido.");
            }
            String dificuldade = campos[1];
            if (!DIFICULDADES.contains(dificuldade)) {
                throw new IllegalStateException("Arquivo " + BATALHA_CSV + " corrompido ou com formato inválido.");
            }
            int mapa = numPositivo(campos[2], "mapa atual", i);
            int batalha = numPositivo(campos[3], "batalha atual", i);
            boolean concluida = booleano(campos[4], i);
            encontrada = new CampanhaSalva(dificuldade, batalha, mapa, concluida);
        }
        return Optional.ofNullable(encontrada);
    }

    private int numPositivo(String valor, String campo, int linha) {
        try {
            int numero = Integer.parseInt(valor);
            if (numero < 1) {
                throw new NumberFormatException("Valor não é positivo");
            }
            return numero;
        } catch (NumberFormatException e) {
            throw new IllegalStateException(
                    "Campo " + campo + " inválido na linha " + (linha + 1) + " de " + BATALHA_CSV, e);
        }
    }

    private boolean booleano(String valor, int linha) {
        if (valor.equalsIgnoreCase("true")) {
            return true;
        }
        if (valor.equalsIgnoreCase("false")) {
            return false;
        }
        throw new IllegalStateException("campanha_concluida inválido na linha " + (linha + 1) + " de " + BATALHA_CSV);
    }
}
