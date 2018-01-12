package self.anikesh.lucid;

import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXSpinner;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
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
    private JFXProgressBar commonPro1;

    @FXML
    private JFXProgressBar commonPro2;

    @FXML
    private JFXSpinner dirPro;

    private File workingDir;
    private static String dir;
    private ArrayList<String> allFiles,allDir,images,videos,music,documents,compressed,jar,executables,other;
    private InitFiles initFiles;
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

        workingDir = new File(dir);
        System.out.println("Working Dir Is : "+workingDir.getAbsolutePath());

        initFiles = new InitFiles();
        initFiles.start();
        initFiles.setOnSucceeded(e->{
            comPro.setOpacity(0);
            dirPro.setOpacity(0);
            docPro.setOpacity(0);
            exePro.setOpacity(0);
            jarPro.setOpacity(0);
            musicPro.setOpacity(0);
            picPro.setOpacity(0);
            vidPro.setOpacity(0);
            comStatus.setText(compressed.size()+" Found");
            dirStatus.setText(allDir.size()+" Found");
            docStatus.setText(documents.size()+" Found");
            exeStatus.setText(executables.size()+" Found");
            jarStatus.setText(jar.size()+" Found");
            musicStatus.setText(music.size()+" Found");
            picStatus.setText(images.size()+" Found");
            vidStatus.setText(videos.size()+" Found");
            System.out.println("Success");
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
                            if (fileName.contains(".mp3") || fileName.contains(".wav") || fileName.contains(".ac3")){
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

    private void moveFile(String source,String dest){
        File destination = new File(new File(dest).getParent());
        if (!destination.exists()){
            destination.mkdir();
            System.out.println("Dir Not Exist");
        }
        try {
            Files.move(Paths.get(source),Paths.get(dest), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exit() {
        System.exit(0);
    }

    private void minimize() {
        Stage curStage = (Stage)root.getScene().getWindow();
        curStage.setIconified(true);
    }
}
