/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.controller;

import java.awt.event.KeyEvent;
import mytunes.be.Playlist;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.css.SimpleStyleableObjectProperty;
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
import mytunes.MyTunes;
import mytunes.be.Song;
import mytunes.bll.BllManager;
import mytunes.dal.MockSongDAO;
import mytunes.gui.model.playlistmodel;
/**
 * FXML Controller class
 *
 * @author mega_
 */
public class MyTunesController implements Initializable {

    @FXML
    private Label label;
    @FXML
    public ListView<Playlist> playlistsview;
    @FXML
    private Label playlistslabel;
    @FXML
    private ListView<Song> playlistSongsView;
    @FXML
    private Label Playlistsongslabel;
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
    private TableView<Song> songTable;
    @FXML
    private TableColumn<Song, String> allSongsTitle;  
    @FXML
    private TableColumn<Song, String> allSongsArtist;
    @FXML
    private TableColumn<Song, String> allSongsCategory;
    @FXML
    private TableColumn<Song, Integer> allSongsTime;
    @FXML
    private Button Searchbutton;

   /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        playlistsview.getSelectionModel().selectedItemProperty().addListener((observable) -> {
            Playlist p = playlistsview.getSelectionModel().getSelectedItem();
            
           playlistSongsView.getItems().clear();
           playlistSongsView.getItems().addAll(p.getSonglist());
             
            
        });
        
        
        allSongsTitle.setCellValueFactory((param) -> {
                 
            return new SimpleStringProperty( param.getValue().getTitle()); 
//To change body of generated lambdas, choose Tools | Templates.
        });
        
         
        
//To change body of generated lambdas, choose Tools | Templates.
        
         allSongsCategory.setCellValueFactory((param) -> {
                 
            return new SimpleStringProperty( param.getValue().getCategory()); 
//To change body of generated lambdas, choose Tools | Templates.
        });
         
        BllManager bll = new BllManager();
               
       /* Song song = new Song(0, "JeppesSOng", "ChiliBAnd", "Rock", 0,"music/Belshazzar.mp3");
        Song song1 = new Song(0, "NadiasSong", "ChiliBAnds", "Pop", 0,"music/Belshazzar.mp3");
        List<Song> songs = new ArrayList();
        songs.add(song);
        songs.add(song1);*/
        
        songTable.getItems().clear();
        songTable.getItems().addAll(bll.getAllSongs());
         
         allSongsArtist.setCellValueFactory((param) -> {
                 
            return new SimpleStringProperty( param.getValue().getArtist()); 
//To change body of generated lambdas, choose Tools | Templates.
        });
         allSongsTime.setCellValueFactory((param) -> {
                 
            return new SimpleObjectProperty<>( param.getValue().getDuration()); 
//To change body of generated lambdas, choose Tools | Templates.
        });
        // TODO
       
        // ListView
        
       
            
        
             playlistmodel playlistmodel  = new playlistmodel();
             
             
             playlistsview.setItems(playlistmodel.getAllPlaylist());
        
       
    }    
    
   /*   SearchSong something method that needs to be done 
   @FXML 
   private void searchSongs(KeyEvent evt){
  
   }
*/
    
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
    
        FXMLLoader songLoader = new FXMLLoader(getClass().getResource("/mytunes/gui/view/NewSong.fxml"));
        Parent root = songLoader.load();
        NewSongController newsong = songLoader.getController();
        ObservableList<Song> songtable = songTable.getItems();
        newsong.setSongNew(songtable); //setsongtodeletefromplaylist
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
    
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mytunes/gui/view/AskDeletePlaylist.fxml"));
        Parent root = loader.load();
        AskDeletePlaylistController ctrl = loader.getController();
        ObservableList<Playlist> playlist = playlistsview.getItems();
        Playlist Playlist = playlistsview.getSelectionModel().getSelectedItem();
        playlistsview.getItems().remove(Playlist);
        ctrl.setPlaylistToDelete(playlist);
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    } 

@FXML
private void clickDeleteSong(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mytunes/gui/view/AskDeleteSong.fxml"));
        Parent root = loader.load();
        AskDeletePlaylistSongController ctrl = loader.getController();
        ObservableList<Song> playlist = playlistSongsView.getItems();
        Song song = playlistSongsView.getSelectionModel().getSelectedItem();
        ctrl.setSongAndPlaylistToDelete(playlist, song); //setsongtodeletefromplaylist
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        
    } @FXML
private void clickDeletePlaylistSong(ActionEvent event) throws IOException {
    
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/mytunes/gui/view/AskDeletePlaylistSong.fxml"));
        Parent root = loader.load();
        AskDeletePlaylistSongController ctrl = loader.getController();
        ObservableList<Song> playlist = playlistSongsView.getItems();
        Song song = playlistSongsView.getSelectionModel().getSelectedItem();
        ctrl.setSongAndPlaylistToDelete(playlist, song); //setsongtodeletefromplaylist
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
       
    
    }


}
