/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal.db;

import java.sql.Connection;
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

    public void savePlaylist(Playlist playlist) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deletePlaylist(Playlist playlist) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
