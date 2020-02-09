package ryanParsing;

public class Node {

    private double lat;
    private double lon;
    private long id;
    private int occurences = 0;

    public Node(double lat, double lon, long id) {
        this.lat = lat;
        this.lon = lon;
        this.id = id;
    }


    public void addOccurences() {
        this.occurences++;
    }

    public int getOccurences() {
        return this.occurences;
    }
}
