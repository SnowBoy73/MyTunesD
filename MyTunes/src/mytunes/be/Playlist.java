/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.be;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Louise, Nadia, Superior Martin and Alan
 */


public class Playlist {
    
 
    private int id;  // was final
    private String name;
    //private int NoOfSongs;
    List <Song> songList = new ArrayList<>();

    //NoOfSongs = playList.size();
    
    
    public Playlist(int id, String name) {
        this.id = id;
        this.name = name;
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

     public void addSongToList(Song songToAdd){
         songList.add(songToAdd);
     }
    

}