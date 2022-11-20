package lab3;

import java.util.Scanner;
public class HistogramApp{

    public static void main(String[] args){
        Scanner inputs = new Scanner(System.in);

        System.out.println("Enter Upperbound: ");
        int uppernum = inputs.nextInt();
        System.out.println("Enter Lowerbound: ");
        int lowernum = inputs.nextInt();

        Histogram userHist = new Histogram(lowernum, uppernum);

        boolean stopCondition = false;
        while(stopCondition == false){
            String yeoers = inputs.nextLine();
            String add = "add";
            String print = "print";
            String quit = "quit";
            System.out.println("input: " +yeoers);
            if(yeoers.equals(quit)){
                stopCondition = true;
            }else if(yeoers.equals(add)){
                System.out.println("Enter Number: ");
                int x = inputs.nextInt();
                userHist.add(x);
            }else if (yeoers.equals(print)){
                System.out.println(userHist.toString());
            }
        }
    inputs.close();
    }
}