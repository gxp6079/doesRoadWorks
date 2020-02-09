package main.java.UserInterface;

import static spark.Spark.*;

import com.google.gson.Gson;


import ryanParsing.Way;
import main.java.UserInterface.GetHomeRoute;
import spark.TemplateEngine;

import java.util.ArrayList;

public class WebServer {
//    public static final Logger LOG = Logger.getLogger(WebServer.class.getName());

    public static final String HOME_URL = "/";

    private final TemplateEngine templateEngine;
    private final Gson gson;
    public static String API_KEY;
    private ArrayList<Way> ways;

    public WebServer(final TemplateEngine templateEngine, final Gson gson, final String API_KEY, ArrayList<Way> ways){
//        Objects.ensureNotNull(templateEngine);
//        Objects.ensureNotNull(gson);

        this.templateEngine = templateEngine;
        this.gson = gson;
        this.API_KEY = API_KEY;
        this.ways = ways;
    }

    public void initialize(){
        staticFileLocation("/public");

        get(HOME_URL, new GetHomeRoute(templateEngine, this.ways));
    }

}
