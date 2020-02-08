package UserInterface;

import static spark.Spark.*;

//import java.util.logging.Logger;

import com.google.gson.Gson;

//import com.webcheckers.appl.GameCenter;
//import com.webcheckers.appl.PlayerLobby;

import spark.TemplateEngine;

import javax.xml.bind.ValidationEvent;

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
