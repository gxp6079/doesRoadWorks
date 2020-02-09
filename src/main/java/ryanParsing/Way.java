/* A way in OSM represents many things, for our purposes a road. */
package ryanParsing;

import java.util.ArrayList;

public class Way {
    private ArrayList<Node> nodes;
    private ArrayList<Node> intersections;
    private int speed;
    private int expectedTime;
    private double distance; // miles
    private int givenTime;
    private boolean isGood = false;



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

    public int getExpectedTime() {
        this.expectedTime = (int) (((distance/5280)/speed)*3600);
        return (int) (((distance/5280)/speed)*3600);
    }

    public int getGivenTime() {
        return givenTime;
    }

    public void setGivenTime(int givenTime) {
        this.givenTime = givenTime;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }


    public double isGood() {
        this.isGood = givenTime <= getExpectedTime();
        return givenTime - getExpectedTime();
    }

    public double isGood(double average) {
        double delta = givenTime - getExpectedTime();
        this.isGood = delta < average;
        return givenTime - average;
    }

}
