import java.util.Scanner;//imports scanner class
import java.util.Arrays; // import the ArrayList class
import java.util.HashMap;//Imports "dictionaries"
public class Hist2{
    public int upperbound;
    public int lowerbound;
    public int[] array;

    public Hist2(int initLowerbound, int initUpperbound) {
        if(initUpperbound < initLowerbound){
            lowerbound = initUpperbound
            upperbound = initLowerbound
        }else{
            lowerbound = initLowerbound;
            upperbound = initUpperbound;
        }
        array = new int[(upperbound - lowerbound) + 1];//successfully makes array full of 0s at ther correct length
        //System.out.println(Arrays.toString(array));
        }
    public boolean add(int i){
        int targetIndex = i - lowerbound;
        // System.out.println(upperbound);
        // System.out.println(lowerbound);
        // System.out.println(targetIndex);
        // System.out.println(Arrays.toString(array));
        if(i <= upperbound && i >= lowerbound){
            //System.out.println(array[targetIndex]);
            //System.out.println("true");
            array[targetIndex] = array[targetIndex] + 1;
            //System.out.println(array[targetIndex]);
            //System.out.println(Arrays.toString(array));
            //System.out.println(Arrays.toString(array));
            return true;
        }else{
            //System.out.println("false");
            return false;
        }
    }
    public String toString(){
        int count = lowerbound;
        String bigString = "";
        while (count <= upperbound){
            //System.out.print(count + ": ");
            bigString += count + ": ";
            
            int count2 = array[count - lowerbound];
            while(count2 >0){
                count2 --;
                //System.out.print("*");
                bigString += "*";
            }
            //System.out.println();
            bigString += "\n";
            
            count++;
        }
        return bigString;
    }
    
    
    
    
    
    
    public static void main(String[] args){
        //Scanner bounds = new Scanner(System.in);
        //System.out.println("Enter Upperbound: ");
        //int uppernum = bounds.nextInt();
        //System.out.println("Enter Lowerbound: ");
        //int lowernum = bounds.nextInt();
        //Hist2 testHist = new Hist2(lowernum, uppernum);
        // System.out.println("Enter num to add: ");
        // int addnum = bounds.nextInt();
        // testHist.add(addnum);
        // testHist.toString();
        // Hist2 histo = new Hist2(0, 5);
        // histo.add(3);
        // histo.add(2);
        // histo.add(1);
        // histo.add(2);
        // histo.add(3);
        // histo.add(0);
        // histo.add(1);
        // histo.add(5);
        // histo.add(3);
        // System.out.println(histo);

    }
}