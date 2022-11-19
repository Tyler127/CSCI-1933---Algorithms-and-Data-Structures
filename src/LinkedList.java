// Written by LARS6653 and KANTE060

public class LinkedList<T extends Comparable<T>> implements List<T> {
    private Node<T> head;
    private Node<T> tail;
    private boolean isSorted = true;
    private int size = 0;

    public LinkedList() {
        head = new Node<T>(null);
        head.setNext(null);
        isSorted = true;
    }

    @Override
    public String toString() {
        String outputString = "";
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
        Node<T> newNode = new Node<T>(element);

        // Case 1: element is null
        if (element == null) return false;
        

        // Case 2: list has no nodes
        if (this.head.getNext() == null) {
            this.head.setNext(newNode);
            this.tail = newNode;
        }

        // Case 3: adds newNode to end of list
        else {
            this.tail.setNext(newNode);
            this.tail = this.tail.getNext();
        }

        this.size += 1;
        updateSorted();
        return true;
    }

    @Override
    public boolean add(int index, T element) {
        Node<T> currentNode = this.head; // start on head so if index = 0 element can become first Node such that head points to newNode
        Node<T> newNode = new Node<T>(element);

        // Case 1: element is null
        if (element == null) return false;

        // Case 2: Index bigger than list size or below zero
        if (index >= this.size || index < 0) return false;

        // Case 3: Adding first Node
        if (this.size == 0 && index == 0) {
            this.head.setNext(newNode);
            this.isSorted = true;
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
        }
        this.size += 1;
        updateSorted();
        return true;
    }
     
    @Override
    public void clear() {
        this.head.setNext(null);
        this.isSorted = true;
        this.size = 0;
    }
    
