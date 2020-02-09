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

    public double getstartx() {
        return this.intersections.get(0).getLatitude();
    }

    public double getstarty() {
        return this.intersections.get(0).getLongitude();
    }

    public double getendx() {
        return this.intersections.get(1).getLatitude();
    }

    public double getendy() {
        return this.intersections.get(1).getLongitude();
    }




}
