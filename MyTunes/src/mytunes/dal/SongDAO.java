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

import mytunes.be.Song;


/**
 * @author   Louise, Nadia, Superior Martin and Alan
 */


public class SongDAO {
    
    
private static final String SONG_SOURCE = "data/song_list.txt";
boolean isNewSong = true;
int oldSongId;



    public List<Song> getAllSongsFromDB() throws FileNotFoundException, IOException {
        
         // YET TO WRITE
        return null;
    }
    
    
   
    public Song createSong(String title, String artist, String category, int duration) throws IOException {
System.out.println("create song "); //
System.out.println("isNewSong = " + isNewSong); //
        int songId;
        if(isNewSong) {
             songId = getNewSongId();
        } else {
             songId = oldSongId;
        }
        Song newlyCreatedSong = new Song(songId, title, artist, category, duration);
        isNewSong = true;
        System.out.println("new song "+ songId + title + artist + category + duration);
System.out.println("Song Created"); //
        return newlyCreatedSong;
    }
        
    
    
    public List<Song> addSongToSongList(Song songToBeAdded, List<Song> songList) {
        //Add newSong to songList
        songList.add(songToBeAdded);
        return songList;
        }
        
        
   
    public void deleteSong(Song songToDelete, List<Song> songlist) throws IOException {
        
        List<Song> allSongs = new ArrayList<>();
        allSongs = getAllSongsFromFile();
        if (true) {  // to be replaced with SongExists method (NOT NECESSARY)
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

    
    
    public void updateSong(Song songToUpdate) throws IOException {
        List<Song> allSongs = new ArrayList<>();
        allSongs = getAllSongsFromFile();
        List<Song> updatedAllSongs = new ArrayList<>();

        int songToUpdateId = songToUpdate.getId();
        String updatedSongTitle = songToUpdate.getTitle();
        String updatedSongArtist = songToUpdate.getArtist();
        String updatedSongCategory = songToUpdate.getCategory();
        int updatedSongDuration = songToUpdate.getDuration();
        // new bit
        
System.out.println("updatedAllSongs:"); //
        for (int i = 0; i < allSongs.size(); i++) {
            Song songBeingChecked = allSongs.get(i);
            int songBeingCheckedId = songBeingChecked.getId();
            if(songBeingCheckedId == songToUpdateId) {
                updatedAllSongs.add(songToUpdate);
            } else {
                updatedAllSongs.add(songBeingChecked);
           }
System.out.println(songBeingChecked.getId()); //
        }
        
        writeSongListToFile(updatedAllSongs);
        /*   old bit
        deleteSong(song, songList);
        isNewSong = false;
        oldSongId = songToUpdateId;
        Song updatedSong = createSong(updatedSongTitle,updatedSongArtist,updatedSongCategory, updatedSongDuration);
    */
}

    
   
    public Song getSong(int id) throws IOException {
        List<Song> allSongs = new ArrayList<>();
        allSongs = getAllSongsFromFile();
        for (int i = 0; i < allSongs.size(); i++) {
            Song testSong = allSongs.get(i);
            int foundId = testSong.getId();
            if (foundId == id)  {
            
            return testSong;
            }
        }
        return null;
    }

    
   
    public Song stringArrayToSong(String t) {
        String[] arrSong = t.split(",");

        int id = Integer.parseInt(arrSong[0]);
        String title = arrSong[1];
        String artist = arrSong[2];
        String category = arrSong[3];
        int duration = Integer.parseInt(arrSong[4]);
        Song song = new Song(id, title, artist, category, duration);
        return song;
    }

    
    
    public String songToString(Song song) {
        int newSongId = song.getId();
        String newSongTitle = song.getTitle();
        String newSongArtist = song.getArtist();
        String newSongCatagory = song.getCategory();
        int newSongDuration = song.getDuration();

        String newSongString = newSongId + ","  + newSongTitle + "," + newSongArtist + "," + newSongCatagory + "," + newSongDuration;
        return newSongString;
    }
    
    
    
    public int getNewSongId() throws IOException {

        List<Song> allSongs = getAllSongsFromFile();
        int songListSize = allSongs.size();
 System.out.println("allSongs size = " + songListSize); //
        Song lastSong = allSongs.get(songListSize - 1);
        int newSongId = lastSong.getId() + 1;
        return newSongId;
    }
        
    
    
    public int getOldSongId() throws IOException {
    return oldSongId;
    }
    
    
    
    public List<Song> getAllSongsFromFile() throws FileNotFoundException, IOException {
        List<Song> allSongs = new ArrayList<>();
        File file = new File(SONG_SOURCE);
        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                try
                {
                    Song song = stringArrayToSong(line);
                    allSongs.add(song);

                } catch (Exception ex)
                {
                    //Do nothing we simply do not accept malformed lines of data.
                    //In a perfect world you should at least log the incident.
                }
            }
        }
        return allSongs;
    }

    
  
    public void writeSongListToFile(List<Song> songList) {
        File file = new File(SONG_SOURCE);
        boolean isExistingFile = false;
        
        for (int i = 0; i < songList.size(); i++) {
            Song currentSong = songList.get(i);
            String newSongString;
            if(i == 0) {
                 newSongString = songToString(currentSong);
            } else {
                 newSongString = "\n" + songToString(currentSong);
            }
            try (FileWriter fw = new FileWriter (file,isExistingFile)) {
                fw.write(newSongString);
                isExistingFile = true;
            }
            catch (Exception e)  {
                System.out.println("Error writing file while deleting, dude");
            }
        }
    }
    
     
}
