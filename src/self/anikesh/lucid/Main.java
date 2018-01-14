package self.anikesh.lucid;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("splash.fxml"));
        FadeTransition transition = new FadeTransition(Duration.seconds(3),root);
        transition.setFromValue(1);
        transition.setToValue(0);
        primaryStage.setScene(new Scene(root,750,420));
        primaryStage.setTitle("Lucid Smart Buddy");
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
        transition.play();
        transition.setOnFinished(e->{
            try {
                primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Home.fxml"))));
                System.out.println("DOne");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}
