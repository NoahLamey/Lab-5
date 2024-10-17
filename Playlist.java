import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringJoiner;

public class Playlist {
private ArrayList<Song> songs;
private String filename;

    
    
public Playlist() {
songs = new ArrayList<>();
}

public Playlist(String filename) {
this();
this.filename = filename;
addSongs(filename);
}
    
    
public int getNumSongs() {
return songs.size();
}

    
public Song getSong(int index) {
if (index < 0 || index >= getNumSongs()) {
return null;
}
return songs.get(index);
}
     
    
    
public Song[] getSongs() {
return songs.toArray(new Song[0]); 
}
    
public boolean addSong(Song song) {
return addSong(getNumSongs(), song);
}
   
    
public boolean addSong(int index, Song song) {
if (song == null || index < 0 || index > getNumSongs()) {
return false; 
}
songs.add(index, song);
return true; 
}

    
    
public int addSongs(Playlist playlist) {
if (playlist == null) {
return 0;
}
int count = 0;
for (Song song : playlist.getSongs()) {
addSong(song);
count++;
}
return count; 
}

public void addSongs(String filename) {
try (BufferedReader reader1 = new BufferedReader(new FileReader(filename))) {
String line;
while ((line = reader1.readLine()) != null) {
addSong(new Song(line)); 
}
} catch(IOException e) { 
throw new RuntimeException("Error while reading file: " + e.getMessage());
}
}
    
public Song removeSong() {
return removeSong(getNumSongs() - 1);
}
    
    
    
public Song removeSong(int index) {
if (index < 0 || index >= getNumSongs()){
return null; 
}
return songs.remove(index); 
}
   
public String toString() {
StringJoiner sj = new StringJoiner(System.lineSeparator());
for(Song song : songs) {
sj.add(song.toString());
        	
}
return sj.toString();
}
  
    
public void saveSongs(String filename) {
try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filename))) {
fileWriter.write(toString());
}catch (IOException e) {
throw new RuntimeException("Error while writing the file: " + e.getMessage());
}
}

   
    
    
public int[] getTotalTime() {
int time = 0;
for(Song song : songs) {
int[] totalSongTime = song.getTime();
int allSeconds = 0;
if(totalSongTime.length == 1) {
allSeconds = totalSongTime[0];
}else if(totalSongTime.length == 2) {
allSeconds = totalSongTime[0] + totalSongTime[1] * 60;
}else if(totalSongTime.length == 3){
allSeconds = totalSongTime[0] + totalSongTime[1] * 60 + totalSongTime[2] * 3600;
}
time += allSeconds;
}
int hours = time / 3600;
int minutes = (time % 3600) / 60;
int seconds = time % 60;
if(hours > 0) {
return new int[] {seconds, minutes, hours};
}else if(minutes > 0) {
return new int[] {seconds, minutes};
}else {
return new int[] {seconds};
}
}
}
