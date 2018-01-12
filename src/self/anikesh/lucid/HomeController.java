package self.anikesh.lucid;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private AnchorPane home_pane1;

    @FXML
    private AnchorPane home_pane2;

    @FXML
    private AnchorPane root;
    @FXML
    private Label exit;

    @FXML
    private Label min;

    private RotateTransition rotateTransition;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rotateTransition = new RotateTransition(Duration.seconds(0.2),home_pane1);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(360);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.setCycleCount(5);
        rotateTransition.setOnFinished(e->{
            rotateTransition = new RotateTransition(Duration.seconds(3),home_pane1);
            rotateTransition.setFromAngle(0);
            rotateTransition.setToAngle(90);
            rotateTransition.setInterpolator(Interpolator.LINEAR);
            rotateTransition.play();
            rotateTransition.setOnFinished(lamda->{
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                rotateTransition = new RotateTransition(Duration.seconds(3),home_pane1);
                rotateTransition.setFromAngle(90);
                rotateTransition.setToAngle(360);
                rotateTransition.setInterpolator(Interpolator.LINEAR);
                rotateTransition.play();
            });
        });
        home_pane2.setOnMouseEntered(e->{
            //rotateTransition.play();
        });
        home_pane1.setOnMouseExited(e->{
        });

        exit.setOnMouseClicked(e->{
            exit();
        });
        min.setOnMouseClicked(e->{
            minimize();
        });
    }


    private void exit() {
        System.exit(0);
    }

    private void minimize() {
        Stage curStage = (Stage)root.getScene().getWindow();
        curStage.setIconified(true);
    }
}


