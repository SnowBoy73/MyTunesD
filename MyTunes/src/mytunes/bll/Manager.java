/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.bll;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mytunes.be.Playlist;
import mytunes.dal.DalManager;
import mytunes.dal.IDalManager;
import mytunes.dal.PlaylistDAO;
import mytunes.dal.SongDAO;

/**
 *
 * @author Louise, Nadia, Superior Martin and Alan
 */

public class Manager implements IManager {

    @Override
    public List<Playlist> getAllPlaylists() {
        return dalManager.getAllPlaylists();
      
    }

    
    private IDalManager dalManager;
    public Manager() {
        dalManager = new DalManager();
        
        
    }

    @Override
    public Playlist getPlaylist(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
