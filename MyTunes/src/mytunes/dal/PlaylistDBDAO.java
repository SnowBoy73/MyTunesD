/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
        
import mytunes.be.Playlist;
import mytunes.be.Song;
import static mytunes.dal.DalMethodTester.songDBDao;
import static mytunes.dal.DalMethodTester.songDao;
import mytunes.dal.SongDBDAO;


/**
 *
 * @author Louise, Nadia, Superior Martin and Alan
 */



public class PlaylistDBDAO {
    
  
    
private static final String SONG_SOURCE = "data/song_list.txt";
private static final String PLAYLIST_SOURCE = "data/playlists.txt";
boolean isNewPlaylist = true;
int oldPlaylistId = 20;

   
  
    
    public Playlist getPlaylist(int id) throws IOException {
        List<Playlist> allPlaylists = new ArrayList<>();
        allPlaylists = getAllPlaylistsFromDB();
System.out.println("" ); //
System.out.println(" Fininshed getAllPlaylistsFromFile" ); //
        for (int i = 0; i < allPlaylists.size(); i++) {
            Playlist testPlaylist = allPlaylists.get(i);
            int foundId = testPlaylist.getId();
            if (foundId == id)  {
            return testPlaylist;
            }
        }
        return null;
    }

    
     
    public Playlist createPlaylist(String name, List<Song> songsInNewPlaylist) throws IOException {
System.out.println("create playlist "); //
System.out.println("isNewPlaylist = " + isNewPlaylist); //
        int playlistId;
        if(isNewPlaylist) {
             playlistId = getNewPlaylistId();
        } else {
             playlistId = oldPlaylistId;
        }
        Playlist newlyCreatedPlaylist = new Playlist(playlistId, name, songsInNewPlaylist);
        isNewPlaylist = true;
System.out.println("new splaylist " + playlistId + name + songsInNewPlaylist);
System.out.println("Playlist Created"); //
        return newlyCreatedPlaylist;
    }
    
    
    
     public List<Playlist> addPlaylistToAllPlaylists(Playlist playlistToBeAdded, List<Playlist> playlistList) {
        //Add newSong to songList
        playlistList.add(playlistToBeAdded);
        return playlistList;
        }
     
     
    
        
    public void deletePlaylist(Playlist playlistToDelete) throws IOException {
        
        List<Playlist> allPlaylists = new ArrayList<>();
        allPlaylists = getAllPlaylistsFromDB();
        if (true) {  // to be replaced with playlistExists method (NOT NECESSARY)
            int playlistToDeleteId = playlistToDelete.getId();
            playlistToDelete = getPlaylist(playlistToDeleteId);  // CHECK WITH JEPPE

            for (int i = 0; i < allPlaylists.size(); i++) {
                Playlist playlistBeingChecked = allPlaylists.get(i);
                int playlistBeingCheckedId = playlistBeingChecked.getId();
              //  System.out.println("Movie scanned ID = " + testMovieId);
                if(playlistBeingCheckedId == playlistToDeleteId)  {
                    allPlaylists.remove(i);
                    writeAllPlayistsToDB(allPlaylists);  
                    break;
                }
            } 
        } else {
            System.out.println("");
            System.out.println("Song to delete doesn't exist");
            System.out.println("");
        }   
    }
    
    
    
    public void updatePlaylist(Playlist playlistToUpdate) throws IOException {
        List<Playlist> allPlaylists = new ArrayList<>();
        allPlaylists = getAllPlaylistsFromDB();
        List<Playlist> updatedAllPlaylists = new ArrayList<>();
        int playlistToUpdateId = playlistToUpdate.getId();
        String updatedPlaylistName = playlistToUpdate.getName();  // ARE THESE NEEDED?
        List<Song> updatedSongList = playlistToUpdate.getSonglist();  // ARE THESE NEEDED?
System.out.println("updatedAllSongs:"); //
        for (int i = 0; i < allPlaylists.size(); i++) {
            Playlist playlistBeingChecked = allPlaylists.get(i);
            int playlistBeingCheckedId = playlistBeingChecked.getId();
            if(playlistBeingCheckedId == playlistToUpdateId) {
                updatedAllPlaylists.add(playlistToUpdate);
            } else {
                updatedAllPlaylists.add(playlistBeingChecked);
           }
System.out.println(playlistBeingChecked.getId()); //
        }
        writeAllPlayistsToDB(updatedAllPlaylists);
    }
      
    
    
    
    
