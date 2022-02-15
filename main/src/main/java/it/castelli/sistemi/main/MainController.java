package it.castelli.sistemi.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public int counterClass = 0;
    private boolean search = false;
    public Keys newKeys;

    public void setSearch(boolean search) {
        this.search = search;
    }

    @FXML
    private ListView<Keys> keyListView;

    @FXML
    private TextArea privateKey;

    @FXML
    private TextArea publicKey;

    @FXML
    private Button saveKey;

    @FXML
    private Button signButton;

    @FXML
    private Button verifyButton;

    Window owner = Stage.getWindows().stream().filter(Window::isShowing).findFirst().orElse(null);

    private static MainController instance;

    public static MainController getInstance() {
        return instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instance = this;
        saveKey.setDisable(true);
        signButton.setDisable(true);
        verifyButton.setDisable(true);

    }

    @FXML
    void generateKey(ActionEvent event) {

    }

    @FXML
    void help(ActionEvent event) {
        try {
            Desktop.getDesktop().browse(new URL("https://github.com/FilippoHoch/DigitalSign").toURI());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void loadKey() throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("openKey.fxml"));
        Stage stage = new Stage();
        Scene sceneSearch = new Scene(loader.load(), 238, 131);
        stage.setTitle("Load");
        stage.setScene(sceneSearch);
        Stage currentStage = (Stage) saveKey.getScene().getWindow();
        stage.initOwner(currentStage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.show();
        stage.setOnHiding(event -> {
            if (search) {
                loadNewKey();
                search = false;
            }
        });
    }

    @FXML
    void saveKey(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save public key");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Key", ".key"));
        fileChooser.showSaveDialog(owner);
        fileChooser.setTitle("Save private key");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Key", ".key"));
        fileChooser.showSaveDialog(owner);
        /*
        item.setOnAction(new EventHandler<ActionEvent>() {
         public void handle(ActionEvent event) {
            //Opening a dialog box
            fileChooser.showSaveDialog(stage);
         }
      });
         */
    }

    @FXML
    void selectKey(MouseEvent event) {
        if(keyListView.getSelectionModel().getSelectedIndex() != -1){
            saveKey.setDisable(false);
            signButton.setDisable(false);
            verifyButton.setDisable(false);
        }

    }
    @FXML
    private void loadNewKey(){
        keyListView.getItems().add(newKeys);
    }

    @FXML
    void signDocument(ActionEvent event) {

    }

    @FXML
    void verifyDocument(ActionEvent event) {

    }

}
