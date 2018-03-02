/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxparser;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 *
 * @author Grant
 */
public class SaxParserFXMLDocumentController implements Initializable {
    
    @FXML
    private Button openButton;
    
    @FXML
    private TextArea textArea;
    
    private ParseXML parser;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void handleOpenButton(){
        textArea.setText(parser.openFileToParse());
    }
    
    public void setParser(ParseXML parser) {
        this.parser = parser;
    }
}
