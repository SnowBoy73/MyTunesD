/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.controller;

import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import mytunes.be.Playlist;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import mytunes.MyTunes;
import mytunes.be.Song;
import mytunes.bll.BllManager;

import mytunes.dal.mock.MockPlaylist;

import mytunes.dal.mock.MockPlaylist;

import mytunes.gui.model.playlistmodel;
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
    private Button searchbutton;
    @FXML
    private Button newsongbutton;
    @FXML
    private Button pausebutton;
    @FXML
    private Button stopbutton;

    private MediaPlayer mp;
    private MediaPlayer mediaPlayer;
BllManager bll = new BllManager();
    @FXML
    private Button backbutton;
    @FXML
    private Button nextbutton;



    @Override
    public void initialize(URL url, ResourceBundle rb) {

        playlistsview.getSelectionModel().selectedItemProperty().addListener((observable) -> {
            Playlist p = playlistsview.getSelectionModel().getSelectedItem();

            playlistSongsView.getItems().clear();
            playlistSongsView.getItems().addAll(p.getSonglist());

        });

        allSongsTitle.setCellValueFactory((param) -> {

            return new SimpleStringProperty(param.getValue().getTitle());
//To change body of generated lambdas, choose Tools | Templates.
        });

//To change body of generated lambdas, choose Tools | Templates.
        allSongsCategory.setCellValueFactory((param) -> {

            return new SimpleStringProperty(param.getValue().getCategory());
//To change body of generated lambdas, choose Tools | Templates.
        });

        
        songTable.getItems().clear();
        songTable.getItems().addAll(bll.getAllSongs());

        allSongsArtist.setCellValueFactory((param) -> {

            return new SimpleStringProperty(param.getValue().getArtist());
//To change body of generated lambdas, choose Tools | Templates.
        });
        allSongsTime.setCellValueFactory((param) -> {

            return new SimpleObjectProperty<>(param.getValue().getDuration());
//To change body of generated lambdas, choose Tools | Templates.
        });
        // TODO
        playlistsview.getItems().clear();
        playlistsview.getItems().addAll(bll.getAllPlaylist());

    }

    @FXML
    private void clickNewPlaylist(ActionEvent event) throws IOException {

        FXMLLoader playlistLoader = new FXMLLoader(getClass().getResource("/mytunes/gui/view/NewPlaylist.fxml"));
        Parent root = playlistLoader.load();
        NewPlaylistController newplaylist = playlistLoader.getController();
        ObservableList<Playlist> playlist = playlistsview.getItems();
        newplaylist.setPlaylistNew(playlist);
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

        ctrl.setUpContr(this);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    void DeletePlaylistReal() {
        int i = playlistsview.getSelectionModel().getSelectedIndex();

        //playlistModel.deletePlaylist(Playlist);
        playlistsview.getItems().remove(i);

    }

    @FXML
    private void clickDeleteSong(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mytunes/gui/view/AskDeleteSong.fxml"));
        Parent root = loader.load();
        AskDeleteSongController ctrl = loader.getController();
        ObservableList<Song> songs = songTable.getItems();
        Song song = songTable.getSelectionModel().getSelectedItem();
        ctrl.setSongToDelete(songs, song); //setsongtodeletefromplaylist
        Scene scene = new Scene(root);
        songTable.selectionModelProperty().getValue().getSelectedItem();
        songTable.getItems().remove(song);
        bll.deleteSong(song);
       
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
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

    @FXML
    private void clickPause(ActionEvent event) {

        mp.pause();
    }

    @FXML
    private void clickStop(ActionEvent event) {

        mp.stop();
        mp.dispose();
    }

    @FXML
    private void playMyDud(ActionEvent event) {
    
        
        //chililove: if song is paused, play from where songs is paused.
        if (mp != null && mp.getStatus() == MediaPlayer.Status.PAUSED) {
            mp.play();

        } //chililove: f you click stop when the songs is playing it stops the song.
        else if (mp != null && mp.getStatus() == MediaPlayer.Status.PLAYING) {
            mp.pause();
        } else {
            //Chililove: trying to connect songs to list, to be able to change between songs.
            Song song = songTable.getSelectionModel().getSelectedItem();
            mp = new MediaPlayer(new Media(new File(song.getPath()).toURI().toString())); //This line is giving me problems xxx
      //Not working with Mac       
  //Charlies suggestion til filechange   
  
  //songPlay = songPlay;
  //String filePlay = new File(songPlay.getFilePath()).toURI().toString;
  //Media hit = new Media(filePlay);
  //mp = new MediaPlayer(hit);
  //mp.play();
            
            /* chililove: sry I changed the song, but you can easily change it back by outcommenting this 

            mp = new MediaPlayer(new Media(new File("src/Khul.mp3").toURI().toString()));*/

            {

                // mp.setAutoPlay(true);
                // mp.setVolume(1.0);
                mp.setStartTime(new Duration(0));
                mp.play();
            }

        }
    }
    
    @FXML
    private void clickBackbtn(ActionEvent event) {
        
        
        
        if(backbutton!=null){
           
           backbutton.getId();
           songTable.idProperty().isNotNull();
           mp.play();   
        } 
        
        
    }

    @FXML
    private void clickNextbtn(ActionEvent event) {
        
          // nextbutton =findViewById(clickNextbtn(event));
        }


    
    @FXML
    private void volSlider(MouseEvent event) {
        
        
        Slider vol = new Slider();
        voliumslider.valueProperty().addListener(new InvalidationListener() { 
        
         public void invalidated(Observable ov) 
                { 
                    if (vol.isPressed()) { 
                        mp.setVolume(vol.getValue() / 100);
                    } 
                } 
        });
     }
}

