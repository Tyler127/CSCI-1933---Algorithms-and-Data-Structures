import java.util.Scanner;
public class Max {
    public static int recursiveMaxDigit(int num){
        // get last number w modulo
        // get averything but last modulo with /
        int curr_max = 0;
        return recursiveHelper(num,curr_max);
    }
    public static int recursiveHelper(int num, int curr_max){
        int numlength = String.valueOf(num).length();
        int curr_num = num % 10;
        if (curr_num > curr_max){
                curr_max = curr_num;
            }
        if (numlength == 1){
            return curr_max;}
        num = num / 10;
        return recursiveHelper(num, curr_max);

    }


    public static int iterativeMaxDigit(int num) {
        int count = 0;
        int numlength = String.valueOf(num).length();
        int curr_max = 0;
        while(count < numlength){
            int curr_num = num % 10;
            if (curr_num > curr_max){
                curr_max = curr_num;
            }
            num = num / 10;
            count ++;
        }
        return curr_max;
    }


    public static void main(String[] args) {
        System.out.println(recursiveMaxDigit(578));
        System.out.println(recursiveMaxDigit(10));
        System.out.println(iterativeMaxDigit(9999));
        System.out.println(iterativeMaxDigit(13442));

        Scanner maxScan = new Scanner(System.in);
        System.out.println("Enter what Max you want");
        int n = maxScan.nextInt();
        System.out.println("Recursive: " + recursiveMaxDigit(n));
        System.out.println("Iterative: " + iterativeMaxDigit(n));
        maxScan.close();
    }
}