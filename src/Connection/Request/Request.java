package Connection.Request;

import Utils.ExceptionHandler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Dictionary;

public class Request {
    private String method;
    private String url;
    private Dictionary<String, String> headers;
    private String body;

    public Request(String method, String url, Dictionary<String, String> headers, String body) {
        this.method = method;
        this.url = url;
        this.headers = headers;
        this.body = body;
    }

    public String getMethod() {
        return this.method;
    }

    public URL getUrl() {
        URL url = null;

        try {
            url = new URL(this.url);
        } catch (MalformedURLException ex) {
            ExceptionHandler.handleException(ex);
        }

        return url;
    }

    public Dictionary<String, String> getHeaders() {
        return this.headers;
    }

    public String getBody() {
        return this.body;
    }
}
