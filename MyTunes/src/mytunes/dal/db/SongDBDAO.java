/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal.db;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mytunes.be.Song;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 *
 * @author admin
 */
public class SongDBDAO {

    DBConnectionProvider cp = new DBConnectionProvider();

    /*
    gets all songs from the database and adds them to an arraylist
    */
    public List<Song> getAllSongs() {
        List<Song> allSongs = new ArrayList<>();
        String stat = "SELECT * FROM Song";

        try (Connection xd = cp.getConnection()) {
            Statement statement = xd.createStatement();
            ResultSet rs = statement.executeQuery(stat);
            while (rs.next()) {
                Song song = new Song(rs.getInt("id"), rs.getString("title"), rs.getString("artist"), rs.getString("category"), rs.getInt("duration"), rs.getString("path"));
                allSongs.add(song);
            }
            return allSongs;
        } catch (SQLException ex) {
            System.out.println("Exception " + ex);
            return null;
        }
    }

    public List<Song> getAllSongsWithFilter(String filterText) {
        List<Song> allSongs = new ArrayList<>();
        String stat = "SELECT * FROM Song WHERE Song.title LIKE ? OR Song.artist LIKE ?";

        try (Connection xd = cp.getConnection()) {
            PreparedStatement statement = xd.prepareStatement(stat);
            statement.setString(1, "%" + filterText + "%");
            statement.setString(2, "%" + filterText + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Song song = new Song(rs.getInt("id"), rs.getString("title"), rs.getString("artist"), rs.getString("category"), rs.getInt("duration"), rs.getString("path"));
                allSongs.add(song);
            }
            return allSongs;
        } catch (SQLException ex) {
            System.out.println("Exception " + ex);
            return null;
        }
    }

