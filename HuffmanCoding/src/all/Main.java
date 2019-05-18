package all;


import all.coding.Coding;
import all.decoding.Decoding;
import all.fileoperations.FileControl;
import all.fileoperations.InputType;
import all.fileoperations.OutputType;

public class Main {
    public static void main(String[] args) {

        FileControl.clearOutputFiles();

        //TEKST      - tekst-dlugi.txt
        //OBRAZEK    - plik.dat
        //WLASNY     - input.txt        - krótki plik do testów
        //OUTPUT1    - output.txt       - do zapakowanej wiadomości
        //OUPUT2     - output2.txt      - do rozpakowanej wiadomości

        Coding kodowanie = new Coding(InputType.TEKST, OutputType.OUTPUT1);
        //drzewo.codeText();        //koduje bez prefixu - do testów długości
        kodowanie.codeTextUpgrade();
        kodowanie.printCodes();
        kodowanie.getEfficiency();

        new Decoding(InputType.OUTPUT1, OutputType.OUTPUT2);


        //DOWÓD NIERÓWNOŚCI CHARÓW  - Naprawione przez kodowanie w ISO_8859_1
//        FileControl.clearFile();
//        String bytesToWrite;
//        Buffer buffer = new Buffer();
//        for(int i = 0 ; i<1000; i++){
//            buffer.add("11000000");
//            bytesToWrite = buffer.drawByte();
//            FileControl.writeISO_8859_1(bytesToWrite);
//        }


//        System.out.println(new Buffer().what8bitItIs("00100011"));
//        System.out.println(new Buffer().takeCodeFromSign('#'));

    }

}

