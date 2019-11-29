/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mytunes.be.Song;

/**
 * FXML Controller class
 *
 * @author mega_
 */
public class MyTunesController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private ListView<?> playlistsview;
    @FXML
    private Label playlistslabel;
    @FXML
    private ListView<?> PlaylistsongsView;
    @FXML
    private Label Playlistsongslabel;
    @FXML
    private TableView<Song> SongTable;
    @FXML
    private Button playbutton;
    @FXML
    private Button backbutton;
    @FXML
    private Button nextbutton;
    @FXML
    private Button upbutton;
    @FXML
    private Button downbutton;
    @FXML
    private Button deletebutton;
    @FXML
    private Button newplaylistbutton;
    @FXML
    private Button editplaylistbutton;
    @FXML
    private Button deleteplaylistbutton;
    @FXML
    private Button newsongsbutton;
    @FXML
    private Button editsongsbutton;
    @FXML
    private Button deletesongsbutton;
    @FXML
    private Button addsongstoplaylistbutton;
    @FXML
    private Label AllSongslabel;
    @FXML
    private Label songplayerlabel;
    @FXML
    private TextField searchbar;
    @FXML
    private Slider voliumslider;
    @FXML
    private TableColumn<Song, String> allSongsTitle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        allSongsTitle.setCellValueFactory((param) -> {
                 
            return new SimpleStringProperty( param.getValue().getTitle()); 
//To change body of generated lambdas, choose Tools | Templates.
        });
        
        Song sg = new Song(0, "JeppesSOng", "ChiliBAnd", "Rock", 0);
        allSongsTitle.getTableView().getItems().add(sg);
        // TODO
    }    

    @FXML
    private void clickNewPlaylist(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/mytunes/gui/view/NewPlaylist.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        
    }
    
    @FXML
    private void clickEditPlaylist(ActionEvent event) throws IOException {
    
        Parent root = FXMLLoader.load(getClass().getResource("/mytunes/gui/view/EditPlaylist.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    
    }
    
    @FXML
    private void clickNewSong(ActionEvent event) throws IOException {
    
        Parent root = FXMLLoader.load(getClass().getResource("/mytunes/gui/view/NewSong.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    
}
    @FXML
private void clickEditSong(ActionEvent event) throws IOException {
    
        Parent root = FXMLLoader.load(getClass().getResource("/mytunes/gui/view/EditSong.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
 @FXML
private void clickDeletePlaylist(ActionEvent event) throws IOException {
    
        Parent root = FXMLLoader.load(getClass().getResource("/mytunes/gui/view/AskDeletePlaylist.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    } @FXML
private void clickDeleteSong(ActionEvent event) throws IOException {
    
        Parent root = FXMLLoader.load(getClass().getResource("/mytunes/gui/view/AskDeleteSong.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    } @FXML
private void clickDeletePlaylistSong(ActionEvent event) throws IOException {
    
        Parent root = FXMLLoader.load(getClass().getResource("/mytunes/gui/view/AskDeletePlaylistSong.fxml"));
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }


}
