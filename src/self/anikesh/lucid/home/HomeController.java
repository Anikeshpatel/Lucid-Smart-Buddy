package self.anikesh.lucid;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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

    @FXML
    private Pane menu;

    @FXML
    private Pane menu_btn;

    @FXML
    private JFXButton menu_home;

    @FXML
    private JFXButton menu_status;

    @FXML
    private JFXButton menu_about;

    @FXML
    private JFXButton menu_exit;

    private RotateTransition rotateTransition;

    private Integer toggle = 1;

    private FadeTransition openTransition;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menu.setVisible(false);

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
            if(System.getProperty("os.name").toLowerCase().contains("win"))
                Working.setWorkingDir("C:\\Users\\"+System.getProperty("user.name")+"\\Downloads");
            else if(System.getProperty("os.name").toLowerCase().contains("linux"))
                Working.setWorkingDir("/home/"+System.getProperty("user.name")+"/Downloads");
            Stage curStage = (Stage) root.getScene().getWindow();
            try {
                curStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Working_window.fxml"))));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        selective_pane.setOnMouseClicked(e->{
            DirectoryChooser chooser = new DirectoryChooser();
            if(System.getProperty("os.name").toLowerCase().contains("win"))
                chooser.setInitialDirectory(new File("C:\\Users\\"+System.getProperty("user.name")+"\\Downloads"));
            else if(System.getProperty("os.name").toLowerCase().contains("linux"))
                chooser.setInitialDirectory(new File("/home/"+System.getProperty("user.name")+"/Downloads"));
            chooser.setTitle("Choose Any Crowd Folder");
            File workingDir = chooser.showDialog(root.getScene().getWindow());
            if (workingDir != null){
                Working.setWorkingDir(workingDir.getAbsolutePath());
                Stage curStage = (Stage) root.getScene().getWindow();
                try {
                    curStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Working_window.fxml"))));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        menu_btn.setOnMouseClicked(e->{
            if (toggle.equals(1)){
                menu.setVisible(true);
                toggle *= -1;
            }else {
                menu.setVisible(false);
                toggle *= -1;
            }
        });

        initTransition();
    }

    private void initTransition(){
        openTransition = new FadeTransition(Duration.seconds(1),root);
        openTransition.setFromValue(0);
        openTransition.setToValue(1);
        openTransition.play();
    }

    private void exit() {
        System.exit(0);
    }

    @FXML
    void quit(ActionEvent event) {
        System.exit(0);
    }

    private void minimize() {
        Stage curStage = (Stage)root.getScene().getWindow();
        curStage.setIconified(true);
    }

}


