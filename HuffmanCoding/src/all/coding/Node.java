package all.coding;

public class Node{
    private int value;
    private char sign;
    private Node left;
    private Node right;

    public Node(char sign, int value) {
        this.value = value;
        this.sign = sign;
        this.left = null;
        this.right = null;
    }

    //do odkodowywania
    public Node(char sign) {
        this.sign = sign;
        this.left = null;
        this.right = null;
    }
    public void setValue(int value) {
        this.value = value;
    }

    public Node(Node left, Node right) {
        this.value = left.getValue() + right.getValue();
        this.left = left;
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public char getSign() {
        return sign;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }


}
