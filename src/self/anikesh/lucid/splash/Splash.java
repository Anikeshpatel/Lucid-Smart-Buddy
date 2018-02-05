package self.anikesh.lucid.splash;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Splash implements Initializable {

    @FXML
    private Label userName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userName.setText(System.getProperty("user.name"));
    }
}
