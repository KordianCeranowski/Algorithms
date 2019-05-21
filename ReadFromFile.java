import java.io.*;
import java.util.Scanner;

public class ReadFromFile
{
    public String getText(String input)
    {
            String text = "";
            File file = new File(".\\src\\\\data\\" + input);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null)
                text += st;
        }
        catch(Exception e){
            System.out.println("Coś nie tak wpisano. Spróbuj \n tekst.txt \n text1.txt \n lub wzorzec1.txt");
        }
        return text;
    }
} 