    /* deleteSongFromPlaylist method
    
    /* addSongToPlaylist method
    
    /* moveSongUpPlaylist method

    /* moveSongDownPlaylist method


    
    
    
    
    
    */
   
   
    public Playlist stringToPlaylist(String p) throws IOException {
        Playlist playlist;
//System.out.println("Test point: stringArrayToPlaylist" ); //
        String[] arrPlaylist = p.split(",");
        int id = Integer.parseInt(arrPlaylist[0]);
        String name = arrPlaylist[1];
System.out.println(""); //
System.out.print("Playlist id = " + id); //
System.out.print(":  Name = " + name); //
System.out.print(":  Song count = " + (arrPlaylist.length - 2)); //
        List<Song> allSongs = new ArrayList<>();
        allSongs = songDBDao.getAllSongsFromDB();
        List<Song> playlistSongs = new ArrayList<>();
        int i = 2;  // [0]=id, [1]=name. Songs start at [2]
System.out.print(":  songId's "); //
        if (arrPlaylist.length >= 2) {
            while (i < arrPlaylist.length) {  // <= became <
                int songId = Integer.parseInt(arrPlaylist[i]);
                Song playlistSong = songDBDao.getSong(songId);
System.out.print(", " + songId); //
                playlistSongs.add(playlistSong);
                i++;
            }
            playlist = new Playlist(id, name, playlistSongs);
        } else {
            playlist = new Playlist(id, name, null);
        }
        return playlist;
    }
    
    
      
    public String playlistToString(Playlist playlist) {
        int PlaylistId = playlist.getId();
        String PlaylistName = playlist.getName();
        String SongListString = "";
        List<Song> playlistSongs = new ArrayList<Song>();
        playlistSongs = playlist.getSonglist();
        int playlistSongsSize = playlistSongs.size();
        if (playlistSongsSize > 0) {
            for (int i = 0; i < playlistSongsSize; i++) {  //NEW BIT
            Song songToAdd = playlistSongs.get(i);
            int songIdToAdd = songToAdd.getId();
            SongListString = SongListString + "," + songIdToAdd;  //removed 
            }
        }
        String PlaylistString = PlaylistId + ","  + PlaylistName + SongListString;  // had "," +
        return PlaylistString;
    }
    
    
    
    
    public int getNewPlaylistId() throws IOException {
        System.out.println("newPlaylistString");
        List<Playlist> allPlaylists = getAllPlaylistsFromDB();
        int playlistSize = allPlaylists.size();
        Playlist lastPlaylist = allPlaylists.get(playlistSize - 1);
        int newPlaylistId = lastPlaylist.getId() + 1;
        return newPlaylistId;
    }
        
    
    
    
    
    
    
    
    public void writeAllPlayistsToDB(List<Playlist> allPlaylists) {
                
         // TO DO       
                
    }
        
        
    public List<Playlist> getAllPlaylistsFromDB() throws FileNotFoundException, IOException {
        
         // YET TO WRITE
        return null;
    }
    

    
    
    
    
    
    
    
    /*
    public List<Playlist> getAllPlaylistsFromFile() throws FileNotFoundException, IOException {
//System.out.println("Test point 1 "); //
        List<Playlist> allPlaylists = new ArrayList<>();
        File file = new File(PLAYLIST_SOURCE);
        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                try
                {
//System.out.println("Test point 2 getAllPlaylists while loop " ); //
                    Playlist playlist = stringToPlaylist(line);
                    allPlaylists.add(playlist);

                } catch (Exception ex)
                {
                    //Do nothing we simply do not accept malformed lines of data.
                    //In a perfect world you should at least log the incident.
                }
            }
        }
//System.out.println("/n" + "Test point: getAllPlaylists while loop end "); //
        return allPlaylists;
    }

      
        
    // NEW METHOD
    public void writeAllPlaylistsToFile(List<Playlist> allPlaylistsList) {
        File file = new File(PLAYLIST_SOURCE);
        boolean isExistingFile = false;
System.out.println(""); //
System.out.println("start writeAllPlaylistsToFile"); //
        for (int i = 0; i < allPlaylistsList.size(); i++) {
            Playlist currentPlaylist = allPlaylistsList.get(i);
            String newPlaylistString;
            if(i == 0) {
                 newPlaylistString = playlistToString(currentPlaylist);
            } else {
                 newPlaylistString = "\n" + playlistToString(currentPlaylist);
            }
            try (FileWriter fw = new FileWriter (file,isExistingFile)) {
                fw.write(newPlaylistString);
                isExistingFile = true;
            }
            catch (Exception e)  {
                System.out.println("Error writing file while deleting, dude");
            }
        }
    }
  */
    
}
