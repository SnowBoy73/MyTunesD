/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal;

import java.io.IOException;
import java.util.List;

import mytunes.dal.SongDAO;
import mytunes.be.Song; 


/**
 *
 * @author Alan
 */

public class DalMethodTester {
 


    private static SongDAO songDao = new SongDAO();

    
    
    public static void main(String[] args) throws IOException
    {
         testCreateSong();  // Tests CreateSong method
        //listSongs();
        testGetSong();
         //   songDao.testDeleteSOng();
         //  testUpdateSong();
        listSongs();

    }
    
    
    
    public static void listSongs() throws IOException  {
            
        List<Song> allSongs = songDao.getAllSongs();
        for (Song allSong : allSongs)
        {
           System.out.println(allSong.getTitle());
        }
        System.out.println("");  //
        System.out.println("Song count: " + allSongs.size()); //
          //
        System.out.println("");  //
   
    }

    
    
     public static void testCreateSong() throws IOException {
        String title = "Hump Day";
        String artist = "Tin Huey";
        String category = "ProgRock";
        String duration = "4.20";
        songDao.createSong(title, artist, category, duration);  // Tests CreateMovie method
    }
     
        
     
    public static void testDeleteSong() throws IOException {
        int songToDeleteId = 20; // Test delete song with id 20
        Song songToDelete = songDao.getSong(songToDeleteId); // Tests DeleteSong method
        //System.out.print("Song to delete: " + songToDelete);
        //System.out.println("");        
        //System.out.println("");
        songDao.deleteSong(songToDelete);  // Tests CreateSong method
    }
    
    
    
    
    public static void testGetSong() throws IOException {
        int testId = (int)Math.round(Math.random()*10);  //
        Song testSong = songDao.getSong(testId);
        String testTitle = testSong.getTitle();
        String testArtist = testSong.getArtist();
        String testCategory = testSong.getCategory();
        String testDuration = testSong.getDuration();

        System.out.println("");
        System.out.print("Song with ID " + testId + " is: ");
        System.out.print("\"" + testTitle + "\", by " +  testArtist + " in the category " + testCategory + " with duration " + testDuration);
        System.out.println("");    
    }
    
 
    
    
    public static void testUpdateSong() throws IOException  {
    Song updatedSong;
    updatedSong = new Song(5, "Updated Song", "The Drunks", "Reggae","4:20");
    songDao.updateSong(updatedSong);
    }
    
}
