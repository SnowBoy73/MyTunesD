/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import mytunes.be.Playlist;
import mytunes.be.Song;
import mytunes.dal.SongDBDAO;

/**
 *
 * @author Louise, Nadia, Superior Martin and Alan
 */

public class PlaylistDBDAO {

    private static final String SONG_SOURCE = "data/song_list.txt";
    private static final String PLAYLIST_SOURCE = "data/playlists.txt";
    boolean isNewPlaylist = true;
    int oldPlaylistId = 20;
    private Connection con;
    private SongDBDAO songDBDao = new SongDBDAO(con);
    private Playlist playlist;
    private Song song;
    
    
    public PlaylistDBDAO() {
        DBConnectionProvider cp = new DBConnectionProvider();
        try {
            this.con = cp.getConnection();
        } catch (SQLServerException ex) {
            Logger.getLogger(SongDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updatePlaylistNameInDB(Playlist playlist){
    String stat = "UPDATE playlist/n" +
                  "SET name= '?' /n" +
                  "WHERE id=?";
    try (Connection xd = con){
    PreparedStatement stmt = con.prepareStatement(stat);
    stmt.setString(1,playlist.getName());
    stmt.setInt(2,playlist.getId());
    stmt.execute();
    }   catch (SQLException ex) {
            System.out.println("Exception " + ex);
        }
    }
    
    public void deletePlaylistFromDB(Playlist playlist){
    String stat = "DELETE FROM playlist WHERE id=?";
    try (Connection xd = con){
    PreparedStatement stmt = con.prepareStatement(stat);
    stmt.setInt(1, playlist.getId());
    stmt.execute();
    }   catch (SQLException ex) {
            System.out.println("Exception " + ex);
        }
    }
     
    public void addSongToPlaylistInDB(Playlist playlist, Song song){
    String stat = "INSERT INTO songInPlaylist /n"+
                  "VALUES ('?','?')";
    try (Connection xd = con){
        PreparedStatement stmt=xd.prepareStatement(stat);
        stmt.setInt(1, song.getId());
        stmt.setInt(2, playlist.getId());
        stmt.execute();
    }   catch (SQLException ex) {
            System.out.println("Exception " + ex);
        }
    }
    
    public List<Playlist> getAllPlaylistFromDB(Playlist playlist){
    List<Playlist> allPlaylists = new ArrayList<>();
    String stat = "SELECT * FROM songInPlaylist\n" +
                  "JOIN playlist on idPlaylist = playlist.id\n" +
                  "JOIN song on idSong = song.id\n" +
                  "ORDER BY playlist.id";
    try ( Connection xd = con) {
            Statement statement = xd.createStatement();
            ResultSet rs = statement.executeQuery(stat);
            
        } catch (SQLException ex) {
            System.out.println("Exception " + ex);
        }
    return allPlaylists;
    }
    
    public void createPlaylistInDB(String name){
    String stat = "INSERT INTO playlist VALUES (?)";
    try (Connection xd = con){
    PreparedStatement stmt=xd.prepareStatement(stat);
    stmt.setString(1, name);
    stmt.execute();
    }   catch (SQLException ex) {
            System.out.println("Exception " + ex);
        }
    }
    
    public Playlist createPlaylist(String name, List<Song> songsInNewPlaylist) throws IOException {
        int playlistId;
        if (isNewPlaylist) {
            playlistId = getNewPlaylistId();
        } else {
            playlistId = oldPlaylistId;
        }
        Playlist newlyCreatedPlaylist = new Playlist(playlistId, name, songsInNewPlaylist);
        isNewPlaylist = true;
        return newlyCreatedPlaylist;
    }

    
    
    public List<Playlist> addPlaylistToAllPlaylists(Playlist playlistToBeAdded, List<Playlist> allPlaylists) {
        allPlaylists.add(playlistToBeAdded);
        return allPlaylists;
    }

    
    
    public void deletePlaylist(Playlist playlistToDelete) throws IOException {
        List<Playlist> allPlaylists = new ArrayList<>();
        allPlaylists = getAllPlaylistsFromFile();
        if (true) {  // to be replaced with playlistExists method (NOT NECESSARY)
            int playlistToDeleteId = playlistToDelete.getId();
            playlistToDelete = getPlaylist(playlistToDeleteId);  // CHECK WITH JEPPE
            for (int i = 0; i < allPlaylists.size(); i++) {
                Playlist testPlaylist = allPlaylists.get(i);
                int testPlaylistId = testPlaylist.getId();
                if (testPlaylistId == playlistToDeleteId) {
                    allPlaylists.remove(i);
                    writeAllPlaylistsToFile(allPlaylists);
                    break;
                }
            }
        } else {
            System.out.println("");
            System.out.println("Song to delete doesn't exist");
            System.out.println("");
        }
    }

    
    
    public void updatePlaylist(Playlist playlistToUpdate) throws IOException {
        List<Playlist> allPlaylists = new ArrayList<>();
        allPlaylists = getAllPlaylistsFromFile();
        List<Playlist> updatedAllPlaylists = new ArrayList<>();
        int playlistToUpdateId = playlistToUpdate.getId();
        String updatedPlaylistName = playlistToUpdate.getName();  // ARE THESE NEEDED?
        List<Song> updatedSongList = playlistToUpdate.getSonglist();  // ARE THESE NEEDED?
        for (int i = 0; i < allPlaylists.size(); i++) {
            Playlist playlistBeingChecked = allPlaylists.get(i);
            int playlistBeingCheckedId = playlistBeingChecked.getId();
            if (playlistBeingCheckedId == playlistToUpdateId) {
                updatedAllPlaylists.add(playlistToUpdate);
            } else {
                updatedAllPlaylists.add(playlistBeingChecked);
            }
        }
        writeAllPlaylistsToFile(updatedAllPlaylists);
    }

    
    
    public Playlist getPlaylist(int id) throws IOException {
        List<Playlist> allPlaylists = new ArrayList<>();
        allPlaylists = getAllPlaylistsFromFile();
       for (int i = 0; i < allPlaylists.size(); i++) {
            Playlist testPlaylist = allPlaylists.get(i);
            int foundId = testPlaylist.getId();
            if (foundId == id) {
                return testPlaylist;
            }
        }
        return null;
    }

    
    
    public Playlist stringToPlaylist(String p) throws IOException {
        Playlist playlist;
        String[] arrPlaylist = p.split(",");
        int id = Integer.parseInt(arrPlaylist[0]);
        String name = arrPlaylist[1];
        List<Song> playlistSongs = new ArrayList<>();
        int i = 2;  // [0]=id, [1]=name. Songs start at [2]
        if (arrPlaylist.length >= 2) {
            while (i < arrPlaylist.length) {  // <= became <
                int songId = Integer.parseInt(arrPlaylist[i]);
                Song playlistSong = songDBDao.getSong(songId);
                playlistSongs.add(playlistSong);
                i++;
            }
            playlist = new Playlist(id, name, playlistSongs);
        } else {
            playlist = new Playlist(id, name, null);    // !!!!!!!!!!!!!!!
        }
        return playlist;
    }

    public String playlistToString(Playlist playlist) {
        int playlistId = playlist.getId();
        String playlistName = playlist.getName();
        String songListString = "";
        List<Song> playlistSongs = new ArrayList<Song>();
        playlistSongs = playlist.getSonglist();
        int playlistSongsSize = playlistSongs.size();
        if (playlistSongsSize > 0) {
            for (int i = 0; i < playlistSongsSize; i++) {
                Song songToAdd = playlistSongs.get(i);
                int songIdToAdd = songToAdd.getId();
                songListString = songListString + "," + songIdToAdd;  //removed 
            }
        }
        String playlistString = playlistId + "," + playlistName + songListString;
        return playlistString;
    }

    
    
    public List<Song> getSongListFromPlaylist(Playlist playlist) {
        List<Song> SongsInPlaylist = new ArrayList<>();
        SongsInPlaylist = playlist.getSonglist();
        return SongsInPlaylist;
    }

    
    
    public int getNewPlaylistId() throws IOException {
        List<Playlist> allPlaylists = getAllPlaylistsFromFile();
        int playlistSize = allPlaylists.size();
        Playlist lastPlaylist = allPlaylists.get(playlistSize - 1);
        int newPlaylistId = lastPlaylist.getId() + 1;
        return newPlaylistId;
    }

    
    
    public List<Playlist> getAllPlaylistsFromFile() throws FileNotFoundException, IOException {
       List<Playlist> allPlaylists = new ArrayList<>();
        File file = new File(PLAYLIST_SOURCE);
        try ( BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Playlist playlist = stringToPlaylist(line);
                    allPlaylists.add(playlist);
                } catch (Exception ex) {
                    //Do nothing we simply do not accept malformed lines of data.
                    //In a perfect world you should at least log the incident.
                }
            }
        }
        return allPlaylists;
    }



    public void writeAllPlaylistsToFile(List<Playlist> allPlaylists) {
        File file = new File(PLAYLIST_SOURCE);
        boolean isExistingFile = false;
        for (int i = 0; i < allPlaylists.size(); i++) {
            Playlist currentPlaylist = allPlaylists.get(i);
            String newPlaylistString;
            if (i == 0) {
                newPlaylistString = playlistToString(currentPlaylist);
            } else {
                newPlaylistString = "\n" + playlistToString(currentPlaylist);
            }
            try ( FileWriter fw = new FileWriter(file, isExistingFile)) {
                fw.write(newPlaylistString);
                isExistingFile = true;
            } catch (Exception e) {
                System.out.println("Error writing file while deleting, dude");
            }
        }
    }

    

}