/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxparser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Grant
 */
public class ParseXML {
    
    private Stage stage;
    public static String returnString = "";
    
    public ParseXML(Stage stage) {
        this.stage = stage;
    }
    
    public String openFileToParse() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open XML File");
        File file = fileChooser.showOpenDialog(stage);
        
        if (file != null) {
            String split[] = file.getName().split("\\.");
            if(split[1].equalsIgnoreCase("xml")) {
                return parse(file);
            } else {
                showAlert("File is not of type XML");
            }
        } else {
            showAlert("File could not be opened");
        }
        
        return "";
    }
    
    public void showAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("File Issue");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
    
    public String parse(File file) {
                  
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParser = factory.newSAXParser();
            
            DefaultHandler handler = new DefaultHandler() {
                String temporaryElementName = null;
                String temporaryElementValue = null;
                boolean headTag = false;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    temporaryElementValue = "";
                    temporaryElementName = qName;

                    if(!headTag) {
                        headTag = true;
                        returnString = returnString.concat("XML Object: " + qName);
                    }
                    
                    for (int i=0; i<attributes.getLength(); i++) {
                        String aname = attributes.getLocalName(i);
                        String value = attributes.getValue(i);
                        returnString = returnString.concat("\n\t* has Object " + qName + " with an '" + aname + "' of value " + value);
                    }

                }

                @Override
                public void endElement(String uri, String localName, String qName) 
                                                            throws SAXException { 
                    if(temporaryElementName.equals(qName)) {
                        returnString = returnString.concat("\n\t\twith variable '" + qName + "' of value " + temporaryElementValue);
                    }
                }

                @Override
                public void characters(char ch[], int start, int length) throws SAXException {
                    temporaryElementValue = new String(ch, start, length) ;
                }
            };

            saxParser.parse(file.getAbsoluteFile(), handler);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(ParseXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnString; 
    }
}
