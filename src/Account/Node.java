package Account;

import Connection.ConnectionHandler;
import Connection.Request.RequestHandler;
import Connection.Response.KeysHolder;
import Connection.Response.Response;
import Utils.ExceptionHandler;
import org.json.JSONArray;

import java.util.*;


public class Node {
    private String encodedPublicKey;
    private String encodedPrivateKey;
    private String encodedAddress;

    private List<Integer> publicKey = new ArrayList<>();
    private String privateKey;

    public Node() {
        this.Create();
    }

    //TODO: ask user for typing his address
    public Node(String encodedPublicKey, String encodedPrivateKey,
                String encodedAddress, List<Integer> publicKey, String privateKey) {
        this.encodedPublicKey = encodedPublicKey;
        this.encodedPrivateKey = encodedPrivateKey;
        this.encodedAddress = encodedAddress;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    private void Create() {
        try {
            Response response = ConnectionHandler.sendRequest(RequestHandler.getGenerateNodeRequest());

            KeysHolder keysHolder = KeysHolder.prepareGenerateNodeKeysHolder();
            HashMap<String, Object> content = response.getContent(keysHolder);

            this.encodedPublicKey = (String)content.get("encodedPublicKey");
            this.encodedPrivateKey = (String)content.get("encodedPrivateKey");
            this.privateKey = (String)content.get("privateKey_value");
            JSONArray publicKeyJsonArray = (JSONArray) content.get("publicKey_value");

            for (int i = 0; i < publicKeyJsonArray.length(); ++i) {
                int nr = publicKeyJsonArray.getInt(i);
                this.publicKey.add(nr);
            }

        } catch (Exception ex) {
            ExceptionHandler.handleException(ex);
        }
    }

    public boolean verifyPrivileges(String fileHash) {
        return false; //TODO
    }
}
