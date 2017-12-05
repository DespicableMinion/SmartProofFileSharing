package App.GUI;

import App.Logic.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class AppWindow extends JFrame implements ActionListener {
    boolean online = false; // TODO:use
    private JTextField textField;
    private Client client;

    public AppWindow(Client client) {
        super();

        WindowAdapter windowAdapter = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        };

        this.client = client;

        addWindowListener(windowAdapter);

        JPanel panel = this.createMainPanel();
        add(panel);

        setSize(800, 600);
        setResizable(false);
        setVisible(true);
        System.out.println("AppWindow Created");
    }

    @Override
    public void actionPerformed(ActionEvent e) { }

    @Override
    public void setDefaultCloseOperation(int operation) { }

    public synchronized String getTextFieldContent() {
        return this.textField.getText();
    }

    public synchronized void updateTextFieldContent() {
        //TODOthis.textField.se
    }

    private JPanel createChoicePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel upPanelPart = new JPanel(new BorderLayout());
        JPanel downPanelPart = new JPanel(new BorderLayout());

        // SAVE btn
        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(actionEvent -> {
            //login.setEnabled(false);
            //TODO
            handleActionResult(client.notifyServerAboutSaveOperation(getTextFieldContent()), "save");
        });
        upPanelPart.add(saveBtn, BorderLayout.NORTH);

        // SHARE btn
        JButton shareBtn = new JButton("Share");
        shareBtn.addActionListener(actionEvent -> {
            //TODO
            handleActionResult(client.notifyServerAboutShareOperation(getTextFieldContent()), "share");
        });
        upPanelPart.add(shareBtn, BorderLayout.CENTER);

        // HASH btn
        JButton fileHashBtn = new JButton("Get File Hash");
        fileHashBtn.addActionListener(actionEvent -> {
            //TODO
            handleActionResult(client.notifyServerAboutGetHashOperation(), "share");
        });
        upPanelPart.add(fileHashBtn, BorderLayout.SOUTH);

        panel.add(upPanelPart, BorderLayout.NORTH);

        // Connection info text field
        JTextField connectionInfo = new JTextField("You are offline");
        connectionInfo.setEnabled(false);
        downPanelPart.add(connectionInfo);

        panel.add(downPanelPart, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createTextPanel() {
        JPanel textPanel = new JPanel(new BorderLayout());
        this.textField = new JTextField();

        textField.setPreferredSize(new Dimension(600, 600));
        textField.addActionListener(e -> {

        });

        textPanel.add(new JScrollPane(this.textField), BorderLayout.CENTER);

        return textPanel;
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel leftPanel = createTextPanel();
        JPanel right = createChoicePanel();

        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(right, BorderLayout.EAST);

        return mainPanel;
    }

    private void handleActionResult(Boolean result, String action) {
        System.out.println("Couldn't perform " + action + " operation.");
        //TODO:pop up a window with message
    }
}
