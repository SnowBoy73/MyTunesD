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
//import mytunes.bll.Duration;

/**
 *
 * @author   Louise, Nadia, Superior Martin and Alan

 */
public class SongDAO {
    
    
private static final String SONG_SOURCE = "data/song_list.txt";
int temporaryId = 20;



   
    public List<Song> getAllSongs() throws FileNotFoundException, IOException {
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

    
   
    public Song createSong(String title, String artist, String category, String duration) throws IOException {
        int newId = getNewId();
        Song newSong = new Song(newId, title, artist, category, duration);
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

    
    
   
    public void deleteSong(Song songToDelete) throws IOException {
        
        List<Song> allSongs = new ArrayList<>();
        allSongs = getAllSongs();
       
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

    
    
    
    public void updateSong(Song song) throws IOException {
    int songToUpdateId = song.getId();
    String updatedSongTitle = song.getTitle();
    String updatedSongArtist = song.getArtist();
    String updatedSongCategory = song.getCategory();
    String updatedSongDuration = song.getDuration();

    deleteSong(song);
    setNewId(songToUpdateId);
    createSong(updatedSongTitle,updatedSongArtist,updatedSongCategory, updatedSongDuration);
    
    }

    
   
    public Song getSong(int id) throws IOException {
        List<Song> allSongs = new ArrayList<>();
        allSongs = getAllSongs();
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
        String duration = arrSong[4];
        Song song = new Song(id, title, artist, category, duration);
        return song;
    }

    
    public String songToString(Song song) {
        int newSongId = song.getId();
        String newSongTitle = song.getTitle();
        String newSongArtist = song.getArtist();
        String newSongCatagory = song.getCategory();
        String newSongDuration = song.getDuration();

        String newSongString = newSongId + ","  + newSongTitle + "," + newSongArtist + "," + newSongCatagory + "," + newSongDuration;
        return newSongString;
    }
    
    
    
    
    private int getNewId() {
        temporaryId ++;
        return temporaryId;
    }
        
    
    
    
    private void setNewId(int id) {
        temporaryId = id;
    } 
    
        
  
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
    
     
}
