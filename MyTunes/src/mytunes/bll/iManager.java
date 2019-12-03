/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.bll;

/**
 *
 * @author mac
 */
import java.io.IOException;
import java.util.List;
import mytunes.be.Playlist;
import mytunes.be.Song;

public interface iManager {
     Song createSong(String title, String artist, String category, int duration,String path); // throws IOException {
    List<Song> addSongToSongList(Song songToBeAdded, List<Song> songList);
    void deleteSong(Song songToDelete, List<Song> songlist); // throws IOException {
    void updateSong(Song songToUpdate); // throws IOException {
    Song getSong(int id); // throws IOException {
    Song stringToSong(String t);
    String songToString(Song song);
    int getNewSongId(); // throws IOException {
    int getOldSongId(); // throws IOException {
    List<Song> getAllSongsFromFile(); // throws FileNotFoundException, IOException {
    List<Song> getAllSongsFromDB(); // throws FileNotFoundException, IOException {
    void writeSongListToFile(List<Song> songList);


    
    Playlist createPlaylist(String name, List<Song> songsInNewPlaylist);
    Playlist getPlaylist(int id);
    List<Playlist> addPlaylistToAllPlaylists(Playlist playlistToBeAdded, List<Playlist> playlistList);
    void deletePlaylist(Playlist playlistToDelete); // throws IOException {
    void updatePlaylist(Playlist playlistToUpdate); // throws IOException
    Playlist stringToPlaylist(String p); // throws IOException {
    String playlistToString(Playlist playlist);
    int getNewPlaylistId(); // throws IOException {
    List<Playlist> getAllPlaylistsFromFile(); // throws FileNotFoundException, IOException {
    void writeAllPlaylistsToFile(List<Playlist> allPlaylistsList);

    
}
