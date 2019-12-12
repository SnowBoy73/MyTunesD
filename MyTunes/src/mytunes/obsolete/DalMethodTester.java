/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.obsolete;

import mytunes.dal.db.PlaylistDBDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mytunes.obsolete.file.SongDAO;
import mytunes.obsolete.file.PlaylistDAO;
import mytunes.be.Song; 
import mytunes.be.Playlist; 


/**
 *
 * @author Alan
 */

public class DalMethodTester {
 


    //public static SongDAO songDao = new SongDAO();
    public static PlaylistDBDAO playlistDBDao = new PlaylistDBDAO();

    public static SongDAO songDao = new SongDAO();
    public static PlaylistDAO playlistDao = new PlaylistDAO();
    
    public static void main(String[] args) throws IOException
    {
      // SONG TESTS
        //testCreateSong();  // Tests CreateSong method
        //  displayAllSongs();
        testGetSong();
        //testDeleteSong();
        //testUpdateSong();
          displayAllSongs();
//System.out.println("Start "); //
        //displayAllPlaylists();
//System.out.println("End "); //
     //PLAYLIST TESTS 
        //testGetPlaylist();
        //testCreatePlaylist();  // Tests CreateSong method
        //  listSongs();
        //testGetPlaylist();
       // testDeletePlaylist();
        testUpdatePlaylist();
        testGetPlaylist();
       
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
    
    
    
    public static void displayAllSongs() throws IOException  {
            
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
        String path = "music/Belshazzar.mp3";
        Song createdSong = songDao.createSong(title, artist, category, duration,path); 
        // Tests CreateSong method
        List<Song> updatedSongList = new ArrayList<>();
        List<Song> songList = songDao.getAllSongsFromFile();
        updatedSongList = songDao.addSongToSongList(createdSong, songList);
        songDao.writeSongListToFile(updatedSongList);
     }
     
        
     
    public static void testDeleteSong() throws IOException {
        List<Song> allSongs = new ArrayList<Song>(); //  ArrayList<Song>() OR ArrayList<>()
        allSongs = songDao.getAllSongsFromFile();
        int lastSongID = songDao.getNewSongId() - 1;
        Song songToDelete = songDao.getSong(lastSongID);
        songDao.deleteSong(songToDelete, allSongs);  // Tests deleteSong method

System.out.println("lastSongID = " + lastSongID); 
//System.out.print("Song to delete: " + songToDelete);
//System.out.println("");        
    }
    
    
    
    public static void testUpdateSong() throws IOException  {
    List<Song> allSongs = new ArrayList<Song>();
    allSongs = songDao.getAllSongsFromFile();
    int duration = (int)Math.round(Math.random()*300) + 20;  //
    String path = "music/Belshazzar.mp3";

    Song updatedSong;
    updatedSong = new Song(11, "Updated Song", "The Drunks", "Reggae", duration, path);
    songDao.updateSong(updatedSong);
    }
    
    
    
    
    
    
    
    
    public static void testGetPlaylist() throws IOException {
        int testId = (int)Math.round(Math.random()*10);  //
        Playlist testPlaylist = playlistDao.getPlaylist(testId);
        String testPlaylistString = playlistDao.playlistToString(testPlaylist);
System.out.println(""); //
System.out.println("Test getPlaylist ID: " + testPlaylistString);
System.out.println("");
    }
    
    
    
    public static void displayAllPlaylists() throws IOException {
        List<Playlist> allPlaylists = playlistDao.getAllPlaylistsFromFile();
        int allPlaylistsSize = allPlaylists.size();
System.out.println(" Playlist count: " + allPlaylistsSize); //
        if(allPlaylistsSize > 0) {
            for (Playlist currentPlaylist : allPlaylists) {
                String currentPlaylistString = playlistDao.playlistToString(currentPlaylist);
System.out.println("Playlist ID: " + currentPlaylistString);
            }
        }
    }
     
    
    
    public static void testCreatePlaylist() throws IOException {
 System.out.println("Test createPlaylist start");

        int newPlaylistId = playlistDao.getNewPlaylistId();
System.out.println("new pl id = " + newPlaylistId);

        String newPlaylistString = newPlaylistId + "," + "New Playlist,4,5,6";
System.out.println("newPlaylistString = " + newPlaylistString);

        Playlist createdPlaylist = playlistDao.stringToPlaylist(newPlaylistString);  // Tests CreateSong method
        List<Playlist> updatedAllPlaylists = new ArrayList<>();
        List<Playlist> allPlaylists = playlistDao.getAllPlaylistsFromFile();
        updatedAllPlaylists = playlistDao.addPlaylistToAllPlaylists(createdPlaylist, allPlaylists);
        playlistDao.writeAllPlaylistsToFile(updatedAllPlaylists);
     }
     
    
    
    public static void testDeletePlaylist() throws IOException { 
        List<Playlist> allPlaylistss = new ArrayList<>();
        allPlaylistss = playlistDao.getAllPlaylistsFromFile();
        int lastPlaylistID = playlistDao.getNewPlaylistId() - 1;
        Playlist playlistToDelete = playlistDao.getPlaylist(lastPlaylistID);
        playlistDao.deletePlaylist(playlistToDelete);  // Tests deleteSong method

System.out.println("lastPlaylistID = " + lastPlaylistID); 
//System.out.print("Song to delete: " + songToDelete);
//System.out.println("");        
    }
    
    
    
      public static void testUpdatePlaylist() throws IOException  {
    List<Playlist> allPlaylists = new ArrayList<Playlist>();
    allPlaylists = playlistDao.getAllPlaylistsFromFile();
            int rndPLnumber = (int)Math.round(Math.random()*300) + 20;  //
    Playlist updatedPlaylist;
    String updatedPlaylistName = "UPDATED-" + rndPLnumber;
    Playlist samplePlaylist =  playlistDao.getPlaylist(5);
    List<Song> UppdateSongList = samplePlaylist.getSonglist();
    updatedPlaylist = new Playlist(11, updatedPlaylistName, UppdateSongList);
    playlistDao.updatePlaylist(updatedPlaylist);
    }
    
   
       
}