package com.somamultiplos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SomaMultiplosApplication {

    public static void main(String[] args) {
        SpringApplication.run(SomaMultiplosApplication.class, args);
        SomaMultiplos somaMultiplos = new SomaMultiplos();

        Scanner ler = new Scanner(System.in);

        System.out.printf("Informe um número:\n");


        int numero = ler.nextInt();
        int total = 0;

        total = somaMultiplos.somarMultiplos(numero,3);
        total += somaMultiplos.somarMultiplos(numero,5);

        System.out.println("A soma dos multiplos de 3 e 5 dos numeros abaixo de "+numero+" é "+ total);
    }

}
