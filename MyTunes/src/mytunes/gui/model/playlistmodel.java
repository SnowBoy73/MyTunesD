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
        
        
        List<Playlist> allPlaylist = new ArrayList();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Playlist playlist = new Playlist(0, "Chilli");
        Song songToAdd1 = new Song(0, "NadiasSong", "ChiliBAnds", "Pop", 0);
        Song songToAdd2 = new Song(0, "AlansSong", "ChiliBand", "Country", 0);
        
        playlist.addSongToList(songToAdd1);
        playlist.addSongToList(songToAdd2);
        allPlaylist.add(playlist);
        
        
        
        ObservableList<Playlist> allPlaylistss = FXCollections.observableArrayList();
        allPlaylistss.addAll(allPlaylist);
         return allPlaylistss;
    }
}
