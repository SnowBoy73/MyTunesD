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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mytunes.be.Song;
import mytunes.bll.BllManager;

/**
 * FXML Controller class
 *
 * @author mega_
 */
public class EditSongController implements Initializable {

    private Song song;
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
    
    private BllManager bll = new BllManager();
    private TableView<Song> songTable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ChooseSong(ActionEvent event) {
        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(null);
    }

    @FXML
    private void CancelEditSong(ActionEvent event) {
        Stage stage = (Stage)((Node)((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void SaveEditSong(ActionEvent event) {
        song.setArtist(artistField.getText());
        song.setCatagory(categoryField.getValue());
        song.setDuration(Integer.parseInt(timeField.getText()));
        song.setTitle(titleField.getText());
        song.setPATH(fileField.getText());
        
        bll.editSong(song);
        
        songTable.refresh();
        
         Stage stage = (Stage)((Node)((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }

    public void setSongNew(Song selectedItem) {
        this.song = selectedItem;
        artistField.setText(song.getArtist());
        //categoryField.set(song.getCategory());
        timeField.setText(""+song.getDuration());
        titleField.setText(song.getTitle());
        fileField.setText(song.getPath());
    }

    public void setTable(TableView<Song> songTable) {
        this.songTable = songTable; 
    }
    
}
