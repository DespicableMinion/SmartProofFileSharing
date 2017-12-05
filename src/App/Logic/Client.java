package App.Logic;

import App.GUI.AppWindow;
import Utils.ExceptionHandler;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import static App.Logic.Action.Type.*;


/**
 * Executes logic behind GUI operations
 * */
public class Client extends Thread {
    InetAddress serverIP;
    int serverPort;
    private Socket socket;
    public String username;

    private AppWindow appWindow;

    private ObjectOutputStream outToServer;
    private ObjectInputStream inFromServer;

    public Client(InetAddress serverIP, int serverPort, String username) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.username = username; //TODO:use it

        try {
            this.socket = new Socket(serverIP, serverPort);
            this.outToServer = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            ExceptionHandler.handleException(ex);
        }

        this.appWindow = new AppWindow(this);
    }

    /**
     * C->S: Send a request to server
     * */
    private Boolean executeServerNotification(Action action) {
        Boolean result = false;
        int tryCount = 5;

        while (!result && tryCount > 0) {

            try {
                this.outToServer.writeObject(action);
            } catch (IOException serverEx) {
                try {
                    if(!this.socket.getInetAddress().isReachable(this.socket.getPort())) {
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

    public Boolean notifyServerAboutSaveOperation(String fileContent) {
        return this.executeServerNotification(new Action(SAVE, fileContent));
    }

    public Boolean notifyServerAboutShareOperation(String recipientHashes) {
        return this.executeServerNotification(new Action(SHARE, recipientHashes));
    }

    public Boolean notifyServerAboutModifyOperation(String fileContent) {
        return this.executeServerNotification(new Action(MODIFY, fileContent));
    }

    public Boolean notifyServerAboutGetHashOperation() {
        return this.executeServerNotification(new Action(HASH, null));
    }

    /**
     * This function catches messages sent by Server to Client
     * */
    public void run() {
        while(true) {
            try {
                this.inFromServer = new ObjectInputStream(socket.getInputStream());
                Action action = (Action)inFromServer.readObject();

                switch (action.getType()) {
                    case MODIFY:
                        EventQueue.invokeLater(new FileContentUpdater(action.getMessage()));
                        break;

                    case HASH:
                        EventQueue.invokeLater(new FileHashUpdater(action.getMessage()));
                        break;
                }

            } catch (Exception ex) {
                ExceptionHandler.handleException(ex);
            }
        }
    }

    /**
     * S->C: GUI notifications
     * */
    private class FileContentUpdater implements Runnable {
        private String text;

        public FileContentUpdater(String text) {
            this.text = text;
        }

        @Override
        public void run() {
            //TODO appWindow.
        }
    }

    private class FileHashUpdater implements Runnable {
        private String text;

        public FileHashUpdater(String text) {
            this.text = text;
        }

        @Override
        public void run() {
            //TODO
        }
    }
}