    /*
    creates a new song in the database using data inputs in the GUI
    */
    public void addSong(Song song) {
        String stat = "INSERT INTO Song VALUES (?,?,?,?,?)";
        try (Connection xd = cp.getConnection()) {
            PreparedStatement stmt = xd.prepareStatement(stat, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, song.getTitle());
            stmt.setString(2, song.getArtist());
            stmt.setString(3, song.getCategory());
            stmt.setInt(4, song.getDuration());
            stmt.setString(5, song.getPath());
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    song.setId((int) generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(SongDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SongDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*
    Uses ID to delete song from database
    */
    public void deleteSongFromDB(Song song) {
        String stat = "DELETE FROM song WHERE ID=?";
        try (Connection con = cp.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(stat);
            stmt.setInt(1, song.getId());
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Exception " + ex);
        }
    }

    /*
    Used to update the song in the database, be it either title, artist or similar
    */
    public void updateSongInDB(Song song) {
        String stat = "UPDATE song\n"
                + "SET title=?, artist=?, category=?, duration=?, path=?\n"
                + "WHERE ID=?";
        try (Connection con = cp.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(stat);
            stmt.setString(1, song.getTitle());
            stmt.setString(2, song.getArtist());
            stmt.setString(3, song.getCategory());
            stmt.setInt(4, song.getDuration());
            stmt.setString(5, song.getPath());
            stmt.setInt(6, song.getId());
            stmt.execute();
        } catch (SQLServerException ex) {
            Logger.getLogger(SongDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SongDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    

    

    

    

    public Song getSong(int id) throws IOException {
        List<Song> allSongs = new ArrayList<>();
        allSongs = getAllSongs();
        for (int i = 0; i < allSongs.size(); i++) {
            Song testSong = allSongs.get(i);
            int foundId = testSong.getId();
            if (foundId == id) {

                return testSong;
            }
        }
        return null;
    }
    /* 
    ALAN'S CODE
    
   
package mytunes.dal;

<<<<<<< HEAD

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mytunes.be.Song;

=======
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mytunes.be.Song;
import java.sql.ResultSet;
>>>>>>> 479f1a185a32c87369edc2aa433326aad61bca66


 // @author   Louise, Nadia, Superior Martin and Alan
 


public class SongDBDAO {
<<<<<<< HEAD
    
private static final String MUSIC_SOURCE = "music/Belshazzar.mp3";
private static final String SONG_SOURCE = "data/song_list.txt";  // WILL USE DB REFENCES HERE
boolean isNewSong = true;
int oldSongId;



   
    
   
    public Song createSong(String title, String artist, String category, int duration, String path) throws IOException {
System.out.println("create song "); //
System.out.println("isNewSong = " + isNewSong); //
        int songId;
        if(isNewSong) {
             songId = getNewSongId();
        } else {
             songId = oldSongId;
        }
        Song newlyCreatedSong = new Song(songId, title, artist, category, duration, path);
        isNewSong = true;
System.out.println("new song "+ songId + title + artist + category + duration);
System.out.println("Song Created"); //
        return newlyCreatedSong;
    }
        
    
    
    public List<Song> addSongToSongList(Song songToBeAdded, List<Song> songList) {
        songList.add(songToBeAdded);
        return songList;
        }
        
        
   
    public void deleteSong(Song songToDelete, List<Song> songlist) throws IOException {
        List<Song> allSongs = new ArrayList<>();
allSongs = getAllSongsFromDB(); //  This won't work. needs to get songs from playlist. Maybe need a deleteSongFromPlaylist, ond 
        if (true) {  // to be replaced with SongExists method (NOT NECESSARY)
            int songToDeleteId = songToDelete.getId();
            songToDelete = getSong(songToDeleteId);
            for (int i = 0; i < allSongs.size(); i++) {
                Song testSong = allSongs.get(i);
                int testSongId = testSong.getId();
                if(testSongId == songToDeleteId)  {
                    allSongs.remove(i);
                    writeSongListToDB(allSongs);  
                    break;
                }
            } 
        } else {
            System.out.println("");
            System.out.println("Song to delete doesn't exist");
            System.out.println("");
        }   
    }

    
    
    public void updateSong(Song songToUpdate) throws IOException {
        List<Song> allSongs = new ArrayList<>();
        allSongs = getAllSongsFromDB();
        List<Song> updatedAllSongs = new ArrayList<>();
        int songToUpdateId = songToUpdate.getId();
        String updatedSongTitle = songToUpdate.getTitle();
        String updatedSongArtist = songToUpdate.getArtist();
        String updatedSongCategory = songToUpdate.getCategory();
        int updatedSongDuration = songToUpdate.getDuration();
System.out.println("updatedAllSongs:"); //
        for (int i = 0; i < allSongs.size(); i++) {
            Song songBeingChecked = allSongs.get(i);
            int songBeingCheckedId = songBeingChecked.getId();
            if(songBeingCheckedId == songToUpdateId) {
                updatedAllSongs.add(songToUpdate);
            } else {
                updatedAllSongs.add(songBeingChecked);
           }
System.out.println(songBeingChecked.getId()); //
        }
        writeSongListToDB(updatedAllSongs);
}

    
   
    public Song getSong(int id) throws IOException {
        List<Song> allSongs = new ArrayList<>();
        allSongs = getAllSongsFromDB();
        for (int i = 0; i < allSongs.size(); i++) {
            Song testSong = allSongs.get(i);
            int foundId = testSong.getId();
            if (foundId == id)  {
            
            return testSong;
            }
        }
        return null;
    }

    
   
    public Song stringArrayToSong(String t) {
        String[] arrSong = t.split(",");
        int id = Integer.parseInt(arrSong[0]);
        String title = arrSong[1];
        String artist = arrSong[2];
        String category = arrSong[3];
        int duration = Integer.parseInt(arrSong[4]);
        String path = arrSong[5];
        Song song = new Song(id, title, artist, category, duration, path);
        return song;
    }

    
    
    public String songToString(Song song) {
        int newSongId = song.getId();
        String newSongTitle = song.getTitle();
        String newSongArtist = song.getArtist();
        String newSongCatagory = song.getCategory();
        int newSongDuration = song.getDuration();
        String newSongString = newSongId + ","  + newSongTitle + "," + newSongArtist + "," + newSongCatagory + "," + newSongDuration;
        return newSongString;
    }
    
    
    
    public int getNewSongId() throws IOException {
        List<Song> allSongs = getAllSongsFromDB();
        int songListSize = allSongs.size();
System.out.println("allSongs size = " + songListSize); //
        Song lastSong = allSongs.get(songListSize - 1);
        int newSongId = lastSong.getId() + 1;
        return newSongId;
    }
        
    
    
    public int getOldSongId() throws IOException {
    return oldSongId;
    }
    
    
    
    
    
    public void writeSongListToDB(List<Song> songList) {
                
         // TO DO       
                
    }
        
        
    public List<Song> getAllSongsFromDB() throws FileNotFoundException, IOException {
        
         // YET TO WRITE
        return null;
    }
    

  
   
     */
 /* MARTIN'S CODE
    public List<Song> getAllSongs() {
        List<Song> allSongs = new ArrayList<>();
        String stat = "SELECT * FROM Song";

        try ( Connection xd = con) {
            Statement statement = xd.createStatement();
            ResultSet rs = statement.executeQuery(stat);
            while (rs.next()) {

 parent of f33ab64... Merge branch 'master' of https://github.com/SnowBoy73/MyTunesD
                Song song = new Song(rs.getInt("id"), rs.getString("title"), rs.getString("artist"), rs.getString("category"), rs.getInt("duration"));
                allSongs.add(song);
            }
            return allSongs;
        } catch (SQLException ex) {
            System.out.println("Exception " + ex);
            return null;
        }
    }

    public SongDBDAO(Connection con) {
        this.con = con;
    }
    private Connection con;
     */

}
