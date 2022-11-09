public class Stack<T extends Comparable<T>> {
    private int size;
    private int inStack;
    private Node<T> top;
    public Stack(){this.size = 5;}

    public Stack(int size){this.size = size;}

    public T getTopData(){return top.getData();}

    public T pop()throws StackException{
        if(top == null){
            throw new StackException(size);
        }
        T currData = top.getData();
        Node<T> saveNewTop = top.getNext();
        this.top.setNext(null);
        top = saveNewTop;
        
        return currData;
    }

    public void push(T item)throws StackException{
        if(inStack + 1 > size){
            throw new StackException(size);
        }
        Node<T> newNode = new Node<T>(item);
        newNode.setNext(top);
        top = newNode;
        inStack += 1;
    }
}
