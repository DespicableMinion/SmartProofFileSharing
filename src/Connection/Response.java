package Connection;

import Utils.ExceptionHandler;
import Utils.Pair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;


public class Response
{

    //public enum JsonObjType { STRING, INT, LIST, DICT};

    private JSONObject content;

    private String readContent(HttpURLConnection con)
    {
        StringBuilder content = new StringBuilder();

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream())))
        {
            String line;
            content = new StringBuilder();
            while ((line = in.readLine()) != null)
            {
                content.append(line);
            }
        }
        catch (Exception ex)
        {
            ExceptionHandler.handleException(ex);
        }

        return content.toString();
    }

    public Response()
    {
        this.content = null;
    }

    public Response(HttpURLConnection conn)
    {
        try
        {
            this.content = new JSONObject(this.readContent(conn));
        }
        catch (Exception ex)
        {
            ExceptionHandler.handleException(ex);
        }
    }

    public JSONObject getContent() {
        return this.content;
    }

    public static HashMap<String, Object> parseContent(JSONObject json, List<String> keys) //TODO
    {
        HashMap<String, Object> result = new HashMap<>();
        System.out.println(json);
        System.out.println(json.length());
        for (String key: keys)
        {
            try
            {
                result.put(key, json.get(key));
            }
            catch (Exception ex)
            {
                ExceptionHandler.handleException(ex);
            }
        }
    /*    for (int i = 0; i < this.content.length(); ++i)
        {
            try
            {
                JSONObject obj = this.content.getJSONObject(i);
                System.out.println(obj.toString());
            }
            catch (Exception ex)
            {
                ExceptionHandler.handleException(ex);
            }
        }
        return null;

        for (Utils.Pair<String, JsonObjType> key: keys)
        {
            try
            {

                //result.put(key, this.content);

            }
            catch (Exception ex)
            {
                Utils.ExceptionHandler.handleException(ex);
            }
        }*/
        return result;
    }
}
