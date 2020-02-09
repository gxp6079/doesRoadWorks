import UserInterface.WebServer;
import com.google.gson.Gson;
import freemarker.template.Configuration;
import ryanParsing.MainParser;
import ryanParsing.Way;
import spark.TemplateEngine;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.ArrayList;


public final class Application {

    public static void main(String[] args) {

        final String API_KEY = args[0];
        String osmFile;

        try {
            osmFile = args[1];
        } catch (Exception e) {
            osmFile = null;
        }
        ArrayList<Way> ways;

        try {
            System.out.println("Parsing " + osmFile);
            ways = MainParser.parseFileName(osmFile);
            System.out.println("Success");
        } catch (Exception e) {
            System.out.println("Failed");
            ways = new ArrayList<Way>();
        }

        // The application uses FreeMarker templates to generate the HTML
        // responses sent back to the client. This will be the engine processing
        // the templates and associated data.
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(Application.class, "/spark/freemarker/");
        final TemplateEngine templateEngine = new FreeMarkerEngine(configuration);

        // The application uses Gson to generate JSON representations of Java objects.
        // This should be used by your Ajax Routes to generate JSON for the HTTP
        // response to Ajax requests.
        final Gson gson = new Gson();

        final WebServer webServer = new WebServer(templateEngine, gson, API_KEY, ways);

        // inject web server into application
        final Application app = new Application(webServer);

        // start the application up
        app.initialize();
    }

    //
    // Attributes
    //

    private final WebServer webServer;

    //
    // Constructor
    //

    private Application(final WebServer webServer) {
        this.webServer = webServer;
    }

    //
    // Private methods
    //

    private void initialize() {
        // configure Spark and startup the Jetty web server
        webServer.initialize();
    }

}
