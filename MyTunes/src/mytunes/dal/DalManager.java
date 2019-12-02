/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mytunes.be.Playlist;
import mytunes.bll.Manager;

/**
 *
 * @author Louise, Nadia, Superior Martin and Alan
 */
public class DalManager implements IDalManager{

    
    
      @Override
    public List<Playlist> getAllPlaylists() {
        try {
            return playlistDao.getAllPlaylistsFromFile();
        } catch (IOException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }


    }

    
    private PlaylistDAO playlistDao;
    public DalManager() {
        playlistDao = new PlaylistDAO();
        
        
    }
    
    
    
}
