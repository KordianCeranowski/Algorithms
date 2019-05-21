import java.util.Scanner;

class Main
{
    public static void main(String[] args)
    {
        ReadFromFile test = new ReadFromFile();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwę pliku TEXT");
        String T = test.getText(scanner.next() + ".txt");
        System.out.println("Podaj nazwę pliku PATTERN");
        String P = test.getText(scanner.next() + ".txt");

        Naive.naive(P,T);
        RabinKarp.rabinKarp(P,T);
        KnuthMorrisPratt.KMP(P, T);
    }
}