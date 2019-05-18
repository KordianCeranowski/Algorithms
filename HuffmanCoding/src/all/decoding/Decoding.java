package all.decoding;

import all.*;
import all.fileoperations.FileControl;
import all.fileoperations.InputType;
import all.fileoperations.OutputType;

import java.util.ArrayList;

public class Decoding {
    private String text;
    private String code;
    private String decodedMessage;

    private ArrayList<Code> codesArray;
    private DNode root;



    public Decoding(InputType input, OutputType output) {
        FileControl.setInput(input);
        FileControl.setOutput(output);

        FileControl.clearFile();

        System.out.println("\n\nDEKODOWANIE:\nRozpakowuję wiadomość z pliku \"" + input + "\" do pliku \"" + output  + "\"" );

        this.text = FileControl.parseToString();
        this.codesArray = new ArrayList<>();
        this.decodedMessage = "";

        this.createCodesArray();

        this.root = new DNode();
        this.rebuildTree();
        this.restoreMessage();

        System.out.println("Pomyślnie (oby) rozpakowano wiadomość");
    }

    private void createCodesArray(){
        int countOfZeroesAtTheEnd = Character.getNumericValue(text.charAt(0));
        text = text.substring(1);
        int j = 0;
        while((text.charAt(j) != ';' || text.charAt(j+1) != ';') && j<256){
            Code code = new Code((text.charAt(j)));
            codesArray.add(code);
            j++;
//            //bugtrap
//            if(text.charAt(j) == ';' && text.charAt(j+1) == ';')
//                System.out.println("tutaj");
//            if(j == 254)
//                System.out.println("prawie wszystkie chary");
        }

        text = text.substring(j+2); //ucinamy też separator

        j=0;
        String kod;
        for(int nrSlowa = 0; nrSlowa < codesArray.size(); nrSlowa++) {
            kod = "";
            while (text.charAt(j) != ',') {
                kod += text.charAt(j);
                j++;
            }
            codesArray.get(nrSlowa).setCode(kod);
            j++;
        }

        text = text.substring(j);

        String code = "";
        Buffer buffer = new Buffer();
        for(int i=0; i< text.length(); i++){
            code += buffer.takeCodeFromSign(text.charAt(i));
        }
        this.code = code.substring(0, code.length() - countOfZeroesAtTheEnd);
    }

    private void rebuildTree(){
        DNode root = this.root;
        for(Code code : codesArray){

            //bugtrap
//            if(code == codesArray.get(259))
//                System.out.println("wywala się na 260");

            String kod = code.getCode();
            for(int i=0; i<kod.length() -1; i++){    //-1 powoduje, że zatrzyma się przed ostatnim skrętem aby tam stworzyć DNode z charem;
                if(kod.charAt(i) == '0'){
                    if(root.getLeft() == null)
                        root.makeLeftSon();
                    root = root.getLeft();
                }
                else{
                    if(root.getRight() == null)
                        root.makeRightSon();
                    root = root.getRight();
                }
            }
            if(kod.charAt(kod.length() -1) == '0'){
                root.makeLeftLeaf(code.getSign());
            }
            else{
                root.makeRightLeaf(code.getSign());
            }
            root = this.root;
        }
    }

    private void restoreMessage(){

        DNode root = this.root;
        for(int i=0; i<code.length(); i++){
            if(root.getLeft() == null && root.getRight() == null){
                decodedMessage += root.getSign();
                root = this.root;
                i--;
            }
            else if(code.charAt(i) == '0'){
                root = root.getLeft();
            }
            else {
                root = root.getRight();
            }
        }
        if(root.getLeft() == null && root.getRight() == null)
            decodedMessage += root.getSign();

        FileControl.writeISO_8859_1(decodedMessage);
    }
}
