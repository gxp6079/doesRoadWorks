package ryanParsing;

import java.util.ArrayList;

public class Way {
    private ArrayList<Node> nodes;
    private ArrayList<Node> intersections;
    private int color;


    public Way(ArrayList<Node> nodes, ArrayList<Node> intersections) {
        this.nodes = nodes;
        this.intersections = intersections;
    }
}
