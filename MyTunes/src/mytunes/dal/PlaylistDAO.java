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
import static mytunes.dal.DalMethodTester.songDao;
import mytunes.dal.SongDAO;


/**
 *
 * @author Louise, Nadia, Superior Martin and Alan
 */

public class PlaylistDAO {
    
  
    
private static final String SONG_SOURCE = "data/song_list.txt";
private static final String PLAYLIST_SOURCE = "data/playlists.txt";
boolean isNewPlaylist = true;
int oldPlaylistId = 20;
//List<Song> allSongs = new ArrayList<>();
   // List<Song> allSongs = mytunes.dal.songDao.getAllSongs();
   // public static SongDAO songDao = new SongDAO();



   
  
    
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
    
    
    
     public List<Playlist> addPlaylistToPlaylistList(Playlist playlistToBeAdded, List<Playlist> playlistList) {
        //Add newSong to songList
        playlistList.add(playlistToBeAdded);
        return playlistList;
        }
     
     
        
   /*  old version
    public Playlist createPlaylist(String name, List<Song> songsInNewPlaylist) throws IOException {
        int newPlaylistId = getNewPlaylistId();
        Playlist newPlaylist = new Playlist(newPlaylistId, name, songsInNewPlaylist );
        return 
    String newSongString;
        //Add newSong to songList
        List<Song> songList = new ArrayList<>();
        songList = getAllSongs();
        songList.add(newSong);
        //Add newSong to File
        File file = new File(SONG_SOURCE);
        try (FileWriter fw = new FileWriter (file, true)) {  
            newSongString = "\n" + songToString(newSong);
            fw.write(newSongString);
            fw.close();
        }
         catch (Exception e)  {
             System.out.println("Error writing file, dude");
         }
        return newSong;
    }

    */
    
  /* 
    public void deleteSong(Song songToDelete) throws IOException {
        
        List<Song> allPlaylistSongs = new ArrayList<>();
        allPlaylistSongs = getAllPlaylistSongs();
       
        if (true) {  // to be replaced with movieExists method (NOT NECESSARY)
            int songToDeleteId = songToDelete.getId();
            songToDelete = getSong(songToDeleteId);

            for (int i = 0; i < allSongs.size(); i++) {
                Song testSong = allSongs.get(i);
                int testSongId = testSong.getId();
              //  System.out.println("Movie scanned ID = " + testMovieId);
                if(testSongId == songToDeleteId)  {
                    allSongs.remove(i);
                    writeSongListToFile(allSongs);  
                    break;
                }
            } 
        } else {
            System.out.println("");
            System.out.println("Song to delete doesn't exist");
            System.out.println("");
        }   
    }
    */
    
    
    /*
    public void updateSong(Song song) throws IOException {
    int songToUpdateId = song.getId();
    String updatedSongTitle = song.getTitle();
    String updatedSongArtist = song.getArtist();
    String updatedSongCategory = song.getCategory();
    int updatedSongDuration = song.getDuration();

    deleteSong(song);
    setNewId(songToUpdateId);
    createSong(updatedSongTitle,updatedSongArtist,updatedSongCategory, updatedSongDuration);
    }
*/
    
   
    public Playlist getPlaylist(int id) throws IOException {
        List<Playlist> allPlaylists = new ArrayList<>();
        allPlaylists = getAllPlaylistsFromFile();
        for (int i = 0; i < allPlaylists.size(); i++) {
            Playlist testPlaylist = allPlaylists.get(i);
            int foundId = testPlaylist.getId();
            if (foundId == id)  {
            return testPlaylist;
            }
        }
        return null;
    }

    
   
    public Playlist stringArrayToPlaylist(String p) throws IOException {
        Playlist playlist;
//System.out.println("Test point: stringArrayToPlaylist" ); //
        String[] arrPlaylist = p.split(",");
        int id = Integer.parseInt(arrPlaylist[0]);
        String name = arrPlaylist[1];
System.out.println(""); //
System.out.println(""); //
System.out.println("Playlist id = " + id); //
System.out.println("Playlist name = " + name); //
System.out.println("Playlist song count = " + (arrPlaylist.length - 2)); //
        List<Song> allSongs = new ArrayList<>();
        allSongs = songDao.getAllSongsFromFile();
        List<Song> playlistSongs = new ArrayList<>();
        int i = 2;  // [0]=id, [1]=name. Songs start at [2]
System.out.print(" songId's "); //
        if (arrPlaylist.length >= 2) {
            while (i <= arrPlaylist.length) { 
                int songId = Integer.parseInt(arrPlaylist[i]);
                Song playlistSong = songDao.getSong(songId);
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
        int newPlaylistId = playlist.getId();
        String newPlaylistName = playlist.getName();
        String newSongListString = "";
        List<Song> playlistSongs = new ArrayList<Song>();
        playlistSongs = playlist.getSonglist();
        int playlistSongsSize = playlistSongs.size();
        if (playlistSongsSize > 0) {
            for (int i = 0; i < playlistSongsSize; i++) {
            Song songToAdd = playlistSongs.get(i);
            int songIdToAdd = songToAdd.getId();
            newSongListString = newSongListString + "," + songIdToAdd;
            }
        }
        String newPlaylistString = newPlaylistId + ","  + newPlaylistName + "," + newSongListString;
        return newPlaylistString;
    }
    
    
    
    
    public int getNewPlaylistId() throws IOException {
        List<Playlist> allPlaylists = getAllPlaylistsFromFile();
        int playlistSize = allPlaylists.size();
        Playlist lastPlaylist = allPlaylists.get(playlistSize);
        int newPlaylistId = lastPlaylist.getId() + 1;
        return newPlaylistId;
    }
        
    
    
    /*
    private void setNewId(int id) {
        temporaryId = id;
    } 
    */
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
                    Playlist playlist = stringArrayToPlaylist(line);
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
    public void writePlaylistListToFile(List<Playlist> allPlaylistsList) {
        File file = new File(SONG_SOURCE);
        boolean isExistingFile = false;
        
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
  /*
     private void writeSongListToFile(List<Song> songList) {
        File file = new File(SONG_SOURCE);
        boolean isExistingFile = false;
        
        for (int i = 0; i < songList.size(); i++) {
            Song currentSong = songList.get(i);
            String newSongString = songToString(currentSong) + "\n";
            try (FileWriter fw = new FileWriter (file,isExistingFile)) {
                fw.write(newSongString);
                isExistingFile = true;
            }
            catch (Exception e)  {
                System.out.println("Error writing file while deleting, dude");
            }
        }
    }
    */
     
}
