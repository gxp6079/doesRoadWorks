package ApiUtil;

import ryanParsing.Node;
import ryanParsing.Way;

import java.util.ArrayList;

public class GoogleRoadRequest extends Request {

    private static final String API_CALL_TEMPLATE = "https://roads.googleapis.com/v1/speedLimits?";

    public GoogleRoadRequest( Way way, String key ) {
        super(API_CALL_TEMPLATE);
        StringBuilder parameters = new StringBuilder("paths=");
        ArrayList<Node> nodes = way.getNodes();
        for( int i = 0; i < nodes.size(); i++ ) {
            if( i > 0 )
                parameters.append('|');

            parameters.append( nodes.get(i).getLongitude() ).append(",").append( nodes.get(i).getLongitude() );
        }
        addSection(parameters.toString());
        addSection( "key=" + key );
    }
}
