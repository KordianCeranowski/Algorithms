package RBTrees;

public class BinaryTree {

    private Node root;
    private int counter;
    private boolean switchIsRight; // false = left, true = right


    public BinaryTree() {
        this.root = null;
        this.switchIsRight = false;
    }

    public void insertRB(int value) {
        Node node = new Node(value);
        this.insert(node);
        if (node.isParentRed())
            this.colorTheTree(node);

    }

    public void colorTheTree(Node node) {
        Node tempNode = node;
        if (tempNode == this.root || tempNode.getParent() == null || !(tempNode.isParentRed()))
            return;
        if (tempNode.isUncleRed())
            this.przypadekPierwszy(tempNode);
        else if (tempNode.isUncleBlack() && tempNode.isCurved())
            this.przypadekDrugi(tempNode);
        else if (tempNode.isUncleBlack() && !tempNode.isCurved())
            this.przypadekTrzeci(tempNode);
        this.root.setBlack();
    }

    private void przypadekPierwszy(Node node){
        node.getParent().setBlack();
        node.getGrandFather().setRed();
        node.setUncleBlack();

        colorTheTree(node.getGrandFather());
    }

    private void przypadekDrugi(Node node){
        boolean aTurn = node.getParent().getTurn();
        Node A = node.getParent();
        Node B = node;
        Node C = node.getGrandFather();
        Node T;

        if(aTurn)
            C.setRight(null);
        else
            C.setLeft(null);

        A.setParent(null);
        if(aTurn)
            A.setLeft(null);
        else
            A.setRight(null);

        B.setParent(null);
        if(aTurn)
            //made by Kordian Ceranowski:>
            B.setRight(null);
        else
            B.setLeft(null);

        if(aTurn)
            T = B.getRight();
        else
            T = B.getLeft();

        if(T != null)
            T.setParent(null);

        //wyczyszczono wszystkie więzi

        if(aTurn)
            C.setRight(B);
        else
            C.setLeft(B);

        B.setParent(C);
        if(aTurn)
            B.setRight(A);
        else
            B.setLeft(A);

        A.setParent(B);
        if(aTurn)
            A.setLeft(T);
        else
            A.setRight(T);

        if(T != null)
            T.setParent(A);



        this.przypadekTrzeci(A);
    }

    private void przypadekTrzeci(Node node){

        Node A = node;
        Node B = A.getParent();
        Node C = B.getParent();

        Node T = null;
        boolean bTurn = B.getTurn();
        if(bTurn) {
            if (B.getLeft() != null)
                T = B.getLeft();
        }
        else {
            if (B.getRight() != null)
                T = B.getRight();
        }
        Node D = null;
       if(C != root)
            D = C.getParent();



        //Usuwanie zależności

        B.setParent(null);
        if(bTurn){
            C.setRight(null);
            B.setLeft(null);
        }
        else{
            C.setLeft(null);
            B.setRight(null);
        }

        if(T != null)
            T.setParent(null);

        if(D != null){
            if(C.getTurn()) {
                D.setRight(B);
                B.setParent(D);
            }
            else {
                D.setLeft(B);
                B.setParent(D);
            }
        }

        C.setParent(B);
        if(root == C)
            root = B;

        if(bTurn) {
            B.setLeft(C);
            if (T != null) {
                C.setRight(T);
                T.setParent(C);
            }
        }
        else {
            B.setRight(C);
            if (T != null){
                C.setLeft(T);
                T.setParent(C);
            }
        }

        B.setBlack();
        C.setRed();

    }

