/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.be;


/**
 * @author Louise, Nadia, Superior Martin and Alan
 */


public class Song {
    private int id;  // was final
    private String title;
    private String artist;
    private String category;
    private int duration;  // in seconds
    private String path;

    
    
    public Song(int id, String title, String artist, String category, int duration, String path) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.category = category;
        this.duration = duration;
        this.path=path;
    }

     @Override
    public String toString() {
        return this.title + " " + this.artist + " " + this.category; //To change body of generated methods, choose Tools | Templates.
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

    
    
   public int getDuration() {
        return duration;
   }
   
    public String getPath(){
      return path;
    }
    
    public void setId(int id) { // may not need this. If not set id variable to final
        this.id = id;
    }

    
    
   public void setTitle(String title) {
        this.title = title;
    }

   
   
     public void setArtist(String artist) {
        this.artist = artist;
    }

     
    
    public void setCatagory(String category) {
        this.category = category;
    }

   
   
     public void setDuration(int duration) {  // probably unnecessay method
        this.duration = duration;
        
    }
    
    public void setPATH(String path){
       this.path=path;
    }
}
