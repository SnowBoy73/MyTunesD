/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.be;

import mytunes.bll.Duration;
/**
 *
 * @author Louise, Nadia, Superior Martin and Alan
 */


public class Song {
    private int id;  // was final
    private String title;
    private String artist;
    private String category;
    private String duration;

    
    
    public Song(int id, String title, String artist, String category, String duration) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.category = category;
        this.duration = duration;
    }

    
    
    public int getId() {
        return id;
    }

    
    
    public String getTitle() {
        return title;
    }

    
    
   public String getArtist() {
        return artist;
    }

    
   
   public String getCategory() {
        return category;
    }

    
    
   public String getDuration() {
        return duration;
   }
   
    
    
    public void setId() { // may not need this. If not set id variable to final
        this.id = id;
    }

    
    
   public void setTitle(String title) {
        this.title = title;
    }

   
   
     public void setArtist() {
        this.artist = artist;
    }

     
    
    public void setCatagory() {
        this.category = category;
    }

   
   
     public void setDuration(int m, int s) {
        this.duration = duration;
        
    }
    
    
}