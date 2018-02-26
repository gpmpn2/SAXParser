/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxparser;

import java.io.File;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Grant
 */
public class ParseXML {
    
    private Stage stage;
    private File xmlFile;
    
    public ParseXML(Stage stage) {
        this.stage = stage;
    }
    
    public void openFileToParse() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open XML File");
        File file = fileChooser.showOpenDialog(stage);
        
        if (file != null) {
            
        } else {
            showAlert("File could not be found or is not of type XML");
        }
    }
    
    public void showAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("File Issue");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
    
}
