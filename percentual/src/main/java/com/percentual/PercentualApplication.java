package com.percentual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PercentualApplication {

    public static void main(String[] args) {
        SpringApplication.run(PercentualApplication.class, args);

        CalculoPercentual calculoPercentual = new CalculoPercentual();

        int totalEleitores = 1000;
        int votosValidos = 800;
        int votosBrancos = 150;
        int votosNulos = 50;

        double porcentagemVotosValidos = calculoPercentual.calcularPercentual(totalEleitores, votosValidos);
        System.out.println("Porcentagem de votos válidos: "+porcentagemVotosValidos+"%");

        double porcentagemVotosBrancos = calculoPercentual.calcularPercentual(totalEleitores, votosBrancos);
        System.out.println("Porcentagem de votos brancos: "+porcentagemVotosBrancos+"%");

        double porcentagemVotosNulos = calculoPercentual.calcularPercentual(totalEleitores, votosNulos);
        System.out.println("Porcentagem de votos nulos: "+porcentagemVotosNulos+"%");
    }

}
