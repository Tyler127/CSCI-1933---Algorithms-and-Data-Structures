package lab13;

import java.util.Arrays;
public class BinaryTree {

    // TODO complete this method
    public static boolean isValid(int[] arr) {
      //1st check: if perfect
      int depth = findDepth(arr);
      int holder = (int) Math.pow(2.0, depth);
      if(holder != arr.length){return false;}
      
      System.out.println("RIGHT CHECK--------------");
      boolean rightCheck = helper(arr, 2000000000, arr[1], 3);
      System.out.println("LEFT CHECK-----------------------");
      boolean leftCheck = helper(arr, arr[1], -2000000000, 2);

      if((rightCheck == false) || (leftCheck == false)){
        System.out.println("vlaid change");
        return false;
      }

      return true;
    }

    public static boolean helper(int[] arr, int max, int min, int index){
      //base 1: index over length
      if(index >= arr.length){
        return true;
      }
      System.out.println("current test: " + (arr[index]));
      System.out.println("min: " + min);
      System.out.println("max: " + max);

      //base 2: greater / equal rule violated
      //check against min
      if(arr[index] < min){
        System.out.println("index val less than min -> set to false");
        return false;
      }
      //check agaisnt max
      if(arr[index] > max){
        System.out.println("index val greater than min -> set to false");
        return false;
      }
      System.out.println("\n");
      //go to left go to right
      return helper(arr, arr[index], min, index * 2) && helper(arr, max, arr[index], (index * 2) + 1);
    }

    public static int findDepth(int[] arr){
      int loopLength = arr.length;
      int loopTracker = 1;
      int counter = 0;
      while(loopTracker < loopLength){
        counter ++;
        loopTracker = loopTracker * 2;
      }
      return counter;
    }

    public static void main (String args[]) {
      // milestone 1
      int[] arr1 = new int[]{-1,7,4,10,3,6,8,15};
      int[] arr2 = new int[]{-1,20,12,32,5,18,25,38};
      int[] arr3 = new int[]{-1,11,3,33,2,8,10,44};
      int[] arr4 = new int[]{-1,55,44,77,33,48,54,95,22,34,45,57,53,70,85,98};

      System.out.println("-------------------------------------> arr1 valid: " + isValid(arr1));  // expected: true
      System.out.println("-------------------------------------> arr2 valid: " + isValid(arr2));  // expected: true
      System.out.println("-------------------------------------> arr3 valid: " + isValid(arr3));  // expected: false
      System.out.println("-------------------------------------> arr4 valid: " + isValid(arr4));  // expected: false
    }
}
