/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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

    DBConnectionProvider cp = new DBConnectionProvider();
    private Connection con;
    private Playlist playlist;
    private Song song;
    
    public List<Playlist> getAllPlaylists() {
        List<Playlist> allPlaylists = new ArrayList<>();
        String stat = "SELECT * FROM Playlist";

        try (Connection xd = cp.getConnection()) {
            Statement statement = xd.createStatement();
            ResultSet rs = statement.executeQuery(stat);
            while (rs.next()) {
                Playlist playlist = new Playlist(rs.getInt("id"), rs.getString("name"));
                allPlaylists.add(playlist);
            }
            return allPlaylists;
        } catch (SQLException ex) {
            System.out.println("Exception " + ex);
            return null;
        }
    }

    
    
    public void updatePlaylistNameInDB(Playlist playlist){
    String stat = "UPDATE playlist/n" +
                  "SET name= '?' /n" +
                  "WHERE id=?";
    try (Connection con = cp.getConnection()){
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
    try (Connection con = cp.getConnection()){
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
    try (Connection con = cp.getConnection()){
        PreparedStatement stmt=con.prepareStatement(stat);
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
    try (Connection con = cp.getConnection()){
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(stat);
            int playlistId =-1;
            Playlist pl = null;
            while(rs.next()){
                if(playlistId!=rs.getInt("playlist.id")){
                    pl = new Playlist(rs.getInt("playlist.id"),rs.getString("playlist.name"));
                    playlistId=pl.getId();
                }
                
                Song s = new Song(
                    rs.getInt("song.id"),
                    rs.getString("song.title"),
                    rs.getString("song.artist"),
                    rs.getString("song.category"),
                    rs.getInt("song.duration"),
                    rs.getString("song.path"));
                pl.addSongToList(song);
                
            }
            
        } catch (SQLException ex) {
            System.out.println("Exception " + ex);
        }
    return allPlaylists;
    }
    
    public void createPlaylistInDB(String name){
    String stat = "INSERT INTO playlist VALUES (?)";
    try (Connection con = cp.getConnection()){
    PreparedStatement stmt=con.prepareStatement(stat);
    stmt.setString(1, name);
    stmt.execute();
    }   catch (SQLException ex) {
            System.out.println("Exception " + ex);
        }
    }
}
