package UserInterface;

import static spark.Spark.*;

import com.google.gson.Gson;


import spark.TemplateEngine;

public class WebServer {
//    public static final Logger LOG = Logger.getLogger(WebServer.class.getName());

    public static final String HOME_URL = "/";

    private final TemplateEngine templateEngine;
    private final Gson gson;

    public WebServer(final TemplateEngine templateEngine, final Gson gson){
//        Objects.ensureNotNull(templateEngine);
//        Objects.ensureNotNull(gson);

        this.templateEngine = templateEngine;
        this.gson = gson;
    }

    public void initialize(){
        staticFileLocation("/public");

        get(HOME_URL, new GetHomeRoute(templateEngine));
    }

}
