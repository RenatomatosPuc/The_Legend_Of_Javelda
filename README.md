<<<<<<< HEAD
# The_Legend_Of_Javelda

Projeto básico com **Vaadin 24 + Spring Boot 3.2** e Java 21.

## Como compilar e rodar

```bash
cd The_Legend_Of_Javelda
mvn clean install
mvn spring-boot:run

=======
# The-Legend-Of-Javelda
Jogo lendario de Renato, Vasco e Matheus

```text
The-Legend-Of-Javelda/
├── pom.xml
├── .gitignore
├── README.md
├── mvnw
├── mvnw.cmd
├── .mvn/
│   └── wrapper/
├── data/                         # CSV que vão ser gravados durante a execução
│   ├── herois.csv
│   ├── inventarios.csv
│   └── batalhas.csv
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── br/edu/pucrs/javaria/
│   │   │       ├── Application.java
│   │   │       ├── domain/
│   │   │       │   ├── personagem/
│   │   │       │   │   ├── Combatente.java
│   │   │       │   │   ├── Personagem.java
│   │   │       │   │   ├── Heroi.java
│   │   │       │   │   ├── Guerreiro.java
│   │   │       │   │   ├── Mago.java
│   │   │       │   │   ├── Arqueiro.java
│   │   │       │   │   ├── Clerigo.java
│   │   │       │   │   ├── Criatura.java
│   │   │       │   │   ├── Goblin.java
│   │   │       │   │   ├── Esqueleto.java
│   │   │       │   │   ├── Dragao.java
│   │   │       │   │   └── LichNulo.java
│   │   │       │   ├── item/
│   │   │       │   │   ├── Item.java
│   │   │       │   │   ├── Arma.java
│   │   │       │   │   ├── Armadura.java
│   │   │       │   │   └── Pocao.java
│   │   │       │   ├── inventario/
│   │   │       │   │   └── Inventario.java
│   │   │       │   ├── missao/
│   │   │       │   │   └── Missao.java
│   │   │       │   └── batalha/
│   │   │       │       ├── Batalha.java
│   │   │       │       ├── RegistroBatalha.java
│   │   │       │       └── Resultado.java
│   │   │       ├── service/
│   │   │       │   ├── GuildaService.java
│   │   │       │   ├── BatalhaService.java
│   │   │       │   └── RelatorioService.java
│   │   │       ├── persistence/
│   │   │       │   ├── CsvHeroiRepository.java
│   │   │       │   ├── CsvInventarioRepository.java
│   │   │       │   └── CsvBatalhaRepository.java
│   │   │       ├── exception/
│   │   │       └── ui/
│   │   │           ├── AppShell.java
│   │   │           ├── layout/
│   │   │           └── views/
│   │   │               ├── InicioView.java
│   │   │               ├── RecrutamentoView.java
│   │   │               ├── GuildaView.java
│   │   │               ├── InventarioView.java
│   │   │               ├── ArenaView.java
│   │   │               └── CronicasView.java
│   │   ├── resources/
│   │   │   └── application.properties
│   │   └── frontend/
│   └── test/
│       └── java/
│           └── br/edu/pucrs/javaria/
└── docs/                         # relatório, diagrama, etc.
>>>>>>> d37b7b8bb1c80d69f4708bd668cc39a53e229f29
```
