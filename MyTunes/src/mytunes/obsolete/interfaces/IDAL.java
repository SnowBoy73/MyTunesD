/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.obsolete.interfaces;

import java.util.List;
import mytunes.be.Song;

/**
 *
 * @author Martin
 */
public interface IDAL {
    List<Song> getAllSongs();
    
    void deleteSong(Song songToDelete);
}
