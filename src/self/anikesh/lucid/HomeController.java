package self.anikesh.lucid;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane download_pane;

    @FXML
    private AnchorPane selective_pane;

    @FXML
    private AnchorPane toolbar;

    @FXML
    private Label exit;

    @FXML
    private Label min;

    private RotateTransition rotateTransition;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        final double[] xOffset = new double[1];
        final double[] yOffset = new double[1];

        exit.setOnMouseClicked(e->{
            exit();
        });
        min.setOnMouseClicked(e->{
            minimize();
        });

        toolbar.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset[0] = event.getSceneX();
                yOffset[0] = event.getSceneY();
            }
        });

        toolbar.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getScene().getWindow().setX(event.getScreenX() - xOffset[0]);
                root.getScene().getWindow().setY(event.getScreenY() - yOffset[0]);
            }
        });

        download_pane.setOnMouseClicked(e->{
            Working.setWorkingDir("C:\\Users\\"+System.getProperty("user.name")+"\\Downloads");
            Stage curStage = (Stage) root.getScene().getWindow();
            try {
                curStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Working_window.fxml"))));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        selective_pane.setOnMouseClicked(e->{
            DirectoryChooser chooser = new DirectoryChooser();
            chooser.setInitialDirectory(new File("C:\\Users\\"+System.getProperty("user.name")+"\\Downloads"));
            chooser.setTitle("Choose Any Crowd Folder");
            File workingDir = chooser.showDialog(root.getScene().getWindow());
            Working.setWorkingDir(workingDir.getAbsolutePath());
            Stage curStage = (Stage) root.getScene().getWindow();
            try {
                curStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Working_window.fxml"))));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
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


