package com.fatorial;

public class Fatorial {

    public int calcularFatorial(int numero){
        int fatorial = 1;
        for (int i = 1; i <= numero; i++){
            fatorial *= i;
        }
        return fatorial;
    }
}
