package Connection;

import Utils.ExceptionHandler;

import java.net.HttpURLConnection;
import java.net.URL;


public class ConnectionHandler
{

    synchronized public static Response sendRequest(Request request) {
        return null;//TODO
    }

    synchronized public static Response sendRequest(URL url, String method)
    {
        Response res = new Response();

        try
        {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(method);
            res = new Response(con);
        }
        catch (Exception ex)
        {
            ExceptionHandler.handleException(ex);
        }

        return res;
    }
}
