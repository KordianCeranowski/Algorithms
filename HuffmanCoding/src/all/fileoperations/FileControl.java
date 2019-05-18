package all.fileoperations;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileControl {

    public static String input = "input.txt";
    public static String output = "output.txt";

    public static void setInput(InputType type){
        switch (type){
            case TEKST:
                input = "tekst-dlugi.txt";
                break;
            case OBRAZEK:
                input = "plik.dat";
                break;
            case WLASNY:
                input = "input.txt";
                break;
            case OUTPUT1:
                input = "output.txt";
                break;
        }
    }

    public static void setOutput(OutputType type) {
        switch (type) {
            case OUTPUT1:
                output = "output.txt";
                break;
            case OUTPUT2:
                output = "output2.txt";
                break;
        }
    }

    public static int[] createStatistics(){
        int[] array = new int[256];
        createStatistics(array);
        return array;
    }
    public static void createStatistics(int[] array){
        FileInputStream in;

        try {
            in = new FileInputStream(input);
            int sign;
            while ((sign = in.read()) != -1) {
                array[sign]++;
            }
            in.close();
        }
        catch(IOException io){
            System.out.println("Coś poszło nie tak");
        }
    }

    public static void clearFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(output));
            writer.write("");
            writer.close();
        }
        catch(IOException io){
            System.out.println("Błąd czyszczenia");
        }
    }

    public static void clearOutputFiles() {
        String memoOutput = output;

        setOutput(OutputType.OUTPUT1);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(output));
            writer.write("");
            writer.close();
        }
        catch(IOException io){
            System.out.println("Błąd czyszczenia");
        }
        setOutput(OutputType.OUTPUT2);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(output));
            writer.write("");
            writer.close();
        }
        catch(IOException io){
            System.out.println("Błąd czyszczenia");
        }

        output = memoOutput;
    }

//    public static void write(char sign){
//        write(("") + sign);
//    }
//
//    //public static void write(String text) {
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter(output, true));
//            writer.append(text);
//            writer.close();
//        }
//        catch(IOException io){
//            System.out.println("Błąd wpisywania");
//        }
//    }

    public static String parseToString(){
        String tekst = "";
        try {
            FileInputStream in = new FileInputStream(input);
            int sign;
            while ((sign = in.read()) != -1) {
                tekst += (char)sign;
            }
            in.close();
        }
        catch(IOException io){
            System.out.println("Coś poszło nie tak");
        }
        return tekst;
    }

    public static void writeISO_8859_1(String text) {
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(output, true), StandardCharsets.ISO_8859_1);
            writer.append(text);
            writer.close();
        }
        catch(IOException io){
            System.out.println("Błąd wpisywania");
        }
    }



}
