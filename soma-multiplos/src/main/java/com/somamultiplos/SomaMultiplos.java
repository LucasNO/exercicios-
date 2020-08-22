package com.somamultiplos;

public class SomaMultiplos {

    public int somarMultiplos(int numero, int multiplo){
        int total = 0;

        for(int i = 0; i < numero; i++){
            if(i % multiplo == 0 ){
                total += i;
            }
        }
        return total;
    }
}
