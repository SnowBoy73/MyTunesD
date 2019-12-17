
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.bll;

import java.util.List;
import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.dal.mock.MockPlaylist;
import mytunes.dal.mock.MockSongDAO;
import mytunes.dal.db.SongDBDAO;
import mytunes.dal.db.PlaylistDBDAO;
import mytunes.dal.db.SongDBDAO;
import mytunes.dal.mock.*;

/**
 *
 * @author mega_
 */
public class BllManager {

    private SongDBDAO songDBDao = new SongDBDAO();

    public List<Song> getAllSongs() {

        return songDBDao.getAllSongs();


    }
    public List<Song> getAllSongsWithFilter(String filterText){
        return songDBDao.getAllSongsWithFilter(filterText);
    }

    public void saveSong(Song song) {
    
        songDBDao.addSong(song);


    }

    private PlaylistDBDAO playlistDAO = new PlaylistDBDAO();

    public List<Playlist> getAllPlaylist() {
        return playlistDAO.getAllPlaylists();

    }

    public void savePlaylist(Playlist playlist) {
        playlistDAO.addPlaylist(playlist);

    }

    public void deletePlaylist(Playlist playlist) {
        playlistDAO.deletePlaylist(playlist);

    }

    public void deleteSong(Song song) {

        songDBDao.deleteSongFromDB(song);

    }
    public void addSongToPlaylist(Playlist playlist, Song song) {
        playlistDAO.addSongToPlaylist(playlist, song);
    }

    public void editSong(Song song) {
        songDBDao.updateSongInDB(song);
    }
    public void editPlaylist(Playlist playlist){
    
        playlistDAO.updatePlaylistNameInDB(playlist);
        
    }
    
    

}
