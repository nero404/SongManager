package com.rob;

import com.rob.Services.SongService;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        int option;
        Scanner scanner = new Scanner(System.in);
        SongService songService = new SongService();


        do {menu();

            while (!scanner.hasNextInt()) {
                System.out.println("enter valid option;");
                scanner.next();
            }
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    songService.displaySongList();
                    break;

                case 2:
                    songService.addSong(scanner);
                    break;
                case 3:
                    songService.deleteSong(scanner);
                    break;
                case 4:
                    songService.addComment(scanner);
                    break;
                case 5:
                    songService.addLike(scanner);
                    break;
                case 6:
                    try {
                        songService.saveToXml();
                    } catch (JAXBException e) {
                        e.printStackTrace();
                    }
                    break;
                case 7:
                    try {
                        songService.readFromXml();
                    } catch (JAXBException e) {
                        e.printStackTrace();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }


                case 10:
                    System.out.println("Bye bye");
                    break;
                default:
                    System.out.println("wrong choice");
                    break;
            }


        } while (option != 10);
    }


    private static void menu() {
        System.out.println("Menu of song manager. Please enter the number");
        System.out.println("1: Display songs");
        System.out.println("2: Add Song");
        System.out.println("3: Delete Song");
        System.out.println("4: Add comment to song");
        System.out.println("5: Add like");
        System.out.println("6: Save to xml");
        System.out.println("7: Read  xml");

        System.out.println("10: Quit");


    }
}
