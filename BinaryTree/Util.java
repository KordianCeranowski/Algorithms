package com.company;

public class Util {
    static final void spaces(int ile){
        for(int i=0; i< ile; i++)
            System.out.print(" ");
    }

    static final int odstep(int glebokosc){
        int odstep = 1;
        for(int i=1; i<glebokosc; i++)
            odstep = odstep*2+1;
        return odstep;
    }
}


