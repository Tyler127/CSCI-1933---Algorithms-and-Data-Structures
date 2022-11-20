
import org.junit.AfterClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Random;

import static org.junit.Assert.*;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * JUnit Tests for the LinkedList portion of Project 3 CSCI 1933 Spring 2021.
 * Written by Noah Park on 03.03.2021.
 */
@FixMethodOrder(NAME_ASCENDING)
public class tylastesttest {

    @Rule
    public Timeout globalTimeOut = Timeout.seconds(15);


    @AfterClass
    public static void printScore() {
        System.out.println();
        System.out.println("sussy balls");
        System.out.println();
    }

    @Test
    public void constructorTest() {
        MyMaze maze = new MyMaze(2, 5, 1, 3);

    }

    @Test
    public void printTest() {
        MyMaze maze = new MyMaze(2, 5, 1, 3);
        maze.printMaze();

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
