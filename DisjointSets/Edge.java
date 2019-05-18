package com.company;

public class Edge {

    private Vertex vertexOne;
    private Vertex vertexTwo;
    private int value;

    public Edge(Vertex vertexOne, Vertex vertexTwo, int value) {
        this.vertexOne = vertexOne;
        this.vertexTwo = vertexTwo;
        this.value = value;
    }

    @Override
    public String toString() {
        return "(" + vertexOne.getKey() +
                "," + vertexTwo.getKey() + "){" + value + "}";
//                + "){" + value + "}";
    }

    public Vertex getVertexOne() {
        return vertexOne;
    }


    public Vertex getVertexTwo() {
        return vertexTwo;
    }


    public int getValue() {
        return value;
    }

}
