import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Mediaplayer{
    static class songnode{
        String songname;
        songnode prev;
        songnode next;

        songnode(String songname){
            this.songname = songname;
        }
    }

    static class playlist{
        songnode head;
        songnode tail;
        songnode currentsong;
        // Add a song to the playlist
        public void addsong(String songname){
            songnode newsong = new songnode(songname);

            if(head == null){
                head = newsong;
                tail = newsong;
                currentsong = newsong;
                return;
            }
            tail.next = newsong;
            newsong.prev = tail;
            tail = newsong;
        }
        //Display all songs
        public void displayplaylist(){
            if(head == null){
                System.out.println("Playlist is empty");
                return;
            }
            System.out.println("\nPlaylist:");

            songnode temp = head;
            while(temp != null){
                if(temp == currentsong){
                    System.out.println("-> " + temp.songname + " (current)");
                }else{
                    System.out.println("   " + temp.songname);
                }
                temp = temp.next;
            }
            System.out.println();
        }
        //Play the current song again (replay)
        public void repeatsong(){
            if(currentsong == null){
                System.out.println("No song available");
                return;
            }
            System.out.println("Replaying: " + currentsong.songname);
        }
        //move to the next 
        public void nextsong(){
            if (currentsong == null){
                System.out.println("No song available");
                return;
            }

            if(currentsong.next == null){
                System.out.println("You are already at the last song");
                return;
            }
            currentsong = currentsong.next;
            System.out.println("Now playing: " + currentsong.songname);
        }
        //move to the previous song
        public void previoussong(){
            if (currentsong == null){
                System.out.println("Playlist is empty");
                return;
            }

            if(currentsong.prev == null){
                System.out.println("You are already at the first song");
                return;
            }
            currentsong = currentsong.prev;
            System.out.println("Now playing: " + currentsong.songname);
        }
        // shuffle the playlist
        public void shuffleplaylist(){
            if(head == null){
                System.out.println("Playlist is empty");
                return;
            }

            ArrayList<String> songs = new ArrayList<>();

            songnode temp = head;
            while(temp != null){
                songs.add(temp.songname);
                temp = temp.next;
            }
            Collections.shuffle(songs);

            System.out.println("\nShuffled Playlist:");

            for(String song : songs){
                System.out.println(song);
            }

            System.out.println();
        }
        // show current playing song
        public void currentplayingsong(){
            if (currentsong == null){
                System.out.println("No song available");
                return;
            }
            System.out.println("Currently playing: " + currentsong.songname);
        }
    }
    public static void main(String[] args){
        playlist playlist = new playlist();
        playlist.addsong("shape of you");
        playlist.addsong("sanam teri kasam");
        playlist.addsong("dil ko karaar aaya");
        playlist.addsong("tum hi ho");
        Scanner sc = new Scanner(System.in);
        
        System.out.println("===============================");
        System.out.println("TERMINAL MUSIC PLAYER");
        System.out.println("===============================");

        System.out.println("\ncommands:");
        System.out.println("next");
        System.out.println("previous");
        System.out.println("repeat3");
        System.out.println("shuffle");
        System.out.println("current");
        System.out.println("exit");

        playlist.currentplayingsong();
        while(true){
            System.out.print("Enter command: ");
            String command = sc.nextLine().trim().toLowerCase();

            switch(command){
                case "next":
                    playlist.nextsong();
                    break;
                case "prev":
                    playlist.previoussong();
                    break;
                case "repeat":
                    playlist.repeatsong();
                    break;
                case "shuffle":
                    playlist.shuffleplaylist();
                    break;
                case "current":
                    playlist.currentplayingsong();
                    break;
                case "exit":
                    System.out.println("Exiting music player. Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
    }
}

