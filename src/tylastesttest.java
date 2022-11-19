import org.junit.AfterClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.HashMap;
import java.util.Random;

import static org.junit.Assert.*;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * JUnit Tests for the LinkedList portion of Project 3 CSCI 1933 Spring 2021.
 * Written by Noah Park on 03.03.2021.
 */
@FixMethodOrder(NAME_ASCENDING)
public class tylastesttest {

    private static final ScoringTestRule SCORING_TEST_RULE = new ScoringTestRule();

    @Rule
    public ScoringTestRule scoringTestRule = SCORING_TEST_RULE;

    @Rule
    public Timeout globalTimeOut = Timeout.seconds(15);


    @AfterClass
    public static void printScore() {
        System.out.println();
        System.out.println("sussy balls");
        System.out.println();
    }

    @Test 
    public void indexOfTest() {
        List<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(3);
        list.add(5);
        list.add(7);

        System.out.println(list);
        System.out.println("output: " + list.indexOf(6));
    }

    @Test
    public void addTest() {
        List<Integer> list = new LinkedList<>();
        for (int i=0;i<10;i++) {
            list.add(i);
            System.out.println("List: " + list);
        }
    }

    @Test
    public void pairswap() {
        List<Integer> list = new LinkedList<>();

        for (int i = 0; i < 5; i++) {
            
            list.add(i);
            System.out.println("pre pairswap List: " + list);
            list.pairSwap();
            System.out.println("post pairswap List: " + list);
        }































        // for (int i = 0; i<5;i++) {
        //     list.add(i);
        // }
        // System.out.println(list);
        // System.out.println(list.isSorted());
        // list.pairSwap();
        // System.out.println(list);
        // System.out.println(list.isSorted());
        // list.clear();

        // for (int i = 0; i < 25; i++) {
        //     list.add(i);
        //     boolean check = true;
        //     for (int k = 1; k < list.size(); k++) {
        //         System.out.println(k-1 + " - " + list.get(k-1));
        //         check &= list.get(k).compareTo(list.get(k - 1)) >= 0;
        //     }
        //     System.out.println(check);
        // }

    }



    @Test
    public void issortedpairswap() {
        List<String> list = new LinkedList<>();
        String[] test = new String[]{"One Piece", "Fullmetal Alchemist", "Attack On Titan", "Tokyo Ghoul", "Haikyuu!!", "Mob Psycho", "Hunter x Hunter",
                    "The Promised Neverland", "Solo Leveling", "The Breaker", "One Punch Man", "Dragon Ball Z", "JoJo's Bizarre Adventure", "Yuri!!! on ICE"};
        Random r = new Random();


        // // pairSwap
        // for (int i = 2; i < 5; i++) {
        //     list.add(test[r.nextInt(test.length)]);
        //     System.out.println("OG: " + list);
        //     list.pairSwap();
        //     System.out.println("Pair Swapped: " + list);
        //     boolean check = true;
        //     for (int k = 1; k < list.size(); k++) {
        //         System.out.println(k-1 + " - " + list.get(k-1));
        //         check &= list.get(k).compareTo(list.get(k - 1)) >= 0;
        //     }

        //     assertEquals(check, list.isSorted());
        // }
        // list.clear();
    }


    @Test
    public void mergeTest() {
        List<Integer> list = new LinkedList<>();
        List<Integer> list2 = new LinkedList<>();

        for (int i = 0; i <= 500; i++) {
            list.add(i);
        }

        for (int i = 500; i >= 0; i--) {
            list2.add(i);
        }

        System.out.println("List 1: " + list);
        System.out.println("List 2: " + list2);

        list.merge(list2);
        System.out.println("Merged List: " + list);
        //assertEquals(500, list.size());
    }
    
    @Test
    public void equaltoTest() {
        List<Integer> list = new LinkedList<>();

        for (int i = 0; i < 5; i++) {
            list.add(1);
        }
        for (int i = 0; i < 5; i++) {
            list.add(2);
        }
        for (int i = 0; i < 5; i++) {
            list.add(3);
        }

        System.out.println(list);
        System.out.println("isSorted: " + list.isSorted());

        list.equalTo(2);

        System.out.println(list);
        System.out.println("isSorted: " + list.isSorted());
    }

    // @Test
    // public void addTest() {
    //     List<Integer> list = new LinkedList<>();

    //     // add to empty list
    //     assertTrue(list.add(0));

    //     // cant add nulls 
    //     assertFalse(list.add(null));

    //     // add to last index with index
    //     assertFalse(list.add(2,2));

    //     // add elements
    //     for (int i = 0; i < 25; i++) {
    //         assertTrue(list.add(i));
    //     }

        
    // }
        
    
}
