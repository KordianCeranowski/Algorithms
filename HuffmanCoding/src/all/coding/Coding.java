package all.coding;

import all.*;
import all.fileoperations.FileControl;
import all.fileoperations.InputType;
import all.fileoperations.OutputType;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Coding {

    private ArrayList<Node> tree;
    private int[] frequency;
    private Node root;
    private ArrayList<Code> codes;

    public Coding(InputType inputType, OutputType outputType) {

        FileControl.setInput(inputType);
        FileControl.setOutput(outputType);

        this.codes = new ArrayList<>();
        this.tree =  new ArrayList<>();
        this.frequency = FileControl.createStatistics();
        FileControl.clearFile();
        this.createBranches();
        this.sortTree();
        this.connectWholeTree();
        root = tree.get(0);
        this.createCodes();
        this.sortCodes();
    }

    private void createBranches(){
        for(int i = 0; i < 256; i++){
            if(frequency[i] != 0) {
                Node node = new Node((char) i, frequency[i]);
                tree.add(node);
            }
        }
    }

    private void sortTree(){
        Collections.sort(tree, new Comparator<Node>(){
            public int compare(Node nodeOne, Node nodeTwo){
                return Integer.valueOf(nodeOne.getValue()).compareTo(nodeTwo.getValue());
            }
        });
    }

    private void sortCodes(){
        Collections.sort(codes, new Comparator<Code>(){
            public int compare(Code codeOne, Code codeTwo){
                return Integer.valueOf(codeTwo.getFrequency()).compareTo(codeOne.getFrequency());
            }
        });
    }

    private void connectBranches(){
        Node result = new Node(tree.get(0), tree.get(1));
        tree.remove(0);
        tree.remove(0);
        tree.add(result);
    }

    private void connectWholeTree(){
        while(tree.size() != 1){
            connectBranches();
            sortTree();
        }
    }

    private void createCodes(){
        createCodes(root,"");
    }
    private void createCodes(Node node, String currentCode){
        if(node.getLeft() == null && node.getRight() == null){
            Code temp = new Code(node.getSign(), currentCode);
            temp.setFrequency(this.frequency[node.getSign()]);
            codes.add(temp);
            return;
        }
        else {
            if(node.getLeft() != null)
                createCodes(node.getLeft(), currentCode + "0");
            if(node.getRight() != null)
                createCodes(node.getRight(), currentCode + "1");
        }
    }

    public void printCodes(){
        System.out.println("\nKODY HUFFMANA: ");
        int i=1;
        for (Code code : codes) {
            System.out.println(i + ": " +code);
            i++;
        }
    }

    private  String getCode(char sign){
        for (Code code : codes) {
            if (code.getSign() == sign)
                return code.getCode();
        }
        System.out.println("Jednego z zakodowywancych znaków nie znaleziono w bazie kodów");
        return "Jednego z zakodowywancych znaków nie znaleziono w bazie kodów";
    }

    public void codeText(){
        FileInputStream in;
        Buffer buffer = new Buffer();
        String bytesToWrite;
        PercentViewer satisfaction = new PercentViewer(frequency);
        try {
            in = new FileInputStream(FileControl.input);
            int sign;
            while ((sign = in.read()) != -1) {
                String code = getCode((char)sign);
                buffer.add(code);
                bytesToWrite = buffer.drawByte();
                if(bytesToWrite != "")
                    FileControl.writeISO_8859_1(bytesToWrite);
                satisfaction.anotherOneDone();
                satisfaction.getIt();
            }

            FileControl.writeISO_8859_1(buffer.lastDraw());
        }
        catch(IOException io){
            System.out.println("Coś poszło nie tak z zapisem");
        }
    }
    public void codeTextUpgrade(){
        String prefix = "";
        int addedZeroes = (8 - (countCodedBits() % 8));
        prefix += addedZeroes;
        System.out.println(prefix);

        for(Code code : codes){
            prefix += code.getSign();
        }

        prefix += ";;";

        for(Code code : codes) {
            prefix += code.getCode();
            prefix += ",";
        }

        FileControl.writeISO_8859_1(prefix);

        codeText();
    }

    public int countCodedBits() {
        int bits = 0;
        for (char i = 0; i < frequency.length; i++)
            if (frequency[i] != 0)
                bits += frequency[i] * getCode(i).length();
        return bits;
    }

    public void getEfficiency(){
        float licznik = 0;
        float mianownik = 0;

        for(char i = 0; i < frequency.length; i++ ){
            if(frequency[i] != 0){
                licznik += frequency[i] * getCode(i).length();
                mianownik += frequency[i] * 8;
            }
        }

        System.out.println("\nSTATYSTYKI: ");
        System.out.println("Znaki w tekście zakodowanym: " + Math.ceil(licznik/8));
        System.out.println("Znaki w tekście odkodowanym: " + mianownik/8);
        System.out.printf("Zysk z kompresji:             %.2f wielkości początkowej", (1 - licznik/mianownik));
    }
}
