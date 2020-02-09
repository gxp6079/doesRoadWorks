package ApiUtil;


import java.io.*;

public class OsmApiManager extends ApiManager {

    public static void main( String[] args ){
        OsmApiManager testManager = new OsmApiManager();
        try {
            InputStream in = testManager.makeRequest(new OSMRequest(-73.1, 49.0, -73.0, 49.1));
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader buff = new BufferedReader(reader);
            String line;
            while ((line = buff.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    public static InputStream generateAndMakeRequest( double left, double bottom, double right, double top ) {
        OSMRequest req = new OSMRequest( left, bottom, right, top );
        InputStream inputStream = null;
        try {
            inputStream = makeRequest(req);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println( "[ERROR] Encountered and I/O problem while accessing OpenStreetMap API" );
        }
        return inputStream;
    }

    public static InputStream makeRequest(OSMRequest r) throws IOException {
        return getInputStream(r);
    }
}
