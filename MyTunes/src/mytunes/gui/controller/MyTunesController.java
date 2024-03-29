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
import javafx.beans.binding.Bindings;
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
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import mytunes.MyTunes;
import mytunes.be.Song;
import mytunes.bll.BllManager;

import mytunes.dal.mock.MockPlaylist;

import mytunes.dal.mock.MockPlaylist;

import mytunes.gui.model.playlistmodel;
import mytunes.be.Song;
import mytunes.dal.db.SongDBDAO;
import mytunes.gui.model.Songmodel;
import mytunes.obsolete.file.SongDAO;

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
    private Label AllSongslabel;
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
    private Button newsongbutton;
    @FXML
    private Button pausebutton;
    @FXML
    private Button stopbutton;

    private MediaPlayer mp;
    @FXML
    private Button backbutton;
    @FXML
    private Button nextbutton;
    BllManager bll = new BllManager();
    @FXML
    private Button addSongToPlaylist;
    @FXML
    private TextField searchbarField;
    @FXML
    private Label showsongplayed;

    private SelectionModel<Song> currentListSelection;
    @FXML
    private Slider playerslider;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        playlistsview.getSelectionModel().selectedItemProperty().addListener((observable) -> {
            Playlist p = playlistsview.getSelectionModel().getSelectedItem();

            playlistSongsView.getItems().clear();
            playlistSongsView.getItems().addAll(p.getSonglist());
        });

        playlistSongsView.getSelectionModel().selectedItemProperty().addListener((observable) -> {
            currentListSelection = playlistSongsView.getSelectionModel();
        });

        songTable.getSelectionModel().selectedItemProperty().addListener((observable) -> {
            currentListSelection = songTable.getSelectionModel();
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

        searchbarField.textProperty().addListener((observable, oldVal, newVal) -> {
            songTable.getItems().clear();
            songTable.getItems().addAll(bll.getAllSongsWithFilter(newVal));
        });

    }

    @FXML
    private void clickNewPlaylist(ActionEvent event) throws IOException { //This method creates the new playlist 

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

        FXMLLoader playlistLoader = new FXMLLoader(getClass().getResource("/mytunes/gui/view/EditPlaylist.fxml"));
        Parent root = playlistLoader.load();
        EditPlaylistController editplaylist = playlistLoader.getController();
        editplaylist.setList(playlistsview);

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
        FXMLLoader songLoader = new FXMLLoader(getClass().getResource("/mytunes/gui/view/EditSong.fxml"));
        Parent root = songLoader.load();
        EditSongController editsong = songLoader.getController();
        editsong.setSongNew(songTable.getSelectionModel().getSelectedItem());
        editsong.setTable(songTable);

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
        Playlist playlist = playlistsview.getSelectionModel().getSelectedItem();

        //playlistModel.deletePlaylist(Playlist);
        bll.deletePlaylist(playlist);
        playlistsview.getItems().remove(playlist);

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
    private void clickPause(ActionEvent event
    ) { //This method pauses the musicplayer 

        mp.pause();
    }

    @FXML
    private void clickStop(ActionEvent event
    ) { //This method stopps the musicplayer
        if (mp != null && mp.getStatus() != MediaPlayer.Status.STOPPED) {
            mp.stop();
            showsongplayed.setText("");
            mp.dispose();
        }
//if the musicplayer is equal to null and when we get the status it's on stopped then,the player stops playing music, shows the text ""? and gets disposed..
    }

    @FXML
    private void playMyDud(ActionEvent event
    ) {

        //chililove: if song is paused, play from where songs is paused.
        if (mp != null && mp.getStatus() == MediaPlayer.Status.PAUSED) {
            mp.play();

        } //chililove: f you click stop when the songs is playing it stops the song.
        else if (mp != null && mp.getStatus() == MediaPlayer.Status.PLAYING) {
            mp.pause();
        } else {
            //Chililove: trying to connect songs to list, to be able to change between songs.
            Song song = currentListSelection.getSelectedItem();
            mp = new MediaPlayer(new Media(new File(song.getPath()).toURI().toString())); //This line is giving me problems xxx

            mp.setStartTime(new Duration(0)); //This line of code needs to be written for the player to be functioning on MAC.
            mp.play();
            showsongplayed.setText(song.toString());
            mp.setVolume(voliumslider.getValue()); //Set up the volume, by getting the volumeslider's value. 
            mp.currentTimeProperty().addListener(new javafx.beans.value.ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                    if (newValue.toMillis() > mp.getMedia().getDuration().toMillis() - 100) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ignored) {
                        }

                        clickNextbtn(null);
                    }
                }
            });

        }
    }

    @FXML
    private void clickBackbtn(ActionEvent event
    ) {
        Song song = currentListSelection.getSelectedItem(); //Gets the song from the list and selects the previous song
        currentListSelection.selectPrevious();
        if (song == currentListSelection.getSelectedItem()) {
            currentListSelection.selectLast();
        }

        clickStop(event);   //What are those ? xD 
        playMyDud(event);

    }

    @FXML
    private void clickNextbtn(ActionEvent event) { //Method for the next button function
        Song song = currentListSelection.getSelectedItem();
        currentListSelection.selectNext();
        if (song == currentListSelection.getSelectedItem()) {
            currentListSelection.selectFirst();
        }

        clickStop(event);
        playMyDud(event);

    }

    @FXML
    private void volSlider(MouseEvent event
    ) {
        //VolumeSlider-> if the music player doesnt eqaul null aka playing the volumeslider gets the value and sets it so it multiplies and divides with 100.
        if (mp != null) {
            System.out.println(voliumslider.getValue());
            mp.setVolume(voliumslider.getValue() * 100);
            mp.setVolume(voliumslider.getValue() / 100);
        }
    }

    @FXML
    private void addsongstoplaylistbutton(ActionEvent event
    ) {

        Playlist playlist = playlistsview.getSelectionModel().getSelectedItem();
        Song song = songTable.getSelectionModel().getSelectedItem();
        bll.addSongToPlaylist(playlist, song);
        playlist.addSongToList(song);
        playlistSongsView.getItems().add(song);

    }

    @FXML
    private void searchbarfield(ActionEvent event
    ) {
    }

    @FXML
    private void showSongPlayed(MouseEvent event
    ) {

    }

    @FXML
    private void clickUp(ActionEvent event
    ) { //This method gets the button to move songs up in the songs of the Playlist.

        int index = playlistSongsView.getSelectionModel().getSelectedIndex();
        if (index != 0) {
            playlistSongsView.getItems().add(index - 1, playlistSongsView.getItems().remove(index));

            playlistSongsView.getSelectionModel().clearAndSelect(index - 1);
        }
        //Basically we need the index of the int in the playlistsongsView. index -1 makes the selected item move up.
    }

    @FXML
    private void clickDown(ActionEvent event
    ) { //This method gets the button to move songs up in the songs of the Playlist.
        int index = playlistSongsView.getSelectionModel().getSelectedIndex();
        if (index != 0) {
            playlistSongsView.getItems().add(index + 1, playlistSongsView.getItems().remove(index));
            playlistSongsView.getSelectionModel().clearAndSelect(index + 1);
        }
        //index +1 because it needs to move the song(item) down.
    }

    @FXML
    private void clickplayerSlider(MouseEvent event
    ) {

        // This Code can be used to make the progression musicplayer bar.
        Double time = mp.getTotalDuration().toSeconds(); //we get the total duration in seconds.

        mp.currentTimeProperty().addListener((ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) -> {
            playerslider.setValue(newValue.toSeconds());
        }); //Get the currentTime and add change listner, which will be notified whenever the value of the ObservableValue changes and that we get the new value and set it to the slider 
        playerslider.maxProperty().bind(Bindings.createDoubleBinding( //We need binding in order for the slider to be bind to the musicplayer(song).
                () -> mp.getTotalDuration().toSeconds(), //The media player won't know the total duration of the song before it has read enough info from the song that's why we use binidng.
                mp.totalDurationProperty()));

        playerslider.setOnMouseClicked((MouseEvent mouseEvent) -> { //This Method shows the progress of the progress bar
            mp.seek(Duration.seconds(playerslider.getValue())); //It seeks the duration in seconds ofc. 
        });

    }
}