    private void insert(Node node){
        //Node node = new Node(value);
        Node tempRoot = root;
        //int depth = 1;
        if(root == null) {
            root = node;
            root.setBlack();
        }
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
            //System.out.println("Głębokość: " + counter);
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

    public void searchPRO(int value){
        Node tempNode = root;
        int depth = 0;
        int blackHeight = 0;
        while(true) {
            if(tempNode.isBlack()) blackHeight++;
            depth++;
            if (tempNode.getValue() < value)
                if(tempNode.getRight() != null)
                    tempNode = tempNode.getRight();
                else
                    return;
            else if (tempNode.getValue() > value)
                if(tempNode.getLeft() != null)
                    tempNode = tempNode.getLeft();
                else
                    return;
            else{
                String kolor;
                if(tempNode.isBlack()) kolor = "BLACK";
                else kolor = "RED";
                System.out.println("Kolor: " + kolor + "\nGłębokość: " + depth + "\nCzarna wysokość: " + blackHeight);
                return;
            }
        }
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
        //System.out.println("\nLicznik " + counter);
        return;
    }

    @Override
    public String toString() {
        return "BinaryTree{" +
                 root +
                 "\nGłębokość" +counter +
                '}';
    }

    //--------------------------------------------------------//
    //--------------------DODATKOWY PKT-----------------------//
    //--------------------------------------------------------//

    private int countOfReds = 0;
    private int minDepth = 0;
    private int maxDepth = 0;

    public void setMinDepth(int minDepth) {
        if(minDepth < this.minDepth || this.minDepth == 0)
            this.minDepth = minDepth;
    }
    public void setMaxDepth(int maxDepth) {
        if(maxDepth > this.maxDepth)
            this.maxDepth = maxDepth;
    }

    public void resetStats(){
        this.countOfReds = 0;
        this.minDepth = 0;
        this.maxDepth = 0;
    }
    public void setStats(int depth){
        setMinDepth(depth);
        setMaxDepth(depth);
    }

    public void recursiveTrip(){
        resetStats();
        recursiveTrip(this.root, 0);

        System.out.println("\n--------RAPORT--------");
        System.out.println("Minimalna głębokość:  " + this.minDepth);
        System.out.println("Maksymalna głębokość: " + this.maxDepth);
        System.out.println("Ilość czerwonych    : " + this.countOfReds);
    }
    public void recursiveTrip(Node currentNode, int depth){

        if(currentNode == null) {
            setStats(depth);
            return;
        }
        if(currentNode.isRed())
            this.countOfReds++;
        recursiveTrip(currentNode.getLeft(), depth+1);
        recursiveTrip(currentNode.getRight(),depth+1);
    }


    //--------------------------------------------------------//
    //----------------------USUWANIE--------------------------//
    //--------------------------------------------------------//

    public void deleteRB(int value){
        System.out.println("USUWAM " + value);
        Node node = search(value);
            //zakładam, że nie usuwam roota
            //bo wtedy to nie wiem jak to, chyba po synach czy coś
        Node parent = node.getParent();
        boolean turn = node.getTurn();
        boolean needRepair = node.isBlack();

        delete(node);

        if(needRepair){
            Node currentNode = (turn) ? parent.getRight() : parent.getLeft();
            repairColor(currentNode);
        }
        drukuj();
    }

    public void repairColor(Node currentNode){
        if (currentNode == this.root || currentNode.getParent() == null)
            return;

        if(currentNode.isRed())
            przypadekUsuwanie0(currentNode);
        else if(currentNode.isBrotherRed())
            przypadekUsuwanie1(currentNode);
        else if(currentNode.isBrotherBlack() && currentNode.isSameSideNephewBlack() && currentNode.isOppositeNephewBlack())
            przypadekUsuwanie2(currentNode);
        else if(currentNode.isBrotherBlack() && currentNode.isSameSideNephewRed() && currentNode.isOppositeNephewBlack())
            przypadekUsuwanie3(currentNode);
        else if(currentNode.isBrotherBlack() && currentNode.isOppositeNephewRed())
            przypadekUsuwanie4(currentNode);
        return;
    }

    public void przypadekUsuwanie0(Node x){
        x.setBlack();
        repairColor(x);
        return;
    }

    public void przypadekUsuwanie1(Node x){
        Node I = x.getGrandFather();
        Node A = x.getParent();
        Node D = x.getBrother();
        boolean turnA = A.getTurn();

        Node C = x.getSameSideNephew();
        if(C != null)
            C.destroyRelationWith(D);

        A.destroyRelationWith(I);
        D.destroyRelationWith(A);

        //relacje zniszczone

        if(turnA)
            I.setRight(D);
        else
            I.setLeft(D);
        D.setParent(I);

        if(x.getTurn())
            D.setRight(A);
        else
            D.setLeft(A);
        A.setParent(D);

        if(x.getTurn())
            A.setLeft(C);
        else
            A.setRight(C);
        C.setParent(A);

        A.setRed();
        D.setBlack();
        repairColor(x);
        return;
    }

    public void przypadekUsuwanie2(Node x){
        Node B = x.getParent();
        Node D = x.getBrother();
        D.setRed();
        repairColor(B);
        return;
    }

    public void przypadekUsuwanie3(Node x){
        Node B = x.getParent();
        Node D = x.getBrother();
        Node C = x.getSameSideNephew();
        Node C2 = (x.getTurn()) ? C.getLeft() : C.getRight();

        B.destroyRelationWith(D);
        D.destroyRelationWith(C);
        if(C2 != null)
            C.destroyRelationWith(C2);

        //relacje zniszczone

        if(x.getTurn())
            B.setLeft(C);
        else
            B.setRight(C);
        C.setParent(B);

        if(x.getTurn())
            C.setLeft(D);
        else
            C.setRight(D);
        D.setParent(C);

        if(C2 != null){
            if(x.getTurn())
                D.setRight(C2);
            else
                D.setLeft(C2);
            C2.setParent(D);
        }

        C.setBlack();
        D.setRed();

        przypadekUsuwanie4(x);
    }

    public void przypadekUsuwanie4(Node x){
        Node I = x.getGrandFather();
        Node B = x.getParent();
        Node D = x.getBrother();
        Node C = x.getSameSideNephew();
        Node E = x.getOppositeNephew();
        boolean turnB = B.getTurn();

        I.destroyRelationWith(B);
        B.destroyRelationWith(D);
        D.destroyRelationWith(C);

        if(I != null) {
            if (turnB)
                I.setRight(D);
            else
                I.setLeft(D);
            D.setParent(I);
        }

        if(x.getTurn())
            D.setRight(B);
        else
            D.setLeft(B);
        B.setParent(D);

        if(x.getTurn())
            B.setLeft(C);
        else
            B.setRight(C);
        if(C != null)
            C.setParent(B);
    }


    //A to node wokół kórego obracamy
    public void obrotWPrawo(Node A){
        Node I = A.getParent();
        boolean turnA = A.getTurn();
        Node B = A.getLeft();
        Node C = B.getRight();

        I.destroyRelationWith(A);
        A.destroyRelationWith(B);
        if (C != null)
            B.destroyRelationWith(C);

        if(turnA)
            I.setRight(B);
        else
            I.setLeft(B);
        B.setParent(I);

        B.setRight(A);
        A.setParent(B);

        if(C!=null){
            A.setLeft(C);
            C.setParent(A);
        }
    }

}

