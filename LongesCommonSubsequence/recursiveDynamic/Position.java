package recursiveDynamic;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position moveLeft(){
        Position temp = new Position(this.getX(), this.getY() -1);
        return temp;
    }
    public Position moveUp(){
        Position temp = new Position(this.getX() -1, this.getY());
        return temp;
    }
    public Position moveDiagonally(){
        Position temp = new Position(this.getX() -1, this.getY() -1);
        return temp;
    }

    public boolean isOutOfRange(){
        if(this.getX() < 0 || this.getY() < 0)
            return true;
        else
            return false;
    }



}
