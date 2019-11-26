/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.be;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Louise, Nadia, Superior Martin and Alan
 */

public class Playlist {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alan
 */
//public class Song {
    private int id;  // was final
    private String name;
    private List<Song> songList = new ArrayList<>();
    
    
    
    public Playlist(int id, String name, String artist, String category, String duration) {
        this.id = id;
        this.name = name;
        this.songList = songList;
    }

    
    
    public int getId() {
        return id;
    }

    
    
    public String getName() {
        return name;
    }

    
    
   public List<Song> getSonglist() {
        return songList;
    }

   
    
    public void setId() { // may not need this. If not set id variable to final
        this.id = id;
    }

    
    
   public void setName(String name) {
        this.name = name;
    }

   
   
     public void setSonglist() {
        this.songList = songList;
    }

    

}
