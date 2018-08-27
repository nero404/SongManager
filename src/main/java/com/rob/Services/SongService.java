package com.rob.Services;

import com.rob.Models.Comment;
import com.rob.Models.Song;
import com.rob.Models.Songs;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;


public class SongService {

    private static int songCounter;

    public static int getNewSongId() {
        return songCounter++;
    }

    public static void setSongCounter(int songCounter) {
        SongService.songCounter = songCounter;
    }

    private static List<Song> songList = new ArrayList<Song>();

    static {
        songList.add(new Song("Letnisko"));
        songList.add(new Song("Letnie niebo"));
    }

    public void addSong(Scanner scanner) {
        Song song = new Song();
        System.out.println("Enter title of song");
        scanner.nextLine();
        song.setTitle(scanner.nextLine());
        songList.add(song);
        sortListBySongs();
    }

   /* abstract class CompareSong implements Comparable<Song>{


        public int compareTo(Song song1, Song song2) {
            return song1.getTitle().compareTo(song2.getTitle());
        }
    }*/

    private void sortListBySongs() {
        songList.sort(new Comparator<Song>() {
            @Override
            public int compare(Song o1, Song o2) {
                return o2.getLikes() - o1.getLikes();
            }
        });
    }

    private void sortListByDate(){
        songList.sort(new Comparator<Song>() {
            @Override
            public int compare(Song song, Song t1) {
                return -1* song.getDate().compareTo(t1.getDate()) ;
            }
        });
    }

    public void deleteSong(Scanner scanner) {
        System.out.println("Enter id of song to delete");

        Song songTodelete=null;
        do {
            validateInt(scanner);
            try {
                songTodelete = songList.get(scanner.nextInt());
            }catch (Exception ex){

                System.out.println("enter proper id of song to delete");
            }


        }while (songTodelete==null);
        int indexOfSong= songList.indexOf(songTodelete);
        songList.remove(indexOfSong);
       /* for (Iterator<Song> iterator = songList.listIterator(); iterator.hasNext(); ) {
            Song song = iterator.next();
            if (song.getId() == id)
                iterator.remove();
        }*/

    }
    private int getIdOfLastSong(){
        int id = songList.size() -1;
        return id;
    }

    public void displaySongList() {
        sortListByDate();
        for (Song song : songList
                ) {
            System.out.println(song + "id of song: " + songList.indexOf(song));


        }
    }

    public void addComment(Scanner scanner) {
        System.out.println("Enter id of song to comment");
        validateInt(scanner);
        Song song = getSong(scanner.nextInt());
        System.out.println("Enter the comment");
        scanner.nextLine();
        song.addComment(new Comment(scanner.nextLine()));
    }

    public void addLike(Scanner scanner) {
        System.out.println("Enter id of song to like");
        Song song = null;
        do {
            validateInt(scanner);
            song = getSong(scanner.nextInt());
            if (song == null)
                System.out.println("Enter proper id");
        } while (song == null);

        song.addLike();

    }

    public void deleteComment() {

    }

    public Song getSong(int id) {
        for (Song song : songList
                ) {
            if (song.getId() == id)
                return song;

        }
        return null;

    }
    public Song getSongV2(int id){
       return songList.get(id);
    }

    public void saveToXml() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Songs.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        Songs songs = new Songs();
        songs.setSongList(songList);
        m.marshal(songs, System.out);
        m.marshal(songs, new File("songs.xml"));
    }

    public void readFromXml() throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(Songs.class);
        Unmarshaller um = context.createUnmarshaller();
        Songs songs = (Songs) um.unmarshal(new FileReader("songs.xml"));
        songList = songs.getSongList();
        songs.getSongList().forEach(song -> System.out.println(song));
        //displaySongList();
    }


    public static List<Song> getSongList() {
        return songList;
    }

    public static void setSongList(List<Song> songList) {
        SongService.songList = songList;
    }

    public void validateInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Enter valid option");
            scanner.next();
        }
    }
}



