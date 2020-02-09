package ApiUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public abstract class ApiManager  {

    public static InputStream getInputStream( Request req ) throws IOException {

        URL url = new URL( req.toString() );
        URLConnection urlConnection = url.openConnection();

        return urlConnection.getInputStream();
    }
}
