package Utils;

public class ExceptionHandler {

    public static void handleException(Exception ex)
    {
        System.out.println("Exception occurred:");
        System.out.println(ex.getMessage());
        ex.printStackTrace();
        System.exit(1);
    }
}
