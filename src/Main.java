import Account.Node;
import App.Logic.Server;
import Data.DataHandler;
import Data.File;
import Utils.ExceptionHandler;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class Main
{

    public static void main(String[] args)
    {
        Node node = new Node();

        DataHandler dataHandler = new DataHandler();
        //dataHandler.getHash(new File());
        System.out.println("Main: before server creation");
        try {
            Server server = new Server(InetAddress.getLocalHost(), 12345, "Minion");
            System.out.println("Main: server created");
            server.start();
            System.out.println("Main: server started");
        } catch (UnknownHostException ex) {
            ExceptionHandler.handleException(ex);
        }
    }
}
