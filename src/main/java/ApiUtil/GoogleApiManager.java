/* Controls all Google API requests */
package ApiUtil;


import org.json.JSONObject;
import ryanParsing.Node;
import ryanParsing.Way;

import java.io.*;
import java.util.ArrayList;

public class GoogleApiManager extends ApiManager {

    private final static String apiKeyFile = "res/APIKey.secret";
    private static String apiKey = null;

    /**
     * Test function to determine connectivity with API
     * @param args
     */
    public static void main(String[] args) {
        GoogleDistanceRequest req1 = new GoogleDistanceRequest( 41.43206, -81.38992, -33.86748, 151.20699, getApiKey());
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(new Node(43.08817, -77.66745, 1));
        nodes.add(new Node(43.08809, -77.66782, 2));
        GoogleRoadRequest req2 = new GoogleRoadRequest( new Way(nodes,nodes), getApiKey());


        System.out.println( req1.toString() );
        try {
            //System.out.println( makeRequest(req1) );
            System.out.println( makeRequest(req2) );
        } catch ( Exception e ) {

        }
    }

    /**
     * Retrieve distance given two sets of coordinates
     * @param originX
     * @param originY
     * @param destX
     * @param destY
     * @return JSONObject containing distance data
     */
    public static JSONObject generateAndMakeDistanceRequest( double originX, double originY, double destX, double destY ) {
        GoogleDistanceRequest req = new GoogleDistanceRequest( originX, originY, destX, destY, getApiKey());
        JSONObject json = null;
        try {
          json = makeRequest( req );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * Sends request, receives response as buffer
     * @param req
     * @return JSONObject containing data
     * @throws IOException
     */
    public static JSONObject makeRequest( Request req ) throws IOException {

        BufferedReader in = null;
        try {
            in = new BufferedReader(
                    new InputStreamReader(getInputStream(req)));
        } catch ( Exception e ) {
            System.err.println(e);
            return null;
        }

        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        return new JSONObject(response.toString());
    }

    public static String getApiKey() {
        // Don't want to have to reread a file
        if( apiKey != null )
            return apiKey;

        try {
            BufferedReader br = new BufferedReader(new FileReader( new File(apiKeyFile) ));
            apiKey = br.readLine().trim();
        } catch( Exception e ) {
            System.err.println( e.toString() );
        }

        return apiKey;
    }
}
