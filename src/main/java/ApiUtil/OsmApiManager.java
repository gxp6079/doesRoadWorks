package ApiUtil;

import jdk.internal.util.xml.impl.Input;
import org.eclipse.jetty.util.IO;

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

    public InputStream makeRequest(OSMRequest r) throws IOException {
        return getInputStream(r);
    }
}
