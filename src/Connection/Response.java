package Connection;

import App.Logic.Action;
import Utils.ExceptionHandler;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Response {
    private int code;
    private JSONObject body;

    public Response(int code, String body) {
        this.code = code;

        try {
            this.body = new JSONObject(body);
        } catch (Exception ex) {
            ExceptionHandler.handleException(ex);
        }
    }

    public HashMap<String, Object> getContent(HashMap<String, Object> keys) {
        return Response.parse(this.body, keys, null);
    }

    synchronized public static HashMap<String, Object> parse(JSONObject json, HashMap<String, Object> jKeys, String prefix) {
        HashMap<String, Object> result = new HashMap<>();

        for (Map.Entry<String, Object> k : jKeys.entrySet()) {
            String key = k.getKey();
            Object obj = k.getValue();

            try {
                Object o = json.get(key);
                if (prefix != null) key = prefix + key;
                result.put(key, o);

                if (obj != null) {
                    HashMap<String, Object> recJKeys = (HashMap) obj;
                    HashMap<String, Object> partRes = Response.parse((JSONObject) o, recJKeys, key);
                    for (Map.Entry<String, Object> r : partRes.entrySet()) {
                        result.put(r.getKey(), r.getValue());
                    }
                }
            }
            catch (Exception ex) {
                ExceptionHandler.handleException(ex);
            }
        }

        return result;
    }

    public int getCode() {
        return this.code;
    }

    public Action getAction() {
        //TODO
        return null;
    }
}
