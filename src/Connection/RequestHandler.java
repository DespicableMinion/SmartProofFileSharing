package Connection;

public class RequestHandler {

    synchronized public static Request getSaveRequest(String fileContent) {
        return new Request();//TODO
    }

    synchronized public static Request getShareRequest(String recipients) {
        return new Request();//TODO
    }

    synchronized public static Request getModifyRequest(String fileContent) {
        return new Request();//TODO
    }

    synchronized public static Request getHashRequest() {
        return new Request();//TODO
    }
}
