import javax.print.attribute.IntegerSyntax;

public class LinkedList<T extends Comparable<T>> implements List<T> {
    private Node<T> head;
    private boolean isSorted = true;
    private int size = 0;


    public LinkedList() {
        head = new Node<T>(null);
        head.setNext(null);
        isSorted = true;
    }

    @Override
    public String toString() {
        String outputString = "toString() - output string: ";
        Node<T> currentNode = this.head.getNext();

        // Add each element to the string until the node points to null
        while (currentNode != null) {
            outputString += currentNode.getData();

            // As long as currentNode isn't the last Node adds a comma
            if (currentNode.getNext() != null) {
                outputString += ", ";
            }            

            currentNode = currentNode.getNext();
        }

        return outputString;
    }

    @Override
    public boolean add(T element) {
        Node<T> currentNode = this.head;
        Node<T> newNode = new Node<T>(element);
        T dataPrevious = null;

        // Case 1: element is null
        if (element == null) {
            return false;
        }

        // Case 2: head points to nothing
        if (currentNode.getNext() == null) {
            this.head.setNext(newNode); // sets head to the now second node
            this.isSorted = true;
        }

        // Case 3: head points to a Node. Must loop to find end of list.
        else {
            // loop until the last element is found (when the Node points to null)
            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }

            currentNode.setNext(newNode); // set next for last element to the new Node
            dataPrevious = currentNode.getData();
            //System.out.println("New Element: " + element + " dataPrevious: " + dataPrevious);

            // Checks if element is bigger than the current last item. if not, order is broken
            if (element.compareTo(dataPrevious) > 0 && this.isSorted == true) {
                this.isSorted = true;
            }
            else {
                this.isSorted = false;
            }
        }
            
