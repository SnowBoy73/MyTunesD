/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.bll.util;

import java.util.ArrayList;
import java.util.List;
import mytunes.be.Song;

/**
 *
 * @author mac
 */
public class SearchSong {
    
    public static List<Song>searchSong(List<Song>songList,String filter)
    {
    filter = filter.toLowerCase();
        List<Song> filteredSongs = new ArrayList();
        for(Song s : songList)
        {
            if(filter.length() <= s.getTitle().length() && filter.equals(s.getTitle().toLowerCase().substring(0, filter.length())))
            {
                filteredSongs.add(s);
            }
            else if(filter.length() <= s.getArtist().length() && filter.equals(s.getArtist().toLowerCase().substring(0, filter.length())))
            {
                filteredSongs.add(s);
            }
        }
        return filteredSongs;
    
    }
    
    
}
