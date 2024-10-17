import java.util.Arrays;

public class Song {
	
private String title;
private String artist;
private int[] time;
private String info;
private int minutes;
private int seconds;
private static final String INFO_DELIMITER = "; ";
private static final String TIME_DELIMITER = ":";
   
  
   
public Song(String title, String artist, int[] time) {
this.title = title;
this.artist = artist;
this.time = Arrays.copyOf(time, time.length);
      
if (time.length == 1) {
this.seconds = time[0];
this.minutes = 0;
} else if (time.length == 2) {
this.minutes = time[1];
this.seconds = time[0];
} else {
this.minutes = time[1];
this.seconds = time[0];
}
}
   


public Song(String info) {
this.info = info;
String[] iParts = info.split(INFO_DELIMITER);
this.title = iParts[0];
this.artist = iParts[1];
String[] tParts = iParts[2].split(TIME_DELIMITER);
       
      
int[] time = new int[tParts.length];
for (int i = 0; i < tParts.length; i++) {
time[i] = Integer.parseInt(tParts[i]);
}
       
if (time.length == 1) {
this.time = new int[] {time[0]};
this.seconds = time[0];
this.minutes = 0;
} else if (time.length == 2) {
this.time = new int[] {time[1], time[0]};
this.minutes = time[1];
this.seconds = time[0];
} else {
this.time = new int[] {time[2], time[1], time[0]};
this.minutes = time[1];
this.seconds = time[0];
}
}
   


public String getTitle() {
return title;
}
   


public String getArtist() {
return artist;
}
   


public int[] getTime() {
return Arrays.copyOf(time, time.length);
}


   
@Override
public String toString() {
     
String timeString;
if (time.length == 1) {
  
timeString = String.format("%d", time[0]);
} else if (time.length == 2) {
           
timeString = String.format("%d:%02d", time[1], time[0]);
} else {
          
timeString = String.format("%d:%02d:%02d", time[2], time[1], time[0]);
}

return String.format("%s; %s; %s", title, artist, timeString);
}
   
public int getTimeInSeconds() {
return minutes * 60 + seconds;
}
}
