package com.company;

public class BinaryTree {

    private Node root;

    private int counter;

    private boolean switchIsRight; // false = left, true = right

    public BinaryTree() {
        this.root = null;
        this.switchIsRight = false;
    }

    public void insert(int value){
        Node node = new Node(value);
        Node tempRoot = root;
        //int depth = 1;
        if(root == null)
            root = node;
        else {
            while(true) {
                if(node.getValue() < tempRoot.getValue()){
                    if(tempRoot.getLeft() == null) {
                        tempRoot.setLeft(node);
                        node.setParent(tempRoot);
                        //depth++;
                        //setSize(depth);
                        return;
                    }
                    else{
                        tempRoot = tempRoot.getLeft();
                        //depth++;
                    }
                }
                else if(node.getValue() > tempRoot.getValue()){
                    if(tempRoot.getRight() == null) {
                        tempRoot.setRight(node);
                        node.setParent(tempRoot);
                        //depth++;
                        //setSize(depth);
                        return;
                    }
                    else{
                        tempRoot = tempRoot.getRight();
                        //depth++;
                    }
                }
                else if(node.getValue() == tempRoot.getValue()){
                    if(switchIsRight) {
                        if (tempRoot.getRight() == null) {
                            tempRoot.setRight(node);
                            node.setParent(tempRoot);
                            switchIsRight = false;
                            //setSize(depth);
                            return;
                        } else {
                            tempRoot = tempRoot.getRight();
                            //depth++;
                        }
                    }
                    else {
                        if (tempRoot.getLeft() == null) {
                            tempRoot.setLeft(node);
                            node.setParent(tempRoot);
                            switchIsRight = true;
                            //setSize(depth);
                            return;
                        } else {
                            tempRoot = tempRoot.getLeft();
                            //depth++;
                        }
                    }
                }
            }
        }
    }

    public Node search(int value){return search(value, root);}     //default option
    public Node search(int value, Node customRoot){
        while(true){
            if( customRoot == null )
                return null;
            if( value == customRoot.getValue() )
                return customRoot;
            else if( value < customRoot.getValue() )
                customRoot = customRoot.getLeft();
            else if ( value > customRoot.getValue() )
                customRoot = customRoot.getRight();

        }
    }

    public boolean[] tablicaDwojkowa(int index, int głębokość){
        boolean tablica[] = new boolean[głębokość];
        for(int i = głębokość-1; i >= 0 ; i--){
            if(index%2 == 1) {
                tablica[i] = true;
                index--;
            }
            else tablica[i] = false;

            index=index/2;
        }
        return tablica;
    }

    public void drukuj(){
        System.out.println("\n-------------------------------------------------------------------------------\n");
        countLevels();
        int głębokość = counter;//size;
        int pierwszyOdstep = Util.odstep(głębokość);
        int odstep = Util.odstep(głębokość-1);

        for(int poziom = 0; poziom <głębokość; poziom++) {
            pierwszyOdstep = Util.odstep(głębokość-poziom);
            Util.spaces(pierwszyOdstep);
            for (int index = 0; index < Math.pow(2, poziom); index++) {
                if (getNode(index, poziom) != null)
                    System.out.print(getNode(index, poziom).getValue());
                else
                    System.out.print("_");
                Util.spaces(odstep);
            }
            System.out.println();
            odstep = pierwszyOdstep;
        }
        System.out.println("\n-------------------------------------------------------------------------------\n");
    }

    public Node getNode(int index, int głębokość){
        Node currentNode = root;
        boolean[] droga = tablicaDwojkowa(index, głębokość);
        for( int j = 0; j < głębokość; j++){
            if(droga[j])
                currentNode = currentNode.getRight();
            else
                currentNode = currentNode.getLeft();
            if(currentNode == null)
                return null;
            }
        return currentNode;
    }

    public Node treeMinimum(Node x){
        while (x.getLeft() != null)
            x= x.getLeft();
        return x;
    }

    public void delete(Node z){
        if(z.getLeft() == null && z.getRight() == null){
            if(z == root)
                root = null;
            else if (z == z.getParent().getLeft())
                z.getParent().setLeft(null);
            else if (z == z.getParent().getRight())
                z.getParent().setRight(null);
        }
        else if(z.getLeft() != null && z.getRight() != null){
            Node y = treeMinimum(z.getRight());
            //przepisz zawartość węzła z y do z
            z.setValue(y.getValue());
            //
            delete(y);
        }
        else if( z.getLeft() != null ) {
            z.getLeft().setParent(z.getParent());
            if (z.getParent() == root)
                root = z.getLeft();
            else if(z == z.getParent().getLeft())
                z.getParent().setLeft(z.getLeft());
                else
                z.getParent().setRight(z.getLeft());
        }
        else{
            z.getRight().setParent(z.getParent());
            if( z == root )
                root = z.getRight();
            else if( z == z.getParent().getLeft())
                z.getParent().setLeft(z.getRight());
            else
                z.getParent().setRight(z.getRight());
        }
    }

    public void countLevels(){
        counter = 0;
        countLevels(root, 1);
    }
    public void countLevels(Node currentRoot, int counter){
        if(currentRoot.getRight() != null)
            countLevels(currentRoot.getRight() , counter+1);
        if(currentRoot.getLeft() != null)
            countLevels(currentRoot.getLeft(), counter+1);
        if(counter > this.counter)
            this.counter = counter;
        return;
    }

    @Override
    public String toString() {
        return "BinaryTree{" +
                 root +
                 "\nGłębokość" +counter +
                '}';
    }


}
