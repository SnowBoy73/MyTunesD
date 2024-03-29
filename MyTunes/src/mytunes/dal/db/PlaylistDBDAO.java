/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal.db;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mytunes.be.Playlist;
import mytunes.be.Song;

/**
 *
 * @author mega_
 *
 * /*
 * SELECT * FROM songInPlaylist JOIN playlist ON
 * playlist.id=songInPlaylist.idPlaylist JOIN song ON
 * song.id=songInPlaylist.idSong ORDER BY idPlaylist
 */
public class PlaylistDBDAO {

    private DBConnectionProvider cp = new DBConnectionProvider();

    /*
    Takes a name string from the GUI level and sends to to the database, changing the name
    */
    public void updatePlaylistNameInDB(Playlist playlist) {
        String stat = "UPDATE playlist\n"
                + "SET name=? \n"
                + "WHERE id=?";
        try (Connection con = cp.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(stat);
            stmt.setString(1, playlist.getName());
            stmt.setInt(2, playlist.getId());
            stmt.execute();
        } catch (SQLServerException ex) {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }


    public void removeSongFromPlaylist(){
    
    }
    
    
    /*
    Deletes playlist from database with help from id, delivered from the GUI layer
    */
    public void deletePlaylist(Playlist playlist) {
        String stat = "DELETE FROM playlist WHERE id=?";
        try (Connection con = cp.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(stat);
            stmt.setInt(1, playlist.getId());
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Exception " + ex);
        }
    }

    /*
    Uses an id of a song and of a playlist to combine/connect them in the database
    */
    public void addSongToPlaylist(Playlist playlist, Song song) {
        String stat = "INSERT INTO songInPlaylist \n"
                + "VALUES (?,?)";
        try (Connection con = cp.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(stat);
            stmt.setInt(1, song.getId());
            stmt.setInt(2, playlist.getId());
            stmt.execute();
        } catch (SQLServerException ex) {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    Gets all playlists that are currently in the database and makes it tangible for java to read and use, 
    adding it to a list including both the playlists and the songs in said playlist
    */
    public List<Playlist> getAllPlaylists() {
        List<Playlist> allPlaylists = new ArrayList<>();
        String stat ="SELECT playlist.id AS pid, playlist.name, song.*, songInPlaylist.* FROM playlist\n" +
        "LEFT JOIN songInPlaylist on idPlaylist = playlist.id\n" +
        "LEFT JOIN song on idSong = song.id\n" +
        "ORDER BY playlist.id";
        /*String stat = "SELECT * FROM songInPlaylist\n"
                + "RIGHT JOIN playlist on idPlaylist = playlist.id\n"
                + "LEFT JOIN song on idSong = song.id\n"
                + "ORDER BY playlist.id";*/
        try (Connection con = cp.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(stat);
            int playlistId = -1;
            Playlist pl = null;
            while (rs.next()) {
                if( rs.getInt("idPlaylist")==0){ // No songs on playlist
                    allPlaylists.add(new Playlist(rs.getInt("pid"), rs.getString("name")));
                }
                else
                {
                    if (playlistId != rs.getInt("pid")) {
                        pl = new Playlist(rs.getInt("pid"), rs.getString("name"));
                        playlistId = pl.getId();
                        allPlaylists.add(pl);
                    }

                    Song s = new Song(
                            rs.getInt("idSong"),
                            rs.getString("title"),
                            rs.getString("artist"),
                            rs.getString("category"),
                            rs.getInt("duration"),
                            rs.getString("path"));
                    pl.addSongToList(s);
                }

            }
            return allPlaylists;

        } catch (SQLException ex) {
            System.out.println("Exception " + ex);
        }
        return allPlaylists;
    }

    /*
    Adds a new playlist to the database with name string from GUI layer
    */
    public void addPlaylist(Playlist playlist) {
        String stat = "INSERT INTO Playlist VALUES (?)";
        try (Connection xd = cp.getConnection()) {
            PreparedStatement stmt = xd.prepareStatement(stat, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, playlist.getName());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    playlist.setId((int) generatedKeys.getLong(1));
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

}
