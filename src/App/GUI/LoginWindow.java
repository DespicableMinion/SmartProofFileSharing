package App.GUI;

import Utils.ExceptionHandler;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static java.lang.Thread.sleep;


public class LoginWindow extends Application {
    private Stage primaryStage = null;
    private GridPane grid = null;

    private void setupGrid() {
        this.grid.setAlignment(Pos.CENTER);
        this.grid.setHgap(10);
        this.grid.setVgap(10);
        this.grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("SmartProof File Sharing");
        scenetitle.setId("welcome-text");

        this.grid.add(scenetitle, 0, 0, 2, 1);

    }

    private void addLabels() {
        Label userName = new Label("User Name:");
        this.grid.add(userName, 0, 1);

        Label pw = new Label("Password:");
        this.grid.add(pw, 0, 2);

    }

    private void addInputFields() {
        TextField userTextField = new TextField();
        this.grid.add(userTextField, 1, 1);


        PasswordField pwBox = new PasswordField();
        this.grid.add(pwBox, 1, 2);
    }

    private void addButtons() {
        final Text actiontarget = new Text();
        actiontarget.setId("actiontarget");

        this.grid.add(actiontarget, 1, 5);

        // Sign in btn
        Button btnSingIn = new Button("Sign in");
        btnSingIn.setMinSize(150, 25);
        btnSingIn.setOnAction(e -> {
            actiontarget.setText("Sign in button pressed");

            EditorWindow editorWindow = new EditorWindow();

            try {
                editorWindow.start(this.primaryStage);
            } catch (Exception ex) {
                ExceptionHandler.handleException(ex);
            }
            //TODO add auth verification action
        });
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btnSingIn);
        this.grid.add(hbBtn, 1, 4);

        /*// Create account btn
        Button btn2 = new Button("Create NEM account");
        btn2.setMinSize(150, 25);
        btn2.setOnAction(e -> {
           //TODO
        });
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn2.getChildren().add(btn2);
        this.grid.add(hbBtn2, 1, 6);*/
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        this.grid = new GridPane();

        this.setupGrid();
        this.addLabels();
        this.addInputFields();
        this.addButtons();

        Scene scene = new Scene(this.grid, 500, 275);

        this.primaryStage.setScene(scene);
        scene.getStylesheets().add(LoginWindow.class.getResource("CSSLoginWindow.css").toExternalForm());

        this.primaryStage.show();
    }

    public static void launchWindow(String[] args) {
        launch(args);
    }
}
