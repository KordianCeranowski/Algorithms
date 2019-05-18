    package com.company;

    import java.util.ArrayList;
    import java.util.Collections;

    public class Graph implements Cloneable{

        private int identifier_V = 0;
        public ArrayList<Vertex> V;
        public ArrayList<Edge> E;

        public Graph() {
            this.V = new ArrayList<>();
            this.E = new ArrayList<>();
        }

        public void dodajWierzcholki(int ile) {
            for(int i=0; i<ile; i++)
                this.dodajWierzcholek();
        }
        public void dodajWierzcholek(){
             V.add(new Vertex(identifier_V));
            identifier_V++;
        }

        public void dodajKrawedz(Vertex x, Vertex y, int value){
            E.add(new Edge(x, y, value));
            x.findSet().union(y.findSet());
        }

        public void dodajKrawedz(int X, int Y, int value){
            Vertex x = this.getV().get(X);
            Vertex y = this.getV().get(Y);
            E.add(new Edge(x, y, value));
            x.findSet().union(y.findSet());
        }

        public boolean saTejSamejSkladowejSpojnosci(Vertex x, Vertex y){
            if(x.findSet() == y.findSet())
                return true;
            else
                return false;
        }

        public ArrayList<Vertex> getV() {
            return V;
        }

        public Graph Kruskal() throws CloneNotSupportedException {
            System.out.println("Wierzchołki:\n\t " + this.V);
            System.out.println("Krawędzi przed:\n\t " + this.E);

            if(!this.isATree()){
                System.out.println("Przekazany graf nie jest drzewem :(");
                return null;
            }

            Graph graph = (Graph)this.clone();

            for (Vertex vertex: graph.V) {
                vertex.setP(vertex);
            }

            ArrayList<Edge> allEdges = new ArrayList<>();
            for (Edge edge:graph.E) {
                allEdges.add(edge);
            }
            graph.E.clear();


            while(!graph.isATree()){
                Collections.sort(allEdges, (o1, o2) -> Integer.valueOf(o1.getValue()).compareTo(o2.getValue()));
                for (Edge currEdge : allEdges) {
                    if(!graph.saTejSamejSkladowejSpojnosci(currEdge.getVertexOne(), currEdge.getVertexTwo())){
                        graph.dodajKrawedz(currEdge.getVertexOne(), currEdge.getVertexTwo(), currEdge.getValue());
                    }
                }
            }

            System.out.println("Krawędzi po:\n\t " + graph.E + "\n");
            return graph;
        }

//          poprawiłem ustawienia sortowania, ale w funkcji z której na końcu nie korzystałem ;/
//
//        private void sortEdges(){
//            Collections.sort(E, (o1, o2) -> Integer.valueOf(o2.getValue()).compareTo(o2.getValue()));
//        }

        private boolean isATree(){
            Vertex parent = this.V.get(0).findSet();
            for (Vertex vertex:this.V) {
                if(vertex.findSet() != parent)
                    return false;
            }
            return true;
        }

    }
