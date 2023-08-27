package com.mart.tienda.auxi;

import java.util.Random;

public class Generador {

    public static String generarId(int max){
        Random rand = new Random();
        StringBuilder str = new StringBuilder();
        for(int i=0;i<max;i++) {
            int x = rand.nextInt(10);
            str.append(x);
        }
        return str.toString();
    }

}
