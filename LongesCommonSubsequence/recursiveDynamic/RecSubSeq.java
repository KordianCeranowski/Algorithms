package recursiveDynamic;

import java.util.ArrayList;

public class RecSubSeq{


    public void NWP(){this.NWP(true);}
    public void NWP(boolean czydrukować){
        this.startFindAll();
        if(czydrukować)
            this.printArray();
        this.startFinalSearch();
    }

    ArrayList<String> results;
    Cell[][] memo;
    String wordOne;
    String wordTwo;
    int sizeOne;
    int sizeTwo;

    public RecSubSeq(String wordOne, String wordTwo) {
        this.wordOne = wordOne;
        this.wordTwo = wordTwo;
        this.results = new ArrayList<String>();
        this.sizeOne = wordOne.length();
        this.sizeTwo = wordTwo.length();
        this.memo = new Cell[sizeOne][sizeTwo];
        for( int i = 0; i < sizeOne; i++){
            for( int j = 0; j < sizeTwo; j++){
                this.memo[i][j] = new Cell();
            }
        }
    }

    public void startFindAll(){
        Position currPos = new Position(wordOne.length()-1, wordTwo.length()-1);
        findAll(currPos);
    }
    private int findAll(Position position){

        int left;
        int up;
        if(outOfBounds(position))
            return 0;
        else if(arrayKnowsValue(position)) {
            return getValue(position);
        }
        else if(lettersAreMatching(position)) {
            saveValue(position, findAll(position.moveDiagonally()) + 1);
            saveArrow(position, Arrow.DIAGONAL);
            return getValue(position);
        }
        else{
            left = findAll(position.moveLeft());
            up = findAll(position.moveUp());
            if(up == left){
                saveArrow(position, Arrow.BOTH);
                saveValue(position, left);
                return left;
            }
            else if(up > left){
                saveArrow(position, Arrow.UP);
                saveValue(position, up);
                return up;
            }
            else{
                saveArrow(position, Arrow.LEFT);
                saveValue(position, left);
                return left;
            }
        }
    }

    public void startFinalSearch(){
        String result = "";
        Position position = new Position(wordOne.length()-1, wordTwo.length()-1);
        finalSearch(result, position);
        System.out.println("\nWyniki:");
        for(int i=0; i<results.size(); i++)
            System.out.println(results.get(i));
        System.out.println("-------------------------------------------------------------------------");
    }
    private void finalSearch(String result, Position position){

        if(position.isOutOfRange()){
            submit(result);
            return;
        }
        switch (getArrow(position)) {
            case DIAGONAL:
                result =  wordOne.charAt(position.getX()) + result;
                finalSearch(result, position.moveDiagonally());
                return;
            case UP:
                finalSearch(result, position.moveUp());
                return;
            case LEFT:
                finalSearch(result, position.moveLeft());
                return;
            case BOTH:
                finalSearch(result, position.moveLeft());
                finalSearch(result, position.moveUp());
                return;
            default:
                System.out.print("_");
        }
    }

    private boolean lettersAreMatching(Position currCell){
        int x = currCell.getX();
        int y = currCell.getY();
        if(wordOne.charAt(x) == wordTwo.charAt(y))
            return true;
        else
            return false;
    }

    private void submit(String result) {
        for(int i=0; i<results.size(); i++) {
            if (results.get(i).equals(result))
                return;
        }
        this.results.add(result);
        return;
    }

    private void saveValue(Position position, int value){
        memo[position.getX()][position.getY()].setValue(value);
    }

    private boolean arrayKnowsValue(Position position){
        if(memo[position.getX()][position.getY()].getValue() == -1)
            return false;
        else
            return true;
    }

    private int getValue(Position position){
        return memo[position.getX()][position.getY()].getValue();
    }

    private boolean outOfBounds(Position position){
        if(position.getX() == -1 || position.getY() == -1)
            return true;
        else
            return false;
    }

    public void printArray(){

        System.out.print("  ");
        for( int i = 0; i < sizeTwo; i++){
            System.out.print( " " + wordTwo.charAt(i) + " ");
        }
        System.out.println();

        for( int i = 0; i < sizeOne; i++){
            System.out.print(wordOne.charAt(i) + " ");
            for( int j = 0; j < sizeTwo; j++){
                switch (memo[i][j].getArrow()) {
                    case DIAGONAL:
                        System.out.print("\\");
                        break;
                    case UP:
                        System.out.print("|");
                        break;
                    case LEFT:
                        System.out.print("-");
                        break;
                    case BOTH:
                        System.out.print(">");
                        break;
                    default:
                        System.out.print("_");
                }
                if(memo[i][j].getValue() != -1)
                    System.out.print(memo[i][j].getValue() + " ");
                else
                    System.out.print("_ ");
            }
            System.out.println();
        }
    }

    public void saveArrow(Position position, Arrow arrow){
        memo[position.getX()][position.getY()].setArrow(arrow);
    }

    public Arrow getArrow(Position position){
        return memo[position.getX()][position.getY()].getArrow();
    }
}






