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
        return templateEngine.render(new ModelAndView(vm, ""));
    }
}
