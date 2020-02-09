/* Represents a point in geographical space. Used by OSM as points along a Way */
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

    // An additional occurences field is used to determine if this node represents an intersection of ways
    // An intersection is a node shared between ways
    public Node(double lat, double lon, long id, int occurences) {
        this.lat = lat;
        this.lon = lon;
        this.id = id;
        this.occurences = occurences;
    }

    public void addOccurences() {
        this.occurences++;
    }

    public int getOccurences() {
        return this.occurences;
    }

    public double getLatitude() {
        return lat;
    }

    public double getLongitude() {
        return lon;
    }

    @Override
    public String toString() {
        return "ID: " + this.id + "Occ: " + occurences;
    }
}
