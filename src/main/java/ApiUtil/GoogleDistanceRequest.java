package ApiUtil;

public class GoogleDistanceRequest extends Request {

    private final static String distanceAPIUrl = "https://maps.googleapis.com/maps/api/distancematrix/";
    private final static String JSON = "json";

    private StringBuilder parameters;

    public GoogleDistanceRequest() {
        super(distanceAPIUrl);
        addSection(JSON);
        parameters = new StringBuilder();
        addParam("mode=driving");
        addParam("units=imperial");
    }

    /**
     * Default option
     */
    public void addResultBestGuessParam() {
        addParam("traffic_model=best_guess");
    }

    public void addResultPessimisticParam() {
        addParam("traffic_model=pessimistic");
    }

    public void addResultOptimisticParam() {
        addParam("traffic_model=optimistic");
    }

    public void addOriginParam( double x, double y ) {
        addParam( "origins", x + "," + y );
    }

    public void addDestinationParam( double x, double y ) {
        addParam( "destinations", x + "," + y );
    }

    public void addKeyParam( String key ) {
        addParam("key", key);
    }

    public void addParam( String name, String val ) {
        addParam( new StringBuilder(name).append("=").append(val).toString() );
    }

    private void addParam( String param ) {
        if( parameters.length() == 0 )
            parameters.append(param);
        else
            parameters.append("&").append(param);
    }

    public void compileRequest() {
        addSection(parameters.toString());
        parameters = new StringBuilder();
    }

    public String toString() {
        return super.toString();
    }
}
