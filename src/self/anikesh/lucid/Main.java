package self.anikesh.lucid;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Lucid Smart Buddy");
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
