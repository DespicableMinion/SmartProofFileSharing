package Account;

import Connection.ConnectionHandler;
import Connection.Request;
import Connection.RequestHandler;
import Connection.Response;
import Utils.ExceptionHandler;
import org.json.JSONObject;

import java.net.URL;
import java.util.*;

import static Utils.UrlHelper.generateNodeUrl;


public class Node {
    private String encodedPublicKey;
    private String encodedPrivateKey;
    private String encodedAddress;

    private List<Integer> publicKey;
    private String privateKey;

    public Node() {
        this.Create();
    }

    //TODO: ask user for typing his address
    public Node(String encodedNemAddress) {
        this.encodedAddress = encodedNemAddress;
    }

    private void Create() {
        try {
            Response response = ConnectionHandler.sendRequest(RequestHandler.getGenerateNodeRequest());

            HashMap<String, Object> keys = new HashMap<>();
            keys.put("encodedPublicKey", null);
            keys.put("encodedPrivateKey", null);
            keys.put("encodedAddress", null);
            //keys.put("account", null);

            HashMap<String, Object> privateAndPublicKeysKey = new HashMap<>();
            privateAndPublicKeysKey.put("value", null);

            HashMap<String, Object> keyPairKeys = new HashMap<>();
            keyPairKeys.put("privateKey", privateAndPublicKeysKey);
            keyPairKeys.put("publicKey", privateAndPublicKeysKey);

            keys.put("keyPair", keyPairKeys);

            HashMap<String, Object> content = response.getContent(keys);

            System.out.println("VALUES:");
            for (Map.Entry<String, Object> k : content.entrySet()) {
                String key = k.getKey();
                Object obj = k.getValue();
                System.out.println(key);
                System.out.println(obj.toString());
            }

            this.encodedPublicKey = (String)content.get("encodedPublicKey");
            this.encodedPrivateKey = (String)content.get("encodedPrivateKey");
            this.encodedAddress = (String)content.get("encodedAddress");

        } catch (Exception ex) {
            ExceptionHandler.handleException(ex);
        }
    }
}