    @Override
    public void equalTo(T element) {
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

                // Optimizes the method using isSorted:
                // if sorted and pointer -> element and pointer.next -> not element 
                // then chop off rest of nodes because all elements are accounted for
                if (this.isSorted) {
                    if (pointer.getData() == element && pointer.getNext().getData() != element) {
                        pointer.setNext(null);
                        return;
                    }
                }
            }
            this.isSorted = true;
        }
    }

    @Override
    public T get(int index) {
        Node<T> currentNode = this.head.getNext();

        // Case 1: the index is out of bounds
        if (index > this.size - 1 || index < 0) return null;

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
       if (element == null) return -1;
       
       // Case 1: loop until a Node is found with value matching element
       while (currentNode != null) {
        
            // Optimizes the method by ending loop early if the next data is > than element.
            if (this.isSorted == true && element.compareTo(currentNode.getData()) < 0) {
                return -1;
            }

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
        // Case 1: otherList is null
        if (otherList == null) return;

        LinkedList<T> other = (LinkedList<T>) otherList;
        this.sort();
        other.sort();

        Node<T> newHead = new Node<T>(null);
        Node<T> newTail = new Node<T>(null);;
        Node<T> nodeToAppend;
        int newSize = 0;
        
        // Case 2: Merges the lists
        // loops until one list is empty
        while (this.head.getNext() != null && other.head.getNext() != null) {
            // If this's first node's data is smaller than other's first node's data -> append that node.
            if (this.head.getNext().getData().compareTo(other.head.getNext().getData()) <= 0) {
                nodeToAppend = this.head.getNext();
                this.head.setNext(this.head.getNext().getNext());
            } 
            // Other's first node must be smaller -> append that node.
            else {
                nodeToAppend = other.head.getNext();
                other.head.setNext(other.head.getNext().getNext());
            }

            // list doesn't exist -> create new head and set tail to new node
            if (newHead.getNext() == null) {
                newHead.setNext(nodeToAppend);
                newTail = newHead.getNext();
                newSize++;
            }
            // list exists -> set and move tail
            else {
                newTail.setNext(nodeToAppend);
                newTail = newTail.getNext();
                newSize++;
            }
        }

        // adds any remaining nodes from this onto the new list
        while (this.head.getNext() != null) {
            nodeToAppend = this.head.getNext();
            this.head.setNext(this.head.getNext().getNext());
            newTail.setNext(nodeToAppend);
            newTail = newTail.getNext();
            newSize++;
        }

        // adds any remaining nodes from other onto the new list
        while (other.head.getNext() != null) {
            nodeToAppend = other.head.getNext();
            other.head.setNext(other.head.getNext().getNext());
            newTail.setNext(nodeToAppend);
            newTail = newTail.getNext();
            newSize++;
        }

        this.head = newHead;
        this.size = newSize;
        updateSorted();
    }

    @Override
    public void pairSwap() {
        if (this.size != 0) {
            Node<T> trailer = this.head.getNext();
            Node<T> pointer = trailer.getNext();
            int swaps = (int) Math.floor(this.size / 2);

            // Loop that swaps the nodes and leaves the one at the end if length is odd
            for (int i = 0; i < swaps; i++) {
                T tempData = pointer.getData();
                pointer.setData(trailer.getData());
                trailer.setData(tempData);

                // Prevents reassignments if the swap was the last one
                // swaps - (i+1) = the number of swaps that have occured. if zero, all swaps have been done
                if (swaps - (i+1) != 0) { 
                    pointer = pointer.getNext().getNext();
                    trailer = trailer.getNext().getNext();
                }
            }
            updateSorted();
        }
    }

    @Override
    public T remove(int index) {
        Node<T> trailer = this.head;
        Node<T> pointer = trailer.getNext();
        T data;

        // Case 1: invalid index
        if (index > this.size - 1 || index < 0) {
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

        updateSorted();
        this.size -= 1;
        return data;
    }

    @Override
    public void reverse() {
        // Case 1: list is empty or length of 1
        if (this.size == 0 || this.size == 1) return;

        // Case 2: Reverses the list
        Node<T> trailer = this.head.getNext();
        Node<T> pointer = trailer.getNext();

        for (int i = 1; i < this.size; i++) { // i starts on 1 because size is length not index
            trailer.setNext(pointer.getNext());
            pointer.setNext(this.head.getNext());
            this.head.setNext(pointer);

            pointer = trailer.getNext();
        }
        updateSorted();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void sort() { 
        // Increases efficiency is the list is sorted by not resorting it if already sorted.
        if (this.isSorted != true) {
            Node<T> pointer = this.head; 
            Node<T> testNode = null;
            T temp;
    
            // Loops throught the list starting at pointer
            for (int i = 0; i < this.size; i++) {
                testNode = pointer.getNext();

                // Loops through the list again starting at testNode
                while (testNode != null) {
                    // If pointer's data is bigger than testNodes's swap the data
                    if (pointer.getData() != null && testNode.getData() != null) {
                        if (pointer.getData().compareTo(testNode.getData()) > 0) { 
                            //System.out.println("    Swap: " + pointer.getData() + " and " + testNode.getData());
                            temp = pointer.getData();
                            pointer.setData(testNode.getData());
                            testNode.setData(temp);
                        }
                    }
                    testNode = testNode.getNext();
                }
                pointer = pointer.getNext();
            }
            this.isSorted = true;
        }
    }

    private void updateSorted() {
        if (this.size == 1) { // If the size is one, the list is sorted
            this.isSorted = true;
        }
        else {
            // Resets trailer and pointer to iterate over the list
            Node<T> trailer = this.head.getNext();
            Node<T> pointer = trailer.getNext();

            this.isSorted = true; // Set to true by default then the iteration will change it based on if the list is sorted or not

            while (pointer != null) {
                //System.out.println("    Trailer: " + trailer.getData());
                //System.out.println("    pointer: " + pointer.getData());
                //System.out.println("        compare: " + trailer.getData().compareTo(pointer.getData()));

                // Checks if for any pair of trailer and pointer if trailer is > than pointer in which case the list would remain unsorted
                if (trailer.getData().compareTo(pointer.getData()) > 0) {
                    //sortedTester = false;
                    this.isSorted = false;
                }
                trailer = trailer.getNext();
                pointer = pointer.getNext();
            }
        }
    }
}
