package ApiUtil;

import org.eclipse.jetty.util.IO;

import java.io.IOException;
import java.io.InputStream;

public class OsmApiManager extends ApiManager {

    public static void main( String[] args ) {

    }

    public InputStream makeRequest(OSMRequest r) throws IOException {
        return getInputStream(r);
    }
}
