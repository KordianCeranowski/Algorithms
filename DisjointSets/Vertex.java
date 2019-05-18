package com.company;

public class Vertex {

    private int key;
    private Vertex p;
    private int rank;

    public Vertex(int key) {
        this.key = key;
        this.p = this;
        this.rank = 0;
    }

    public Vertex findSet(){
        if(this != this.p)
            return this.p.findSet();
        return this;
    }

    public void union(Vertex other){
        if(this.rank > other.rank)
            other.p = this;
        else{
            this.p = other;
            if(this.rank == other.rank)
                other.setRank(other.getRank() + 1);
        }
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getKey() {
        return key;
    }

    public Vertex getP() {
        return p;
    }

    @Override
    public String toString() {
        return ""+key;
//        if(this.p != this)
//            return key + "(" + rank + ")" + " --> " + p;
//        else
//            return key + "(" + rank + ")";
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setP(Vertex p) {
        this.p = p;
    }
}
