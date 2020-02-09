package main.java.UserInterface;

import spark.Request;
import spark.Response;
import spark.Route;


public class PostNewBoundariesRoute implements Route {


    public Object handle(Request request, Response response) throws Exception {
        System.out.println(request.queryParams("north"));
        response.redirect(WebServer.HOME_URL);
        return null;
    }
}
