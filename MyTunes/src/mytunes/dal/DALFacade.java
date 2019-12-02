/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.util.List;
import mytunes.be.Song;

/**
 *
 * @author Martin
 */
public class DALFacade implements IDAL {

    private SongDBDAO songDAO;

    public DALFacade() throws SQLServerException {
        DBConnectionProvider DBCon = new DBConnectionProvider();
        this.songDAO = new SongDBDAO(DBCon.getConnection());
        
    }
    
    @Override
    public List<Song> getAllSongs() {
        return songDAO.getAllSongs();
    }

    @Override
    public void deleteSong(Song songToDelete) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
