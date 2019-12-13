/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.model;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Playlist;
import mytunes.be.Song;

/**
 *
 * @author mac
 */
public class playlistmodel {
     private ObservableList<Playlist> allPlaylist;

    public ObservableList<Playlist> getAllPlaylist() {
        allPlaylist = FXCollections.observableArrayList();
        allPlaylist.addAll(allPlaylist);
        return allPlaylist;
    }
}


