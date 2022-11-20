package lab6;

public class greatest{ 
    public greatest(int amogus){

    }
    public static int greatestCommonDivisor(int a, int b){
       if (a==0){
        return b;
       }else{
        return greatestCommonDivisor(b % a, a);
        }
    }

    public static void main(String[] args){
        int x = greatestCommonDivisor(18, 12);
        System.out.println(x);
    }
}