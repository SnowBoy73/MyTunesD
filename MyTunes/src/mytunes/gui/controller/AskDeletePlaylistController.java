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
import mytunes.gui.controller.AskDeletePlaylistController;
import mytunes.bll.BllManager;
import mytunes.dal.mock.MockPlaylist;
import mytunes.dal.db.PlaylistDBDAO;
import mytunes.dal.mock.MockPlaylist;
import mytunes.obsolete.file.PlaylistDAO;
import mytunes.be.Playlist;
import mytunes.be.Song;

/**
 * FXML Controller class
 *
 * @author mac
 */
public class AskDeletePlaylistController implements Initializable {
    private ObservableList<Playlist>playlist;
       private Playlist playlyist;
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
       
        //playlistview.getItems().remove(playlist);
        
        prim.DeletePlaylistReal();
        Stage stage = (Stage)((Node)((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }
private MyTunesController prim;

    void setUpContr(MyTunesController prim) {
    this.prim = prim;   }
    
}
