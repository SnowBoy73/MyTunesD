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
import javafx.scene.control.Spinner;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import mytunes.be.Song;
import mytunes.bll.BllManager;

/**
 * FXML Controller class
 *
 * @author mega_
 */




public class AskDeleteSongController implements Initializable {
    
TableView<Song> table = new TableView<>();
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
    private void KeepDeleteSong(ActionEvent event) {
        
        Stage stage = (Stage)((Node)((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void DeleteDeleteSong(ActionEvent event) {
        
    table.selectionModelProperty().getValue().getSelectedItem();
    table.getItems().remove(song);

      //  this.song.(song);
        Stage stage = (Stage)((Node)((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
        //bll.deleteSongFromPlaylist(song);
        };
    public void setSongToDelete(ObservableList<Song> playlist, Song song){
        this.song= song;
        this.table = table;
    }}
    

