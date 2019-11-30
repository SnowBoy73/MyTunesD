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

    public SongDBDAO(Connection con) {
        this.con = con;
    }
    private Connection con;
}
