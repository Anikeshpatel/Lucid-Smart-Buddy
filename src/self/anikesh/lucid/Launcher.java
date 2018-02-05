package self.anikesh.lucid;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class Launcher extends Application {
    public static Stage curStage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        curStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("splash/splash.fxml"));
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
                primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("home/Home.fxml"))));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        Font.loadFont(getClass().getResource("../../../Resources/fonts/DFPop91.ttf").toExternalForm(),5);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
