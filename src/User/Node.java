package User;

import Connection.ConnectionHandler;
import Connection.Response;
import Utils.ExceptionHandler;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class Node
{
    public void Create()
    {
        try
        {
            URL url = new URL("http://p2ptest1.smartproof.io:8082/sp/account/generate");
            Response response = ConnectionHandler.sendRequest(url, "GET");
            ArrayList<String> keys = new ArrayList<>(Arrays.asList("encodedPublicKey", "encodedPrivateKey", "encodedAddress", "keyPair", "account"));
            HashMap<String, Object> content = Response.parseContent(response.getContent(), keys);

            System.out.println("VALUES:");
            for (String key: keys)
            {
                System.out.println(content.get(key));
            }

        }
        catch (Exception ex)
        {
            ExceptionHandler.handleException(ex);
        }
    }
}
