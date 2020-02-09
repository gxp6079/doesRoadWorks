/* A portion of a given way, identifiable as what portion of a given way falls inside a bounding box */
package ryanParsing;

import java.util.ArrayList;

public class SegmentData {
    ArrayList<String> destination_addresses;
    ArrayList<Elements> rows;
    ArrayList<String> origin_addresses;
    String statis;
}



//{"destination_addresses":["114 W 29th St, New York, NY 10001, USA"],
// "rows":[{"elements":[{"duration":{"text":"1 min","value":27},
// "distance":{"text":"230 ft","value":70},"status":"OK"}]}],
// "origin_addresses":["6 Avenue & 29 St, New York, NY 10001, USA"],
// "status":"OK"}