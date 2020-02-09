package main.java.UserInterface;

import static spark.Spark.*;

import com.google.gson.Gson;


import spark.TemplateEngine;

public class WebServer {
//    public static final Logger LOG = Logger.getLogger(WebServer.class.getName());

    public static final String HOME_URL = "/";
    public static final String POST_BOUND = "/newBoundaries";

    private final TemplateEngine templateEngine;
    private final Gson gson;
    public static String API_KEY;

    public WebServer(final TemplateEngine templateEngine, final Gson gson, final String API_KEY){
//        Objects.ensureNotNull(templateEngine);
//        Objects.ensureNotNull(gson);

        this.templateEngine = templateEngine;
        this.gson = gson;
        this.API_KEY = API_KEY;
    }

    public void initialize(){
        staticFileLocation("/public");
        get(HOME_URL, new GetHomeRoute(templateEngine));
        post(POST_BOUND, new PostNewBoundariesRoute());
    }

}