        this.size += 1;
        return true;
    }
    
    @Override
    public boolean add(int index, T element) {
        Node<T> currentNode = this.head; // start on head so if index = 0 element can become first Node such that head points to newNode
        Node<T> newNode = new Node<T>(element);
        T dataPrevious = null;
        T dataNext = null;

        // Case 1: element is null
        if (element == null) {
            return false;
        }

        // Case 2: Adding first Node
        if (this.size == 0 && index == 0) {
            this.head.setNext(newNode);
            this.isSorted = true;
        }

        // Case 3: Index bigger than list size or below zero
        else if (index > this.size || index < 0) {
            System.out.println("fail");
            return false;
        }

        // Case 4: Valid parameters
        else { 
            // Sets currentNode to the Node at the index before newNode needs to be placed
            for (int i = 0; i < index; i++) { 
                currentNode = currentNode.getNext();
            }

            // Rearranges Nodes to insert newNode into the list
            newNode.setNext(currentNode.getNext());
            currentNode.setNext(newNode);

            // Gets the data of the nodes surrounding newNode
            dataPrevious = currentNode.getData(); 
            if (newNode.getNext() != null) {
                dataNext = newNode.getNext().getData();
            }
            
            // Compares elements to determine if order is preserved
            // Case 4.1: newNode not last item of list. compares previous and next data.
            if (dataNext != null) {
                if (element.compareTo(dataNext) < 0 && element.compareTo(dataPrevious) > 0 && this.isSorted == true) {
                    System.out.println("order preserved");
                    this.isSorted = true;
                }
            }
            // Case 4.2: newNode is now last item of list. compares only previous data.
            else if (element.compareTo(dataPrevious) > 0 && this.isSorted == true) {
                System.out.println("order preserved");
                this.isSorted = true;
            }
            // Case 4.3: the list becomes unsortec
            else {
                this.isSorted = false;
            }
        }

        this.size += 1;
        return true;
    }
     
    @Override
    public void clear() {
        // Make head point to nothing (hope javer cleans it up?)
        this.head.setNext(null);
        this.isSorted = true;
        this.size = 0;
    }
    
    @Override
    public void equalTo(T element) {
        // TODO: optimize equalTo with isSorted?
        if (element != null) { // Only runs if element is not null
            Node<T> trailer = this.head;
            Node<T> pointer = trailer.getNext();

            // Case 1: list is size 1
            if (this.size == 1 && trailer.getData() != element) {
                this.clear();
            }
            
            // Case 2: list size > 1. loop through list
            while (pointer != null) {
                if (pointer.getData() != element) {
                    trailer.setNext(pointer.getNext()); // trailer points now to Node after the invalid Node
                    pointer = pointer.getNext(); // pointer now is the Node after the invalid Node
                    this.size -= 1;
                }
                else { // Increment over the linked list
                    trailer = pointer;
                    pointer = trailer.getNext();
                }
            }
            this.isSorted = false;
        }
    }

    @Override
    public T get(int index) {
        Node<T> currentNode = this.head.getNext();

        // Case 1: the index is out of bounds
        if (index > this.size - 1) {
            return null;
        }

        // Case 2: the index is whithin bounds
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }
        return currentNode.getData();
    }

    @Override
    public int indexOf(T element) {
       // TODO: optimize indexOf with isSorted?
       Node<T> currentNode = this.head.getNext();
       int index = 0;
    
       // Case 0: element is null; exit right away
       if (element == null) {
            return -1;
       }
       
       // Case 1: loop until a Node is found with value matching element
       while (currentNode != null) {
            if (element == currentNode.getData()) {
                return index;
            }
            currentNode = currentNode.getNext();
            index += 1;
       }

       // Case 2: no such element is found
       return -1;
    }

    @Override
    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isSorted() {
        return this.isSorted;
    }

    @Override
    public void merge(List<T> otherList) {
        // TODO merge
        
    }

    @Override
    public void pairSwap() {
        // TODO pairswap
        
    }

    // TODO: check remove if still sorted?? kekw why tf would sorted even change if it was sorted in the first place
    @Override
    public T remove(int index) {
        Node<T> trailer = this.head;
        Node<T> pointer = trailer.getNext();
        T data;


        // Case 1: invalid index
        if (index > this.size - 1) {
            return null;
        }

        // Case 2: loops over list until pointer is on the parameter index
        for (int i = 0; i < index; i++) {
            trailer = trailer.getNext();
            pointer = trailer.getNext();
        }
        
        data = pointer.getData(); // pointer is on the Node that will be removed

        // Reassign Nodes
        trailer.setNext(pointer.getNext());
        pointer = pointer.getNext();

        this.size -= 1;
        return data;
    }

    @Override
    public void reverse() {
        // TODO reverse
        
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void sort() {
        // TODO sort
        
    }

    public static void main(String[] args) {
        // LinkedList<Integer> list = new LinkedList<Integer>();
        // //System.out.println(list);

        // list.add(3);
        
        // list.add(3);
        // list.add(4);
        // list.add(5);
        // list.add(36);
        // // System.out.println(list);
        // // System.out.println(list.size());

        // list.clear();
        // System.out.println(list);
        // System.out.println(list.size());


        LinkedList<String> list = new LinkedList<String>();
        //System.out.println(list);

        
        list.add("0");
        list.add("1");
        //list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        
        // System.out.println(list.remove(0));

        System.out.println(list);
       list.add(6, "7");
        System.out.println(list);

    //    System.out.println(list);
    //    System.out.println(list.indexOf("3"));
    //    System.out.println(list.indexOf("conna moment"));
    //    System.out.println(list.indexOf("0"));
    //    System.out.println(list.indexOf("dddd"));
    //    System.out.println(list.indexOf(null));
    //    list.add(2, "susgayballs");
     
       
        // list.equalTo("conna moment");
        // System.out.println(list);
        // System.out.println(list.get(6));
        // System.out.println(list.get(7));
        // System.out.println(list.get(0));
        // System.out.println(list.get(2));

        System.out.println("Size: " + list.size());
        System.out.println("isSorted: " + list.isSorted());
      
    }
    
}
