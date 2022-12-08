package lab11;

import org.junit.AfterClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Random;

import static org.junit.Assert.*;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * JUnit Tests for the Exam 2 Review CSCI 1933 Spring 2021.
 * Code based off of tests written by Noah Park on 03.03.2021.
 */
@FixMethodOrder(NAME_ASCENDING)
public class Lab11Test {

    private static final ScoringTestRule SCORING_TEST_RULE = new ScoringTestRule();

    @Rule
    public ScoringTestRule scoringTestRule = SCORING_TEST_RULE;

    @Rule
    public Timeout globalTimeOut = Timeout.seconds(15);


    @AfterClass
    public static void printScore() {
        System.out.println();
        System.out.println("LinkedList: " + SCORING_TEST_RULE.getPoints() + " / " + SCORING_TEST_RULE.getTotal() + " points");
        System.out.println();
    }

    @Test
    @WorthPoints(points = 5)
    public void removeEveryTest() {
        // run 1000 tests
        for (int it = 0; it < 1000; it++) {
            LinkedList<String> list = new LinkedList<>();
            String[] test = new String[]{"One Piece", "Fullmetal Alchemist", "Attack On Titan", "Tokyo Ghoul", "Haikyuu!!", "Mob Psycho", "Hunter x Hunter",
                    "The Promised Neverland", "Solo Leveling", "The Breaker", "One Punch Man", "Dragon Ball Z", "JoJo's Bizarre Adventure", "Yuri!!! on ICE"};
            Random r = new Random();
            String[] arr = new String[255]; // comparison array
            int ptr = 0;

            assertFalse(list.add(null)); // can't add null object

            // add element
            for (int i = 0; i < 250; i++) {
                int idx = r.nextInt(test.length);
                assertTrue(list.add(test[idx]));
                arr[ptr++] = test[idx];
            }

            // removeEvery() test removing every random number of elements.
            int n = r.nextInt(100);
            int listIndex = 0;
            list.removeEvery(n);
            if (n == 0) {
                // Remove no elements when n=0.
                for (int i = 0; i < 250; i++) {
                    assertEquals(arr[i], list.get(i));
                }
            } else {
                for (int i = 0; i < 250; i++) {
                    if ((i + 1) % n == 0) {
                        continue;
                    }
                    assertEquals(arr[i], list.get(listIndex));
                    listIndex++;
                }
            }
        }
    }

    @Test
    @WorthPoints(points = 5)
    public void extractGroupsOfTest() {
        // run 1000 tests
        for (int it = 0; it < 1000; it++) {
            LinkedList<String> list = new LinkedList<>();
            String[] test = new String[]{"One Piece", "Fullmetal Alchemist", "Attack On Titan", "Tokyo Ghoul", "Haikyuu!!", "Mob Psycho", "Hunter x Hunter",
                    "The Promised Neverland", "Solo Leveling", "The Breaker", "One Punch Man", "Dragon Ball Z", "JoJo's Bizarre Adventure", "Yuri!!! on ICE"};
            Random r = new Random();
            String[] arr = new String[255]; // comparison array
            int ptr = 0;

            assertFalse(list.add(null)); // can't add null object

            // add element
            for (int i = 0; i < 250; i++) {
                int idx = r.nextInt(test.length);
                assertTrue(list.add(test[idx]));
                arr[ptr++] = test[idx];
            }

            int n = r.nextInt(100);
            LinkedList<LinkedList<String>> result = list.extractGroupsOf(n);


            for (int i = 0; i < result.size() - 1; i++) {
                LinkedList<String> innerList = result.get(i);
                // verify the length of the innerList is correct.
                assertEquals(n, innerList.size());


                // verify the contents of innerList
                for (int j = 0; j < n; j++) {
                    assertEquals(arr[j + (n * i)], innerList.get(j));
                }

            }
            // verify remainder list is correctly sized
            if(n != 0) {
                assertEquals(result.get(result.size() - 1).size(), 250 % n);
                for (int k = 0; k < 250 % n; k++) {
                    assertEquals(arr[k + 250 - (250 % n)], result.get(result.size() - 1).get(k));
                }
            }
        }
    }

    // @Test
    // @WorthPoints(points = 5)
    // public void hackStackTest() {
    //     // run 1000 tests
    //     for (int t = 0; t < 1000; t++) {
    //         HackStack<String> stack = new HackStack<>();
    //         String[] test = new String[]{"One Piece", "Fullmetal Alchemist", "Attack On Titan", "Tokyo Ghoul", "Haikyuu!!", "Mob Psycho", "Hunter x Hunter",
    //                 "The Promised Neverland", "Solo Leveling", "The Breaker", "One Punch Man", "Dragon Ball Z", "JoJo's Bizarre Adventure", "Yuri!!! on ICE"};
    //         Random r = new Random();
    //         String[] arr = new String[255]; // comparison array
    //         int ptr = 0;

    //         // push element
    //         for (int i = 0; i < 250; i++) {
    //             int idx = r.nextInt(test.length);
    //             stack.push(test[idx]);
    //             arr[ptr++] = test[idx];
    //         }


    //         ptr = 249;
    //         // test original pop() and peek()
    //         for (int i = 0; i < 100; i++) {
    //             // peek() test
    //             assertEquals(arr[ptr], stack.peek());
    //             // pop() test
    //             assertEquals(arr[ptr--], stack.pop());
    //         }

    //         // test pop(int n)
    //         int n = 0;
    //         while(ptr >= 0) {
    //             assertEquals(arr[ptr], stack.pop(n));
    //             ptr -= 2;
    //             n++;
    //         }
    //     }
    // }

    // @Test
    // @WorthPoints(points = 5)
    // public void isSymmetricTest() {

    //     int[][] a1 = new int[][]{{1}}; // true
    //     int[][] a2 = new int[][]{{1,2},{2,3}};  // true
    //     int[][] a3 = new int[][] {{1,2},{3,4}};  // false
    //     int[][] a4 = new int[][]{{1,2,3},{2,3,2}, {3,2,1}}; // true
    //     int[][] a5 = new int[][]{{1,2,3},{2,3,4},{5,6,7}}; // false
    //     int[][] a6 = new int[][]{{1,2,3,4},{2,3,4,1},{3,4,1,2},{4,1,2,3}}; // true
    //     int[][] a7 = new int[][]{{1,2,3,4},{5,6,7,8},{1,2,3,4},{5,6,7,8}}; // false

    //     ArrayTester a = new ArrayTester();

    //     assertTrue(a.isSymmetric(a1));
    //     assertTrue(a.isSymmetric(a2));
    //     assertFalse(a.isSymmetric(a3));
    //     assertTrue(a.isSymmetric(a4));
    //     assertFalse(a.isSymmetric(a5));
    //     assertTrue(a.isSymmetric(a6));
    //     assertFalse(a.isSymmetric(a7));
    // }
}