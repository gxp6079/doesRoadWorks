package ApiUtil;


import org.json.JSONObject;
import ryanParsing.Node;
import ryanParsing.Way;

import java.io.*;
import java.util.ArrayList;

public class GoogleApiManager extends ApiManager {

    private final static String apiKeyFile = "res/APIKey.secret";
    private static String apiKey = null;

    public static void main(String[] args) {
        GoogleDistanceRequest req1 = new GoogleDistanceRequest( 41.43206, -81.38992, -33.86748, 151.20699, getApiKey());

        System.out.println( req1.toString() );
        try {
            System.out.println( makeRequest(req1) );
        } catch ( Exception e ) {

        }
    }

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
