package Connection.Response;

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

    public HashMap<String, Object> getContent(KeysHolder keysHolder) {
        return this.parse(this.body, keysHolder.getKeysMap(), "");
    }

    private HashMap<String, Object> parse(JSONObject json, HashMap<String, KeysHolder> jKeys, String prefix) {
        HashMap<String, Object> result = new HashMap<>();

        for (Map.Entry<String, KeysHolder> k : jKeys.entrySet()) {
            String key = k.getKey();
            KeysHolder keysHolder = k.getValue();

            try {
                Object obj = json.get(key);

                if (keysHolder != null) {
                    HashMap<String, KeysHolder> recJKeys = keysHolder.getKeysMap();
                    HashMap<String, Object> partRes = this.parse((JSONObject) obj, recJKeys, key + "_");
                    for (Map.Entry<String, Object> r : partRes.entrySet()) {
                        result.put(r.getKey(), r.getValue());
                    }
                } else {
                    key = prefix + key;
                    result.put(key, obj);
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
