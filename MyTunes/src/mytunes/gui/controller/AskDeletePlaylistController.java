/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.controller;

import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.bll.BllManager;

/**
 * FXML Controller class
 *
 * @author mega_
 */
 


public class AskDeletePlaylistController implements Initializable {
     private ObservableList<Playlist>playlist;
       private Playlist Playlist;
       BllManager bll=new BllManager();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void KeepPlaylist(ActionEvent event) {
        
        
    
        Stage stage = (Stage)((Node)((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    
    }

    @FXML
    private void DeletePlaylist(ActionEvent event) {
       // this.playlist.remove(playlist);
        Stage stage = (Stage)((Node)((EventObject) event).getSource()).getScene().getWindow();
         stage.close();
    }

    void setPlaylistToDelete(ObservableList<Playlist> playlist) {
        this.playlist=playlist;
        this.playlist.remove(playlist);
       
    }
}
