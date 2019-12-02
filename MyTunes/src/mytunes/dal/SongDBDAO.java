/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal;

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

    public List<Song> getAllSongs() {
        List<Song> allSongs = new ArrayList<>();
        String stat = "SELECT * FROM Song";

        try ( Connection xd = con) {
            Statement statement = xd.createStatement();
            ResultSet rs = statement.executeQuery(stat);
            while (rs.next()) {
                Song song = new Song(rs.getInt("id"), rs.getString("title"), rs.getString("artist"), rs.getString("category"), rs.getInt("duration"));
                allSongs.add(song);
            }
            return allSongs;
        } catch (SQLException ex) {
            System.out.println("Exception " + ex);
            return null;
        }
    }
    
    public void addSongToDB(String title, String artist, String category, int duration){
    String stat = "INSERT INTO Song VALUES (?,?,?,?)";
    try ( Connection xd = con){
        PreparedStatement stmt=xd.prepareStatement(stat);
        stmt.setString(1, title);
        stmt.setString(2, artist);
        stmt.setString(3, category);
        stmt.setInt(4, duration);
        stmt.execute();
    }   catch (SQLException ex) {
            System.out.println("Exception " + ex);
        }
    
}
    
    public void removeSongFromDB(Song son){
    String stat = "DELETE FROM song WHERE ID=?";
    try (Connection xd = con){
    PreparedStatement stmt = con.prepareStatement(stat);
    stmt.setInt(1, son.getId());
    stmt.execute();
    }   catch (SQLException ex) {
            System.out.println("Exception " + ex);
        }
    }
    
    
    
    public SongDBDAO(Connection con) {
        this.con = con;
    }
    private Connection con;
}
