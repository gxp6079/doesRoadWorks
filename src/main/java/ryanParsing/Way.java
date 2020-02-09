package ryanParsing;

import java.util.ArrayList;

public class Way {
    private ArrayList<Node> nodes;
    private ArrayList<Node> intersections;
    private int speed;


    public Way(ArrayList<Node> nodes, ArrayList<Node> intersections) {
        this.nodes = nodes;
        this.intersections = intersections;
    }

    public ArrayList<Node> getNodes() {
        return this.nodes;
    }

    public ArrayList<Node> getIntersections() {
        return this.intersections;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
