package Data;

import Connection.ConnectionHandler;
import Connection.Request;
import Connection.Response;
import Utils.ExceptionHandler;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DataHandler {
    private String getFileHashUrl = "http://p2ptest1.smartproof.io:8082/sp/datahash/byte-array/hashonly";
    private String storeFileUrl = "http://p2ptest1.smartproof.io:8082/sp/POST/datahash/generate";

    public void getHash(File file) {
        try {
            //URL url = new URL(getFileHashUrl);
            Response response = ConnectionHandler.sendRequest(new Request("POST", getFileHashUrl, null, null));
            System.out.println("getHash response:");
            //System.out.println(response.getContent());
        } catch (Exception ex) {
            ExceptionHandler.handleException(ex);
        }
    }

    public void storeFile(File file) {
        try {
            //URL url = new URL(storeFileUrl);
            Response response = ConnectionHandler.sendRequest(new Request("POST", storeFileUrl, null, null));
            System.out.println("storeFile response:");
            //System.out.println(response.getContent());
        } catch (Exception ex) {
            ExceptionHandler.handleException(ex);
        }
    }
}
