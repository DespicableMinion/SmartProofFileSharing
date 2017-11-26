package Connection.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class KeysHolder { //TODO: add T extends HashMap<String, Object>

    private HashMap<String, KeysHolder> keysMap = new HashMap<>();

    public KeysHolder() { }

    public HashMap<String, KeysHolder> getKeysMap() {
        return this.keysMap;
    }

    public void addKeyToKeysMap(String parentKey, String newKey) {
        if (parentKey == null) {
            this.keysMap.put(newKey, null);
            return;
        }

        if(!this.searchKeyAndAddObject(this.keysMap, parentKey, newKey)) {
            System.out.println("Key haven't found.");
        }
    }

    private boolean searchKeyAndAddObject(HashMap<String, KeysHolder> map, String parentKey, String newKey) {
        if (map == null) return false;

        if (map.containsKey(parentKey)) {

            KeysHolder keysHolderObj;
            if (map.get(parentKey) != null) {
                keysHolderObj = map.get(parentKey);
                map.remove(parentKey);
            } else {
                keysHolderObj = new KeysHolder();
            }

            keysHolderObj.addKeyToKeysMap(null, newKey);
            map.put(parentKey, keysHolderObj);

            return true;
        }

        for (Map.Entry<String, KeysHolder> e: map.entrySet()) {
            KeysHolder keysHolder = e.getValue();

            if (keysHolder != null &&
                    keysHolder.searchKeyAndAddObject(keysHolder.getKeysMap(), parentKey, newKey)) {
                return true;
            }
        }

        return false;
    }

    public synchronized static KeysHolder prepareGenerateNodeKeysHolder() {
        KeysHolder keysHolder = new KeysHolder();

        keysHolder.addKeyToKeysMap(null, "encodedPublicKey");
        keysHolder.addKeyToKeysMap(null, "encodedPrivateKey");
        keysHolder.addKeyToKeysMap(null, "encodedAddress");
        keysHolder.addKeyToKeysMap(null, "keyPair");
        keysHolder.addKeyToKeysMap("keyPair", "privateKey");
        keysHolder.addKeyToKeysMap("keyPair", "publicKey");
        keysHolder.addKeyToKeysMap("privateKey", "value");
        keysHolder.addKeyToKeysMap("publicKey", "value");

        return keysHolder;
    }
}
