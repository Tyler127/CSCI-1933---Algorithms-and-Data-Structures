import java.util.Scanner;
public class Fib {

    public static int fibonacciRecursive(int n){
        if (n <= 1) {
            return n;
        }
        else {
            return fibonacciRecursive(n-2) + fibonacciRecursive(n - 1);
        }
    }
    

    public static int fibonacciIterative(int n){
        int partial = 0;
        int mainnum = 1;
        int count = 0;
        while(count < n - 1){
            int savednum =  mainnum;
            mainnum = mainnum + partial;
            partial = savednum;
            count ++;
        }
        return mainnum;
    }

    public static void main(String[] args) {
        System.out.println("3: " + fibonacciRecursive(3));
        System.out.println("5: " + fibonacciRecursive(5));
        System.out.println("8: " + fibonacciIterative(8));
        System.out.println("10: " + fibonacciIterative(10));
        
        Scanner fibScan = new Scanner(System.in);
        System.out.println("Enter what term of Fibonacci you want");
        int n = fibScan.nextInt();
        System.out.println("Recursive: " + fibonacciRecursive(n));
        System.out.println("Iterative: " + fibonacciIterative(n));
        fibScan.close();
    
    }
}