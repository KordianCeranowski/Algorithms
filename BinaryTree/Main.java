package com.company;

import javax.swing.event.MouseInputListener;

public class Main {

    public static void main(String[] args) {
	// write your code here
        BinaryTree dzewo = new BinaryTree();

        dzewo.insert(5);
        dzewo.insert(3);
        dzewo.insert(6);
        dzewo.insert(7);
        dzewo.insert(8);
        dzewo.insert(4);
        dzewo.insert(20);
        dzewo.insert(4);
        dzewo.insert(4);
        dzewo.insert(19);

//        System.out.println(
//                dzewo.getNode(3,2));

        dzewo.drukuj();

        dzewo.delete(dzewo.search(6));

        dzewo.drukuj();
        dzewo.countLevels();
        //System.out.println("\n"+Util.odstep(3));

    }
}
