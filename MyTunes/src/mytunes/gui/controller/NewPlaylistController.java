/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.controller;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.bll.BllManager;

/**
 * FXML Controller class
 *
 * @author mega_
 */
public class NewPlaylistController implements Initializable {

    @FXML
    private JFXTextField playlistTextField;
    @FXML
    private JFXTextField titleField;
    @FXML
    private JFXTextField playlistSongField;
    @FXML
    private JFXTextField fileField;
    @FXML
    private Button cancelPlaylist;
    @FXML
    private Button savePlaylist;
    @FXML
    //private ObservableList<Playlist>playlistview;
    private ObservableList<Playlist> playlistsview;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void CancelPlaylist(ActionEvent event) {
        //Closes the window if clicked cancel 
        Stage stage = (Stage)((Node)((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void SavePlaylist(ActionEvent event) { 
         BllManager bll = new BllManager();
        
        //use this for save playlists.
        
        Playlist playlist = new Playlist(0, playlistTextField.getText());
       // Playlist playlist = new Playlist(0, titleField.getText());//(fileField.getText());
         bll.savePlaylist(playlist);
         playlistsview.add(playlist);
       
      //  
        
       //  bll.savePlaylist(playlist);
       
       
       
       Stage stage = (Stage)((Node)((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }
    
    void setPlaylistNew(ObservableList<Playlist>playlistsview){
        this.playlistsview=playlistsview;
    }
  
    }
    

