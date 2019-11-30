/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.model;

import java.util.ArrayList;
import java.util.List;
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Song playlist = new Song(0, "JeppesSOng", "ChiliBAnd", "Rock", 320);
        Song playlist1 = new Song(0, "NadiasSong", "ChiliBAnds", "Pop", 0);
        Song playlist2 = new Song(0, "AlansSong", "ChiliBand", "Country", 0);
        
        List<playlistmodel> songs = new ArrayList();
        //songs.add(playlist);
        //songs.add(playlist1);
        //songs.add(playlist2);
         return null;
    }
}
