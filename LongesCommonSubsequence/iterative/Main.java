package iterative;

public class Main {

    public static void main(String[] args) {

        SubsequenceSearcher test = new SubsequenceSearcher("ACBA", "DBCD");
        test.fillArray();
        test.printArray();
        test.findLongest();


        SubsequenceSearcher test2 = new SubsequenceSearcher("ffbbdewerfwefrwe","dbbdfsfeerwffreweqwe");
        test2.fillArray();
        test2.printArray();
        test2.findLongest();

    }


}
