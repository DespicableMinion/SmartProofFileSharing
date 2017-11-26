package App.Logic;

import Connection.ConnectionHandler;
import Connection.Request.RequestHandler;
import Connection.Response.Response;
import Utils.ExceptionHandler;

import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class Server extends Thread {

    private int portNr;
    private ServerSocket socket;
    private Client client;

    public Server(InetAddress inetAddress, int portNr, String username) {
        this.portNr = portNr;
        try {
            this.socket = new ServerSocket(portNr);
        } catch (IOException ex) {
            ExceptionHandler.handleException(ex);
        }
        System.out.println("Server: before client creation");
        this.client = new Client(inetAddress, portNr, username); // TODO: possibly could be more instances
        this.client.start();
        System.out.println("Server Created");
    }

    /**
     * S->C: Send a notification to client
     * */
    synchronized private Boolean executeClientNotification(Action action, Socket socket, ObjectOutputStream clientOutStream) {
        Boolean result = false;
        int tryCount = 5;

        while (!result && tryCount > 0) {

            try {
                clientOutStream.writeObject(action);
            } catch (IOException serverEx) {
                try {
                    if(!socket.getInetAddress().isReachable(socket.getPort())) {
                        --tryCount;
                        continue;
                    }
                } catch (IOException ex) {
                    ExceptionHandler.handleException(ex);
                }
            }
            result = true;
        }

        return result;
    }

    public void run() {
        try {
            while (true) {
                Socket connectionSocket = this.socket.accept(); //TODO:it should be here or in constructor?
                ObjectInputStream inFromClient = new ObjectInputStream(connectionSocket.getInputStream());
                ObjectOutputStream outToClient = new ObjectOutputStream(connectionSocket.getOutputStream());

                Action action = (Action) inFromClient.readObject();
                EventQueue.invokeLater(new FileActionExecutor(action, connectionSocket, outToClient));
            }

        } catch (Exception ex) {
            ExceptionHandler.handleException(ex);
        }
    }

    private class FileActionExecutor implements Runnable {
        private Action action;
        private Socket socket;
        private ObjectOutputStream clientOutStream;

        public FileActionExecutor(Action action, Socket socket, ObjectOutputStream clientOutStream) {
            this.action = action;
            this.socket = socket;
            this.clientOutStream = clientOutStream;
        }

        @Override
        public void run() {
            String message = this.action.getMessage();
            Response response = null;

            switch (this.action.getType()) {
                case SAVE:
                    response = ConnectionHandler.sendRequest(RequestHandler.getSaveRequest(message));
                    break;

                case SHARE:
                    response = ConnectionHandler.sendRequest(RequestHandler.getShareRequest(message));
                    break;

                case MODIFY:
                    response = ConnectionHandler.sendRequest(RequestHandler.getModifyRequest(message));
                    break;

                case HASH:
                    response = ConnectionHandler.sendRequest(RequestHandler.getHashRequest());
                    break;
            }

            Boolean execRes = executeClientNotification(response.getAction(), this.socket, this.clientOutStream);
            if (!execRes) { //TODO:handle it in a different way
                System.exit(1);
            }
        }
    }
}