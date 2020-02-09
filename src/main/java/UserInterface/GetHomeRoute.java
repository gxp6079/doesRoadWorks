package UserInterface;

import com.google.gson.Gson;
import ryanParsing.MainParser;
import ryanParsing.Way;
import spark.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;


public class GetHomeRoute implements Route {

    public final TemplateEngine templateEngine;
    private static ArrayList<Way> ways;


    public GetHomeRoute(final TemplateEngine templateEngine, ArrayList<Way> ways){
        this.templateEngine = templateEngine;
        this.ways = ways;
    }

    public Object handle(Request request, Response response) throws Exception {
        HashMap<String, Object> vm = new HashMap<String, Object>();
        vm.put("title", "Home");
        vm.put("API_KEY", WebServer.API_KEY);

        if(request.session().attribute("north") != null) {
            Double north = Double.valueOf((String) request.session().attribute("north"));
            Double south = Double.valueOf((String) request.session().attribute("south"));
            Double west = Double.valueOf((String) request.session().attribute("west"));
            Double east = Double.valueOf((String) request.session().attribute("east"));

            vm.put("north", north);
            vm.put("south", south);
            vm.put("west", west);
            vm.put("east", east);
        }
        else {
            vm.put("north", 0);
            vm.put("south", 0);
            vm.put("west", 0);
            vm.put("east", 0);
        }

        Way[] wayArr = new Way[ways.size()];
        wayArr = ways.toArray(wayArr);

        System.out.println("Way length: " + ways.size());
        vm.put("wayList", new Gson().toJson(wayArr));
        return templateEngine.render(new ModelAndView(vm, "home.ftl"));
    }



    public static void updateWays(ArrayList<Way> ways) {
        GetHomeRoute.ways = ways;
    }

    public static void updateWays(InputStream osm) throws Exception{
        GetHomeRoute.ways = MainParser.parseInputStream(osm);
    }
}
