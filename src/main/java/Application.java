package main.java;

import com.google.gson.Gson;
import freemarker.template.Configuration;
import main.java.UserInterface.WebServer;
import spark.TemplateEngine;
import spark.template.freemarker.FreeMarkerEngine;



public final class Application {
//    private static final Logger LOG = Logger.getLogger(Application.class.getName());

    //
    // Application Launch method
    //



    public static void main(String[] args) {

        final String API_KEY = args[0];

        // initialize Logging
        try {
//            ClassLoader classLoader = Application.class.getClassLoader();
//            final InputStream logConfig = classLoader.getResourceAsStream("log.properties");
//            LogManager.getLogManager().readConfiguration(logConfig);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Could not initialize log manager because: " + e.getMessage());
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

        // inject the game center and freemarker engine into web server
        final WebServer webServer = new WebServer(templateEngine, gson, API_KEY);

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
        // validation
//        Objects.ensureNotNull(webServer);
        //
        this.webServer = webServer;
    }

    //
    // Private methods
    //

    private void initialize() {
//        LOG.config("WebCheckers is initializing.");

        // configure Spark and startup the Jetty web server
        webServer.initialize();

        // other applications might have additional services to configure

    }

}
