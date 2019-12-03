/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.bll;

import java.util.List;
import mytunes.be.Playlist;
import mytunes.be.Song;
import static mytunes.dal.DalMethodTester.songDao;
import mytunes.dal.MockPlaylist;
import mytunes.dal.MockSongDAO;

/**
 *
 * @author mega_
 */
public class BllManager {

    private MockSongDAO songDAO = new MockSongDAO();

    public List<Song> getAllSongs() {

        return songDAO.getAllSongs();

    }

    public void saveSong(Song song) {
        
        songDAO.saveSong(song);

    }

    private MockPlaylist playlistDAO = new MockPlaylist();

    public List<Playlist> getAllPlaylist() {

        return playlistDAO.getAllPlaylists();

    }

    public void savePlaylist(Playlist playlist) {

        playlistDAO.savePlaylist(playlist);

    }

    public void deletePlaylist(Playlist playlist) {

    }

}
