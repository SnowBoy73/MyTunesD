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
import javafx.stage.Stage;
import mytunes.be.Song;
import mytunes.bll.BllManager;

/**
 * FXML Controller class
 *
 * @author mega_
 */
public class AskDeletePlaylistSongController implements Initializable {
    private ObservableList<Song>playlist;
       private Song song;
       BllManager bll=new BllManager();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void KeepPlaylistSong(ActionEvent event) {
        
        
        Stage stage = (Stage)((Node)((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void DeletePlaylistSong(ActionEvent event) {
        
        this.playlist.remove(song);
        Stage stage = (Stage)((Node)((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
        //bll.deleteSongFromPlaylist(song);
    }
     public void setSongAndPlaylistToDelete(ObservableList<Song> playlist, Song song){
        this.playlist = playlist;
        this.song = song;
     }
    
}
