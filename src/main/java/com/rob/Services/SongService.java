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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


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
    }

    public void deleteSong(Scanner scanner) {
        System.out.println("Enter id of song to delete");

        validateInt(scanner);
        int id = scanner.nextInt();
        for (Iterator<Song> iterator = songList.listIterator(); iterator.hasNext(); ) {
            Song song = iterator.next();
            if (song.getId() == id)
                iterator.remove();
        }

    }

    public void displaySongList() {
        songList.forEach(song -> System.out.println(song));
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
            if(song==null)
                System.out.println("Enter proper id");
        }while(song==null);

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
        songList=songs.getSongList();
        displaySongList();
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



