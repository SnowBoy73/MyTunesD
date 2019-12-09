/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui.model;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Song;
import mytunes.bll.util.SearchSong;
/**
 *
 * @author mac
 */
public class Songmodel {
    
    
    
    
    
     private ObservableList<Songmodel> songList;
     
     
     
     
     private Songmodel(){

     
     }
     
     public ObservableList<Songmodel> getSongs()
    {
        return songList;
    }
      
     
    /* 
      public ObservableList<Song> getFilteredSongs(String filter)
    {
       ObservableList<Song> filteredList = FXCollections.observableArrayList(SearchSong.searchSong(songList, filter));
       return filteredList;
    }
     
      
     
        
        
        
       
        
 */   
}

    