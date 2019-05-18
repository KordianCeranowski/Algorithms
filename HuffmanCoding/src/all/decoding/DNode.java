package all.decoding;

public class DNode {
    private char sign;
    private DNode left;
    private DNode right;

    public DNode() {
    this.left = null;
    this.right = null;
    }

    public void makeLeftSon(){
        this.left = new DNode();
    }

    public void makeRightSon(){
        this.right = new DNode();
    }

    public void makeLeftLeaf(char sign){
        this.left = new DNode();
        this.left.sign = sign;
    }

    public void makeRightLeaf(char sign){
        this.right = new DNode();
        this.right.sign = sign;
    }

    public DNode getLeft() {
        return left;
    }

    public DNode getRight() {
        return right;
    }

    public char getSign() {
        return sign;
    }
}
