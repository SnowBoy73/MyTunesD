/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.controller;

import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;
import mytunes.be.Song;
import mytunes.bll.BllManager;

/**
 * FXML Controller class
 *
 * @author mega_
 */
public class NewSongController implements Initializable {

    @FXML
    private JFXTextField titleField;
    @FXML
    private JFXTextField artistField;
    @FXML
    private JFXTextField timeField;
    @FXML
    private JFXTextField fileField;
    @FXML
    private Spinner<String> categoryField;
    private ObservableList<Song>songTable;
    @FXML
    private Button choosePath;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SaveSong(ActionEvent event) {
        BllManager bll = new BllManager();
        
        //use this for save playlists.
        Song song = new Song(0, titleField.getText(), artistField.getText(), categoryField.getValue(), Integer.parseInt(timeField.getText()), fileField.getText());
         bll.saveSong(song);
         songTable.add(song);
         
         // Close savesong window after saved the song
         Stage stage = (Stage)((Node)((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
       
        
    }
    
     @FXML
    private void CancelSong(ActionEvent event) {
        
        Stage stage = (Stage)((Node)((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }
    
    void setSongNew(ObservableList<Song>songtable){
        this.songTable=songtable;
    }

    @FXML
    private void ChoosePathForSong(ActionEvent event) {
        

        
    }
}
