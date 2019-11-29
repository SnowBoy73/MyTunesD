/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mytunes.dal.SongDAO;
import mytunes.dal.PlaylistDAO;
import mytunes.be.Song; 
import mytunes.be.Playlist; 


/**
 *
 * @author Alan
 */

public class DalMethodTester {
 


    public static SongDAO songDao = new SongDAO();
    public static PlaylistDAO playlistDao = new PlaylistDAO();

    
    
    public static void main(String[] args) throws IOException
    {
        //testCreateSong();  // Tests CreateSong method
        //  listSongs();
        testGetSong();
        //testDeleteSong();
        testUpdateSong();
        //listSongs();
//System.out.println("Start "); //
        //listPlaylists();
//System.out.println("End "); //
       
    }
    
    
    
    public static void listSongs() throws IOException  {
            
        List<Song> allSongs = songDao.getAllSongsFromFile();
        for (Song allSong : allSongs)
        {
System.out.println(allSong.getTitle());
        }
System.out.println("");  //
System.out.println("Song count: " + allSongs.size()); //
System.out.println("");  //
   
    }

    
    
     public static void testCreateSong() throws IOException {
        String title = "Hump Day";
        String artist = "Tin Huey";
        String category = "ProgRock";
        int duration = 180;
        Song createdSong = songDao.createSong(title, artist, category, duration);  // Tests CreateSong method
        List<Song> updatedSongList = new ArrayList<>();
        List<Song> songList = songDao.getAllSongsFromFile();
        updatedSongList = songDao.addSongToSongList(createdSong, songList);
        songDao.writeSongListToFile(updatedSongList);
     }
     
        
     
    public static void testDeleteSong() throws IOException {
        List<Song> allSongs = new ArrayList<Song>();
        allSongs = songDao.getAllSongsFromFile();
        int lastSongID = songDao.getNewSongId() - 1;
        Song songToDelete = songDao.getSong(lastSongID);
        songDao.deleteSong(songToDelete, allSongs);  // Tests deleteSong method

System.out.println("lastSongID = " + lastSongID); 
   
        //System.out.print("Song to delete: " + songToDelete);
        //System.out.println("");        
    }
    
    
    
    
    public static void testGetSong() throws IOException {
        int testId = (int)Math.round(Math.random()*10);  //
        Song testSong = songDao.getSong(testId);
        String testTitle = testSong.getTitle();
        String testArtist = testSong.getArtist();
        String testCategory = testSong.getCategory();
        int testDuration = testSong.getDuration();

System.out.println("");
System.out.print("Song with ID " + testId + " is: ");
System.out.print("\"" + testTitle + "\", by " +  testArtist + " in the category " + testCategory + " with duration " + testDuration);
System.out.println("");    
    }
    
 
    
    
    public static void testUpdateSong() throws IOException  {
    List<Song> allSongs = new ArrayList<Song>();
    allSongs = songDao.getAllSongsFromFile();
            int duration = (int)Math.round(Math.random()*300) + 20;  //
    Song updatedSong;
    updatedSong = new Song(11, "Updated Song", "The Drunks", "Reggae", duration);
    songDao.updateSong(updatedSong);
    }
    
    
    
    
    
       
    public static void listPlaylists() throws IOException {
        List<Playlist> playlists = playlistDao.getAllPlaylists();
System.out.println("listPlaylists Playlist count: " + playlists.size()); //

        
        for (Playlist allPlaylist : playlists) {
        int testPlaylistId = 1;
        Playlist testPlaylist = playlistDao.getPlaylist(testPlaylistId);
        System.out.println("Playlist ID: " + testPlaylist.getId() + "/n");
        System.out.println("Playlist Name: " + testPlaylist.getName() + "/n");
        System.out.println("");  //
        //System.out.println("Song count: " + allSongs.size()); //
        System.out.println("");  //
        }
    }  


}