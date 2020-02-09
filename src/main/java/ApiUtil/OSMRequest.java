package ApiUtil;

public class OSMRequest extends Request {

    private static final String OSM_URL_TEMPLATE = "/api/0.6/map?";

    public OSMRequest(double left, double bottom, double right, double top) {
        super(OSM_URL_TEMPLATE);

        requestString.append("bbox=")
                .append(left).append(",")
                .append(bottom).append(",")
                .append(right).append(",")
                .append(top);
    }
}
