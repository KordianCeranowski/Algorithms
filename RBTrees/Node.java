package RBTrees;

public class Node {
    private int value;
    private Node parent;
    private Node left;
    private Node right;



    private boolean isBlack;
    //false -- czerwony
    //true  -- czarny

    public Node(int value) {
        this.value = value;
        this.parent = null;
        this.left = null;
        this.right = null;
        this.isBlack = false;
    }

    public void setBlack() {
        isBlack = true;
    }

    public void setRed() {
        isBlack = false;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public boolean isRed() {
        return !isBlack;
    }

    public boolean getTurn(){
        //zwraca kierunek w którym węzeł skręca od ojca
        //false -- lewo
        //true  -- prawo
        if(this.getParent().getLeft() == this)
            return false;
        else
            return true;
    }

    public Node getGrandFather(){
        return this.getParent().getParent();
    }

    public void setUncleBlack(){
        if(this.getParent().getTurn()){
            if(this.getGrandFather() != null)
                if(this.getGrandFather().getLeft() != null)
                    this.getGrandFather().getLeft().setBlack();
        }
        else {
            if (this.getGrandFather() != null)
                if (this.getGrandFather().getRight() != null)
                    this.getGrandFather().getLeft().setBlack();
        }
    }

    public boolean isCurved(){
        //made by Kordian Ceranowski:>
        if(this.getTurn() == this.getParent().getTurn())
            return false;
        else return true;
    }

    public boolean isParentRed(){
        if(this.getParent() != null)
            return !this.getParent().isBlack();
        else return false;
    }

    public boolean isUncleRed(){
        if(this.getParent().getTurn()){
            if(this.getGrandFather() != null)
                if(this.getGrandFather().getLeft() != null)
                    return this.getGrandFather().getLeft().isRed();
        }
        else {
            if (this.getGrandFather() != null)
                if (this.getGrandFather().getRight() != null)
                    return this.getGrandFather().getRight().isRed();
        }
        return false;
    }

    public boolean isUncleBlack(){
        if(this.getParent().getTurn()){
            if(this.getGrandFather() != null)
                if(this.getGrandFather().getLeft() != null)
                    return this.getGrandFather().getLeft().isBlack();
        }
        else {
            if (this.getGrandFather() != null)
                if (this.getGrandFather().getRight() != null)
                    return this.getGrandFather().getRight().isBlack();
        }
        return true;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }

    public int getValue() {
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getBrother(){
        Node brother = (this.getTurn()) ? this.getParent().getLeft() : this.getParent().getRight();
        return brother;
    }

    public boolean isBrotherRed(){
        if(this.getBrother() == null)
            return false;
        return this.getBrother().isRed();
    }

    public boolean isBrotherBlack(){
        if(this.getBrother() == null)
            return true;
        return this.getBrother().isBlack();
    }

    public Node getOppositeNephew(){
        Node nephew = (this.getTurn()) ? this.getBrother().getLeft() : this.getBrother().getRight();
        return nephew;
    }

    public boolean isOppositeNephewBlack(){
         Node nephew = (this.getTurn()) ? this.getBrother().getLeft() : this.getBrother().getRight();
         if(nephew == null)
             return true;
         return nephew.isBlack();
    }

    public Node getSameSideNephew(){
        Node nephew = (this.getTurn()) ? this.getBrother().getRight() : this.getBrother().getLeft();
        return  nephew;
    }

    public boolean isSameSideNephewBlack(){
        Node nephew = (this.getTurn()) ? this.getBrother().getRight() : this.getBrother().getLeft();
        if(nephew == null)
            return true;
        return nephew.isBlack();
}

    public boolean isOppositeNephewRed(){
        return !isOppositeNephewBlack();
    }

    public boolean isSameSideNephewRed(){
        return !isSameSideNephewBlack();
    }

    public void destroyRelationWith(Node other){
        if(this.getParent() == other){
            if(this.getTurn())
                other.setRight(null);
            else
                other.setLeft(null);
            this.setParent(null);
        }
        else if(this.getLeft() == other){
            this.setLeft(null);
            other.setParent(null);
        }
        else if(this.getRight() == other){
            this.setRight(null);
            other.setParent(null);
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}

