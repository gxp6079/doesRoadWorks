package ApiUtil;

public abstract class Request {

    protected StringBuilder requestString;

    public Request( String  httpAddress ) {
        requestString = new StringBuilder( httpAddress );
    }

    /**
     * Adds a section to the url, a section is defined as a piece of the url at the end,
     * where sections are separated by '?'
     * @param section
     */
    public void addSection( String section ) {
        if (requestString.length() == 0) {
            requestString.append( section );
            return;
        }

        char endChar = requestString.charAt( requestString.length() - 1 );
        if( endChar == '?' || endChar == '/' ) {
            requestString.append( section );
        } else {
            requestString.append('?').append( section );
        }
    }

    public String toString() {
        return requestString.toString();
    }
}
