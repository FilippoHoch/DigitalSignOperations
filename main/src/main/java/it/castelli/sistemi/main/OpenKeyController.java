package it.castelli.sistemi.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;

public class OpenKeyController implements Initializable {

    private boolean loadingProgressPublic;
    private boolean loadingProgressPrivate;


    private String publicKey;
    private String privateKey;

    @FXML
    private Button load;

    @FXML
    private TextField pairName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        if (pairName.getText().isBlank()){
            pairName.setText("Keys "+ MainController.getInstance().counterClass);
            MainController.getInstance().counterClass++;
        }
        MainController.getInstance().setSearch(true);
        MainController.getInstance().newKeys = new Keys(pairName.getText(), publicKey, privateKey);
        Stage stage = (Stage) load.getScene().getWindow();
        stage.close();
    }

    @FXML
    void loadPrivate(ActionEvent event) {

        loadingProgressPrivate = true;
        checkLoadingProgress();
    }

    @FXML
    void loadPublic(ActionEvent event) {
        loadingProgressPublic = true;
        checkLoadingProgress();
    }

    @FXML
    private void checkLoadingProgress(){
        if (loadingProgressPublic && loadingProgressPrivate && load.isDisable()){
            load.setDisable(false);
        }
    }


}
