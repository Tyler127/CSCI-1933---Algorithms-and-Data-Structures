import org.junit.*;
import org.junit.rules.Timeout;

//import MyMaze;

import java.io.File;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import static org.junit.Assert.*;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;


@FixMethodOrder(NAME_ASCENDING)
public class tests {

    //@Rule
    //public Timeout globalTimeOut = Timeout.seconds(25);


    @AfterClass
    public static void printScore() {
        System.out.println();
        System.out.println("sussy balls");
        System.out.println();
    }

    @Test
    public void hash1Test() {
        HashTable<String> hashTable = new HashTable<>(20);

        File file = new File("src/test.txt");
        try {
            Scanner scanna = new Scanner(file);
            while (scanna.hasNextLine()) {
                String line = scanna.nextLine();
                String[] lineArray = line.split(" ");

                for (String string : lineArray) {
                    System.out.println(string);
                    hashTable.hash1(string);
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    public void noDuplicatesTest() {
        System.out.println("---------NO DUPLICATES TEST----------");

        HashTable<String> hashTable = new HashTable<>(4);
        hashTable.addWordsFromFile("src/duplicateTest.txt");

        int counter = 0;
        NGen<String> pointer = hashTable.getHashTable()[0];
        while (pointer.getNext() != null) {
            counter++;
            System.out.println(counter + " - " + pointer.getData());
            pointer = pointer.getNext();
        }

        assertEquals(counter, 5);
    }

    @Test
    public void displayTest() {
        System.out.println("----------DISPLAY TEST----------");
        HashTable<String> hashTable = new HashTable<>(15);
        hashTable.addWordsFromFile("src/test.txt");
        hashTable.display();
    }

    @Test
    public void keywordsGeneralTest() {
        System.out.println("----------KEYWORDS TEST general----------");
        HashTable<String> hashTable = new HashTable<>(150);
        hashTable.type = "GENERAL";
        hashTable.addWordsFromFile("src/keywords.txt");
        hashTable.display();
    }

    @Test
    public void keywordsSpecificTest() {
        System.out.println("----------KEYWORDS TEST specific----------");
        HashTable<String> hashTable = new HashTable<>(150);
        hashTable.type = "SPECIFIC";
        hashTable.addWordsFromFile("src/keywords.txt");
        hashTable.display();
    }

    @Test
    public void gettysburgGeneralTest() {
        System.out.println("----------GETTYSBURG TEST general----------");
        HashTable<String> hashTable = new HashTable<>(150);
        hashTable.type = "GENERAL";
        hashTable.addWordsFromFile("src/gettysburg.txt");
        hashTable.display();
    }

    @Test
    public void gettysburgSpecificTest() {
        System.out.println("----------GETTYSBURG TEST specific----------");
        HashTable<String> hashTable = new HashTable<>(150);
        hashTable.type = "SPECIFIC";
        hashTable.addWordsFromFile("src/gettysburg.txt");
        hashTable.display();
    }

    @Test
    public void lettersTest() {
        System.out.println("----------LETTERS TEST----------");
        HashTable<String> hashTable = new HashTable<>(52);
        hashTable.addWordsFromFile("src/letters.txt");
        hashTable.display();
    }

























    @Test
    public void randomTest() {
        Random r = new Random();

        for (int i = 0; i<5; i++) {
            int randInt = r.nextInt(0, 5);
            //System.out.println(randInt);
        }
    }
}
