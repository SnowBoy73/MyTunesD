/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.model;

import java.util.List;
import mytunes.be.Playlist;
import mytunes.bll.IManager;
import mytunes.bll.Manager;

/**
 *
 * @author admin
 */
public class PlaylistModel {

    private IManager blManager;
    
    
    public PlaylistModel() {
        blManager = new Manager();
   
    }
    
    public List<Playlist> getAllPlaylists() {
    return blManager.getAllPlaylists();
    
    }
    
}
