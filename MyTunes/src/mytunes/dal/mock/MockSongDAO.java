/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal.mock;

import java.util.ArrayList;
import java.util.List;
import mytunes.be.Song;

/**
 *
 * @author mega_
 */
public class MockSongDAO {

    List<Song> songs = new ArrayList();

    public MockSongDAO() {

        Song song = new Song(0, "JeppesSOng", "ChiliBAnd", "Rock", 320, "Belshazzar.mp3");
        Song song1 = new Song(0, "NadiasSong", "ChiliBAnds", "Pop", 0,"src/Dennis-Lloyd-Analizing.mp3");
        Song song2 = new Song(0, "AlansSong", "ChiliBand", "Country", 0,"src/Khul.mp3");
        songs.add(song);
        songs.add(song1);
        songs.add(song2);

    }

    public void addSong(Song song){
        songs.add(song);
    }
    
    public List<Song> getAllSongs() {

        return songs;
    }

    public void saveSong(Song song) {

        songs.add(song);

    }
    public void cancelSong (Song song) {

        songs.remove(song);

}}