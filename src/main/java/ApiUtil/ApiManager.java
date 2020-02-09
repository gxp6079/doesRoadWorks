/* Generic API manager extended by explicit-use child classes */
package ApiUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public abstract class ApiManager  {

    /**
     * Creates a generic stream for buffering incoming data
     * @param req
     * @return InputStream for buffering incoming data
     * @throws IOException
     */
    public static InputStream getInputStream( Request req ) throws IOException {

        URL url = new URL( req.toString() );
        URLConnection urlConnection = url.openConnection();

        return urlConnection.getInputStream();
    }
}
