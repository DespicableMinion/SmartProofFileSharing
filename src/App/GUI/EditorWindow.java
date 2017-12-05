package App.GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class EditorWindow extends Application {
    private Stage primaryStage = null;
    private GridPane grid = null;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        this.grid = new GridPane();




        Scene scene = new Scene(this.grid, 1000, 1000);
        this.primaryStage.setScene(scene);
        scene.getStylesheets().add(LoginWindow.class.getResource("CSSLoginWindow.css").toExternalForm());

        this.primaryStage.show();
    }
}
