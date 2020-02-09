package UserInterface;

import UserInterface.WebServer;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;


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
        response.redirect(WebServer.HOME_URL);
        return null;
    }
}
