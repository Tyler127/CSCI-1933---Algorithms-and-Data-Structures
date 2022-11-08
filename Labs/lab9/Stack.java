public class Stack<T extends Comparable<T>> {
    private int size;
    private int inStack;
    private Node<T> head = new Node<T>(null, null);
    private Node<T> top;
    public Stack(){this.size = 5;}

    public Stack(int size){this.size = size;}

    public T pop()throws StackException{
        if(head.getNext() == null){
            throw new StackException(size);
        }
        Node<T> currTop = this.top;
        this.top = top.getNext();
        head.setNext(top.getNext());
        return currTop.getData();
    }

    public void push(T item)throws StackException{
        if(inStack + 1 > size){
            throw new StackException(size);
        }
        Node<T> newNode = new Node<T>(item);
        newNode.setNext(head.getNext());
        head.setNext(newNode);
        top = head.getNext();
        inStack += 1;
    }
}
