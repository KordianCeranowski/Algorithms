package com.company;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {

        Graph testowy = new Graph();

        testowy.dodajWierzcholki(4);
        testowy.dodajKrawedz(0, 1, 1);
        testowy.dodajKrawedz(0, 2, 1);
        testowy.dodajKrawedz(0, 3, 3);
        testowy.dodajKrawedz(3, 2, 4);

        testowy.Kruskal();

        // Zmieniałem ustawienia sortowania, ale w funkcji z której nie korzystałem ;/
        // Teraz już działa jak trzeba

    }
}
