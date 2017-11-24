package Connection;

import Utils.ExceptionHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;


public class ConnectionHandler
{

    synchronized public static Response sendRequest(Request request) {
        Response res = null;

        try { // TODO: handle headers & body
            HttpURLConnection con = (HttpURLConnection) request.getUrl().openConnection();
            con.setRequestMethod(request.getMethod());
            StringBuilder content = new StringBuilder();

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {
                String line;
                content = new StringBuilder();
                while ((line = in.readLine()) != null) {
                    content.append(line);
                }
            }
            catch (Exception ex) {
                ExceptionHandler.handleException(ex);
            }

            res = new Response(con.getResponseCode(), content.toString());
        }
        catch (Exception ex) {
            ExceptionHandler.handleException(ex);
        }

        return res;
    }
}
