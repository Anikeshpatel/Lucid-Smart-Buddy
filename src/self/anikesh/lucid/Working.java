package self.anikesh.lucid;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXSpinner;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Working implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane toolbar;

    @FXML
    private Label exit;

    @FXML
    private Label min;

    @FXML
    private Label picStatus;

    @FXML
    private JFXSpinner picPro;

    @FXML
    private Label comStatus;

    @FXML
    private JFXSpinner comPro;

    @FXML
    private Label vidStatus;

    @FXML
    private JFXSpinner vidPro;

    @FXML
    private Label exeStatus;

    @FXML
    private JFXSpinner exePro;

    @FXML
    private Label musicStatus;

    @FXML
    private JFXSpinner musicPro;

    @FXML
    private Label jarStatus;

    @FXML
    private JFXSpinner jarPro;

    @FXML
    private Label docStatus;

    @FXML
    private JFXSpinner docPro;

    @FXML
    private Label dirStatus;

    @FXML
    private Pane backBtn;

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

    @FXML
    private JFXProgressBar commonPro1;

    @FXML
    private JFXProgressBar commonPro2;

    @FXML
    private JFXSpinner dirPro;

    private File workingDir;
    private static String dir;
    private ArrayList<String> allFiles,allDir,images,videos,music,documents,compressed,jar,executables,other;
    private InitFiles initFiles;
    private TranslateTransition slideTransition;
    private Integer toggle = 1;
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

        slideTransition = new TranslateTransition(Duration.seconds(1),root);
        slideTransition.setFromX(0);
        slideTransition.setToX(-750);

        allFiles = new ArrayList<String>();
        allDir = new ArrayList<String>();
        images = new ArrayList<String>();
        videos = new ArrayList<String>();
        music = new ArrayList<String>();
        documents = new ArrayList<String>();
        compressed = new ArrayList<String>();
        jar = new ArrayList<String>();
        executables = new ArrayList<String>();
        other = new ArrayList<String>();

        comStatus.setVisible(false);
        vidStatus.setVisible(false);
        picStatus.setVisible(false);
        musicStatus.setVisible(false);
        jarStatus.setVisible(false);
        exeStatus.setVisible(false);
        docStatus.setVisible(false);
        dirStatus.setVisible(false);

        workingDir = new File(dir);

        initFiles = new InitFiles();
        initFiles.start();
        initFiles.setOnSucceeded(e->{
            comPro.setVisible(false);
            dirPro.setVisible(false);
            docPro.setVisible(false);
            exePro.setVisible(false);
            jarPro.setVisible(false);
            musicPro.setVisible(false);
            picPro.setVisible(false);
            vidPro.setVisible(false);

            comStatus.setVisible(true);
            vidStatus.setVisible(true);
            picStatus.setVisible(true);
            musicStatus.setVisible(true);
            jarStatus.setVisible(true);
            exeStatus.setVisible(true);
            docStatus.setVisible(true);
            dirStatus.setVisible(true);

            comStatus.setText(compressed.size()+" Found");
            dirStatus.setText(allDir.size()+" Found");
            docStatus.setText(documents.size()+" Found");
            exeStatus.setText(executables.size()+" Found");
            jarStatus.setText(jar.size()+" Found");
            musicStatus.setText(music.size()+" Found");
            picStatus.setText(images.size()+" Found");
            vidStatus.setText(videos.size()+" Found");

            commonPro1.setVisible(false);
            commonPro2.setVisible(false);
        });

        backBtn.setOnMouseClicked(e->{
            try {
                goToHome();
            } catch (IOException e1) {
                e1.printStackTrace();
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
        menu_home.setOnAction(e->{
            try {
                goToHome();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }


    public static void setWorkingDir(String dir){
        Working.dir = dir;
    }

    class InitFiles extends Service{
        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    File[] files = workingDir.listFiles();
                    for (int i=0;i<files.length;i++){
                        if (files[i].isFile()){
                            allFiles.add(files[i].getName());
                            String fileName = files[i].getName().toLowerCase();
                            if (fileName.contains(".png") || fileName.contains(".jpg") || fileName.contains(".gif") || fileName.contains(".jpeg")){
                                images.add(files[i].getName());
                                moveFile(files[i].getAbsolutePath(),workingDir.getAbsolutePath()+"\\Lucid_Images\\"+fileName);
                            }
                            if (fileName.contains(".mp4") || fileName.contains(".3gp") || fileName.contains(".mkv") || fileName.contains(".flv")){
                                videos.add(files[i].getName());
                                moveFile(files[i].getAbsolutePath(),workingDir.getAbsolutePath()+"\\Lucid_Videos\\"+fileName);
                            }
                            if (fileName.contains(".mp3") || fileName.contains(".wav") || fileName.contains(".ac3") || fileName.contains(".aac") || fileName.contains(".m4a") || fileName.contains(".ogg")){
                                music.add(files[i].getName());
                                moveFile(files[i].getAbsolutePath(),workingDir.getAbsolutePath()+"\\Lucid_Sounds\\"+fileName);
                            }
                            if (fileName.contains(".doc") || fileName.contains(".pdf") || fileName.contains(".epub")){
                                documents.add(files[i].getName());
                                moveFile(files[i].getAbsolutePath(),workingDir.getAbsolutePath()+"\\Lucid_Documents\\"+fileName);
                            }
                            if (fileName.contains(".zip") || fileName.contains(".rar") || fileName.contains(".tar")){
                                compressed.add(files[i].getName());
                                moveFile(files[i].getAbsolutePath(),workingDir.getAbsolutePath()+"\\Lucid_Compressed\\"+fileName);
                            }
                            if (fileName.contains(".jar") || fileName.contains(".java") || fileName.contains(".class")){
                                jar.add(files[i].getName());
                                moveFile(files[i].getAbsolutePath(),workingDir.getAbsolutePath()+"\\Lucid_Java\\"+fileName);
                            }
                            if (fileName.contains(".exe") || fileName.contains(".deb") || fileName.contains(".msi")){
                                jar.add(files[i].getName());
                                moveFile(files[i].getAbsolutePath(),workingDir.getAbsolutePath()+"\\Lucid_Softwares\\"+fileName);
                            }
                            /*if (fileName.contains(".")){
                                other.add(files[i].getName());
                                moveFile(files[i].getAbsolutePath(),workingDir.getAbsolutePath()+"\\Lucid_Other");
                            }*/
                        }
                        if (files[i].isDirectory()){
                            allDir.add(files[i].getName());
                        }
                    }
                    return null;
                }
            };
        }
    }

    private void goToHome() throws IOException {
        slideTransition.play();
        slideTransition.setOnFinished(e->{
            Stage curStage = (Stage) root.getScene().getWindow();
            try {
                curStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Home.fxml"))));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

    }

    private void moveFile(String source,String dest){
        File destination = new File(new File(dest).getParent());
        if (!destination.exists()){
            destination.mkdir();
        }
        try {
            Files.move(Paths.get(source),Paths.get(dest), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ignored){
        }
    }

    @FXML
    void doneBtnAction(ActionEvent event) throws IOException {
        goToHome();
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
