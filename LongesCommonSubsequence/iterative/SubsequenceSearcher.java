package iterative;

public class SubsequenceSearcher {

    private String word1;
    private String word2;
    private Cell array[][];

    public SubsequenceSearcher(String word1, String word2) {
    this.word1 = word1;
    this.word2 = word2;
    this.setArray(new Cell[word1.length() +1][word2.length() +1]);
    for( int i = 0; i < getWord1().length() +1; i++){
        for( int j = 0; j < getWord2().length() +1; j++){
            this.array[i][j] = new Cell();
        }
    }
}

    private String getWord1() {
        return word1;
    }
    private String getWord2() {
        return word2;
    }
    private void setArray(Cell[][] array) {
        this.array = array;
    }

    public void fillArray(){
        for( int i = 0; i < word1.length() +1; i++){
            for( int j = 0; j < word2.length() +1; j++){
                if( i == 0 || j == 0);
                else
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                        array[i][j].setArrow(Arrow.DIAGONAL);
                        array[i][j].setValue(array[i-1][j-1].getValue() +1);
                }
                else{
                    if(array[i-1][j].getValue() > array[i][j-1].getValue()) {
                        array[i][j].setValue(array[i - 1][j].getValue());
                        array[i][j].setArrow(Arrow.UP);
                    }
                    else {
                        array[i][j].setValue(array[i][j - 1].getValue());
                        array[i][j].setArrow(Arrow.LEFT);
                    }
                }
            }
        }
    }

    public void printArray(){

        System.out.print("     ");
        for( int i = 0; i < word2.length(); i++){
            System.out.print( " " + word2.charAt(i) + " ");
        }

        System.out.println("");
        System.out.print("  ");

        for( int i = 0; i < word1.length() +1; i++){
            for( int j = 0; j < word2.length() +1; j++){
                switch (array[i][j].getArrow()) {
                    case DIAGONAL:
                        System.out.print("\\");
                        break;
                    case UP:
                        System.out.print("|");
                        break;
                    case LEFT:
                        System.out.print("-");
                        break;
                    default:
                        System.out.print(".");
                }
                System.out.print(array[i][j].getValue() + " ");
            }
            System.out.println();
            if(i<word1.length())
            System.out.print(word1.charAt(i) + " ");
        }
    }

    public String findLongest(){
        String sequence = "";

        int tempX = word1.length();
        int tempY = word2.length();
        while(tempX != 0 && tempY != 0){
            switch (array[tempX][tempY].getArrow()) {
                case DIAGONAL:
                    sequence = word1.charAt(tempX - 1) + sequence;
                    tempX--;
                    tempY--;
                    break;
                case UP:
                    tempX--;
                    break;
                case LEFT:
                    tempY--;
                    break;
            }
        }
        System.out.println("\nWynik: ");
        System.out.println(sequence);
        System.out.println("-------------------------------------------------------------------------");
        return sequence;
    }

}
