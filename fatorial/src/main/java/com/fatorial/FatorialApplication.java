package com.fatorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FatorialApplication {

    public static void main(String[] args) {
        SpringApplication.run(FatorialApplication.class, args);

        Fatorial fatorial = new Fatorial();

        int numero = 5;
        int resultado = fatorial.calcularFatorial(numero);

        System.out.println("O fatorial de "+numero+" é "+resultado);
    }

}
