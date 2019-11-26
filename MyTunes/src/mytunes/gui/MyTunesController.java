/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author Louise Nadia
 */
public class MyTunesController implements Initializable {
    
    @FXML
    private Label label;
    private Label playlistslabel;
    private Label Playlistsongslabel;
    private Label Allsongslabel;
    private List  Playlistsview;
    private List Playlistsongsview;
    private TableView SongTable;
    private TextField Searchbar;
    private Button Searchbutton;
    private Button newplaylistbutton;
    private Button editplaylistbutton;
    private Button deleteplaylistbutton;       
    private Button upbutton;
    private Button downbutton;
    private Button deleteplaylistssongbutton;
    private Button newsongbutton;
    private Button editsongsbutton;
    private Button deletesongsbutton;
    private Button addsongsbutton;
    private Button backbutton;
    private Button playbutton;
    private Button nextbutton;
    private Slider voliumelider;
    private Label  songplayerlabel;
    
    @FXML
    
    /*
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
