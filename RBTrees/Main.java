package RBTrees;

public class Main {

    public static void main(String[] args) {
        BinaryTree drzewo = new BinaryTree();

//        drzewo.insertRB(4);
//        drzewo.insertRB(2);
//        drzewo.insertRB(8);
//        drzewo.insertRB(6);
//        drzewo.insertRB(12);
//        drzewo.insertRB(10);
//        drzewo.insertRB(14);
//        drzewo.drukuj();
//        drzewo.insertRB(15);
//        drzewo.drukuj();

 //       drzewo.deleteRB(14);
 //       drzewo.drukuj();

       for(int i=1; i<=10000000; i++)
           drzewo.insertRB(i);

        drzewo.searchPRO(1);
//4 2 8 6 12 10 14
        drzewo.recursiveTrip();
    }
}
