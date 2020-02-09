package ApiUtil;


import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class GoogleApiManager {

    private final static String apiKeyFile = "res/APIKey.secret";
    private static String apiKey = null;

    public static void main(String[] args) {
        GoogleDistanceRequest req = new GoogleDistanceRequest();

        req.addOriginParam(41.43206,-81.38992);
        req.addDestinationParam(-33.86748,151.20699);
        req.addKeyParam( getApiKey() );
        req.compileRequest();

        System.out.println( req.toString() );
        try {
            System.out.println( makeRequest(req) );
        } catch ( Exception e ) {

        }
    }

    public static JSONObject makeRequest( GoogleDistanceRequest req ) throws IOException {

        URL url = new URL( req.toString() );
        URLConnection urlConnection = url.openConnection();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(urlConnection.getInputStream()));
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
