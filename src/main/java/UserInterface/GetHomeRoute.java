package UserInterface;

import spark.*;

import java.util.HashMap;

public class GetHomeRoute implements Route {

    public final TemplateEngine templateEngine;


    public GetHomeRoute(final TemplateEngine templateEngine){
        this.templateEngine = templateEngine;
    }

    public Object handle(Request request, Response response) throws Exception {
        HashMap<String, Object> vm = new HashMap<String, Object>();
        vm.put("title", "Home");
        vm.put("API_KEY", WebServer.API_KEY);
        return templateEngine.render(new ModelAndView(vm, "home.ftl"));
    }
}
