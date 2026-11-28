package com.thelegendofjavelda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.Batalha.Cena1;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        /*
         * Cena1 cena1 = new Cena1(new
         * com.thelegendofjavelda.persistence.CsvSaveService());
         * cena1.iniciar();
         */

    }
}
