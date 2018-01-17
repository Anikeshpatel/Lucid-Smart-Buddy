package self.anikesh.lucid;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PopUpController {

    @FXML
    private AnchorPane root;

    @FXML
    void gotoHome(ActionEvent event) {
        Stage curStage = (Stage) root.getScene().getWindow();
        try {
            curStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Home.fxml"))));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @FXML
    void hidePopup(ActionEvent event) {
        Stage curStage = (Stage) root.getScene().getWindow();
        curStage.hide();
    }

    @FXML
    void quit(ActionEvent event) {
        Main.curStage.close();
    }

    @FXML
    void showStatus(ActionEvent event) {

    }
}
