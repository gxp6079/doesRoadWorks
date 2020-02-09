package ApiUtil;

public class GoogleDistanceRequest extends Request {

    private final static String distanceAPIUrl = "https://maps.googleapis.com/maps/api/distancematrix/";
    private final static String JSON = "json";

    public GoogleDistanceRequest(double originX, double originY, double destX, double destY, String key ) {
        super(distanceAPIUrl);
        addSection(JSON);
        StringBuilder parameters = new StringBuilder();
        parameters.append("mode=driving")
                .append("&units=imperial")
                .append("&origins=").append(originX).append(',').append(originY)
                .append("&destinations=").append(destX).append(',').append(destY)
                .append("&key=").append(key);
        addSection(parameters.toString());
    }
}
