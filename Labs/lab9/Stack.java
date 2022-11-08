public class Stack<T extends Comparable<T>> {
    private int size;
    private Node head = new Node();
    public Stack(){
        this.size = 5;
    }
    public Stack(int size){
        this.size = size;
    }

    public T pop()throws StackException{

    }
}
