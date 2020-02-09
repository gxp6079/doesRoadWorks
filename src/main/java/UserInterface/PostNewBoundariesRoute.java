package UserInterface;

import ApiUtil.OsmApiManager;
import UserInterface.WebServer;
import com.google.gson.Gson;
import ryanParsing.MainParser;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.InputStream;
import java.util.ArrayList;


public class PostNewBoundariesRoute implements Route {
    private final Gson gson;

    public PostNewBoundariesRoute(Gson gson){
        this.gson = gson;
    }

    public Object handle(Request request, Response response) throws Exception {
        System.out.println(request.queryParams("north"));
        request.session().attribute("north", request.queryParams("north"));
        request.session().attribute("south", request.queryParams("south"));
        request.session().attribute("west", request.queryParams("west"));
        request.session().attribute("east", request.queryParams("east"));

        InputStream in = OsmApiManager.generateAndMakeRequest(Double.parseDouble(request.queryParams("west")),
                                     Double.parseDouble(request.queryParams("south")),
                                     Double.parseDouble(request.queryParams("east")),
                                     Double.parseDouble(request.queryParams("north")));
        if (in != null) {
            ArrayList<ryanParsing.Way> ways = ryanParsing.MainParser.parseInputStream(in);
        }

        response.redirect(WebServer.HOME_URL);
        return null;
    }
}
