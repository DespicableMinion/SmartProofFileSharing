package Connection.Request;

import Connection.Request.Request;
import Utils.UrlHelper;

public class RequestHandler {

    synchronized public static Request getGenerateNodeRequest() {
        return new Request("GET", UrlHelper.generateNodeUrl, null, null);
    }

    synchronized public static Request getSaveRequest(String fileContent) {
        return null;//new Request("");//TODO
    }

    synchronized public static Request getShareRequest(String recipients) {
        return null;//new Request();//TODO
    }

    synchronized public static Request getModifyRequest(String fileContent) {
        return null;//new Request();//TODO
    }

    synchronized public static Request getHashRequest() {
        return null;//new Request();//TODO
    }
}
