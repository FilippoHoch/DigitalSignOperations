package it.castelli.sistemi.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OpenKeyController implements Initializable {

    private boolean loadingProgressPublic;
    private boolean loadingProgressPrivate;

    Window owner = Stage.getWindows().stream().filter(Window::isShowing).findFirst().orElse(null);


    private String publicKey;
    private String privateKey;

    FileChooser fileChooser = new FileChooser();
    File file;
    FileReader fileReader;
    BufferedReader bufferedReader;


    @FXML
    private Button load;

    @FXML
    private TextField pairName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Keys", "*.key"));
        loadingProgressPublic = false;
        loadingProgressPrivate = false;
        load.setDisable(true);
    }

    @FXML
    void cancelLoad(ActionEvent event) {
        Stage stage = (Stage) load.getScene().getWindow();
        stage.close();
    }

    @FXML
    void loadKeys(ActionEvent event) {
        if (pairName.getText().isBlank()) {
            pairName.setText("Keys " + MainController.getInstance().counterClass);
            MainController.getInstance().counterClass++;
        }
        MainController.getInstance().setLoadKey(true);
        MainController.getInstance().newKeys = new Keys(pairName.getText(), publicKey, privateKey);
        Stage stage = (Stage) load.getScene().getWindow();
        stage.close();
    }

    @FXML
    void loadPrivate(ActionEvent event) {
        // TODO: 15/02/2022 set priority of view on fileChooser
        fileChooser.setTitle("Open Private key");
        try {
            privateKey = openFile();
            loadingProgressPrivate = true;
            checkLoadingProgress();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void loadPublic(ActionEvent event) {
        // TODO: 15/02/2022 set priority of view on fileChooser
        fileChooser.setTitle("Open Public key");
        try {
            publicKey = openFile();
            loadingProgressPublic = true;
            checkLoadingProgress();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void checkLoadingProgress() {
        if (loadingProgressPublic && loadingProgressPrivate && load.isDisable()) {
            load.setDisable(false);
        }
    }

    private String openFile() throws IOException {
        file = fileChooser.showOpenDialog(owner);
        if (file.isFile() && file != null){
        fileReader = new FileReader(file);
        bufferedReader = new BufferedReader(fileReader);
        return bufferedReader.readLine();
        }
        return null;
    }


}
