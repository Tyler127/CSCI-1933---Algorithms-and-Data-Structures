package lab9;

//import java.util.Stack;

public class StackException extends Exception{
    private int size;
    StackException(int size){
        this.size = size;
    }

    int getSize(){return size;}
    
}
