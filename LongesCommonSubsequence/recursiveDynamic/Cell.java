package recursiveDynamic;

public class Cell {
    private int value;
    private Arrow arrow;

    public Cell() {
        this.value = -1;
        this.arrow = Arrow.NONE;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Arrow getArrow() {
        return arrow;
    }

    public void setArrow(Arrow arrow) {
        this.arrow = arrow;
    }


}
