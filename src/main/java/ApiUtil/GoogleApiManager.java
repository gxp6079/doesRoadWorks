package ApiUtil;


import org.json.JSONObject;

import java.io.*;

public class GoogleApiManager extends ApiManager {

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
