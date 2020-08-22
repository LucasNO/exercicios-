package com.bubblesort;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BubbleSortApplication {

    public static void main(String[] args) {
        SpringApplication.run(BubbleSortApplication.class, args);

        BubbleSort bubbleSort = new BubbleSort();

        int vetor[] = {5,3,2,4,7,1,0,6};

        vetor = bubbleSort.ordernar(vetor);

        System.out.println("Vetor ordenado");
        
        for(int i=0; i<vetor.length; i++){
            System.out.println("posicao " + (i+1) + " = " +vetor[i] );
        }
    }

}
