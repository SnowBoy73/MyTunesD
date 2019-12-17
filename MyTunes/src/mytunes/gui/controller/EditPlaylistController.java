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
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import mytunes.be.Playlist;
import mytunes.be.Song;

import mytunes.bll.BllManager;

/**
 * FXML Controller class
 *
 * @author mega_
 */
public class EditPlaylistController implements Initializable {

    @FXML
    private JFXTextField editplaylistbutton;
    private ListView<Playlist> playlistsview;
    private Playlist playlist;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void CancelEditPlaylist(ActionEvent event) {

        Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void SaveEditPlaylist(ActionEvent event) {
        BllManager bll = new BllManager();

        //use this for save playlists.
        playlist.setName(editplaylistbutton.getText());
        bll.editPlaylist(playlist);
        playlistsview.refresh();
        Stage stage = (Stage) ((Node) ((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }

    public void setList(ListView<Playlist> playListView) {
        this.playlistsview = playListView;
        playlist = playListView.getSelectionModel().getSelectedItem();
        editplaylistbutton.setText(playlist.getName());
    }
}
