

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
        }
        //System.out.println("    added element: " + element);
        //System.out.println("        add presortcheck update: " + this.isSorted);
        
        this.size += 1;
        updateSorted();
        return true;
    }
    
    @Override
    public boolean add(int index, T element) {
        Node<T> currentNode = this.head; // start on head so if index = 0 element can become first Node such that head points to newNode
        Node<T> newNode = new Node<T>(element);

        // Case 1: element is null
        if (element == null) {
            return false;
        }

        // Case 2: Index bigger than list size or below zero
        if (index >= this.size || index < 0) {
            //System.out.println("fail");
            return false;
        }

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
            this.isSorted = true;
        }
    }

    @Override
    public T get(int index) {
        Node<T> currentNode = this.head.getNext();

        // Case 1: the index is out of bounds
        if (index > this.size - 1 || index < 0) {
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
        //TODO: fix this for is sorted
        if (this.size != 0) {
            Node<T> trailer = this.head;
            Node<T> pointer = trailer.getNext();
            Node<T> leader = pointer.getNext();
            double swaps = Math.floor(this.size / 2);

            // Loop that swaps the nodes and leaves the one at the end if length is odd
            for (int i = 0; i < swaps; i++) {
                trailer.setNext(leader);
                pointer.setNext(leader.getNext());
                leader.setNext(pointer);
                
                // Prevents reassignments if the swap was the last one
                if (swaps - (i+1) != 0.0) { 
                    trailer = trailer.getNext().getNext();
                    pointer = pointer.getNext();
                    leader = pointer.getNext();
                }
            }

            updateSorted();
        }
    }

    // TODO: check remove if still sorted?? kekw why tf would sorted even change if it was sorted in the first place
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

        // // Checks to see if list is sorted
        // if (this.isSorted != true) {
        //     // Resets trailer and pointer to iterate over the list
        //     trailer = this.head.getNext();
        //     pointer = trailer.getNext();

        //     this.isSorted = true; // Set to true by default then the iteration will change it based on if the list is sorted or not

        //     while (pointer != null) {
        //         //System.out.println("Trailer: " + trailer.getData());
        //         //System.out.println("pointer: " + pointer.getData());
        //         //System.out.println("    compare: " + trailer.getData().compareTo(pointer.getData()));

        //         // Checks if for any pair of trailer and pointer if trailer is > than pointer in which case the list would remain unsorted
        //         if (trailer.getData().compareTo(pointer.getData()) > 0) {
        //             this.isSorted = false;
        //         }
        //         trailer = trailer.getNext();
        //         pointer = pointer.getNext();
        //     }
        // }

        updateSorted();
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
        if (this.isSorted != true) {
            Node<T> pointer = this.head; 
            Node<T> testNode = null;
            T temp;
    
            // Loop through each node and then from
            for (int i = 0; i < this.size; i++) {
                //System.out.println("Pass: " + i);
                // Node index will point to node next to
                // current
                testNode = pointer.getNext();

                while (testNode != null) {
                    // If current node's data is greater
                    // than index's node data, swap the data
                    // between them
                    if (pointer.getData() != null && testNode.getData() != null) {
                        if (pointer.getData().compareTo(testNode.getData()) > 0) { 
                            //System.out.println("    Swap: " + pointer.getData() + " and " + testNode.getData());
                            temp = pointer.getData();
                            pointer.setData(testNode.getData());
                            testNode.setData(temp);
                        }
                    }
                    //System.out.println("        old testnode: " + testNode);
                    testNode = testNode.getNext();
                    //System.out.println("        new testnode: " + testNode);
                }
                //System.out.println("        old pointer: " + pointer);
                pointer = pointer.getNext();
                //System.out.println("        new pointer: " + pointer);
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
        System.out.println("testSorted() - testSorted Result: " + this.isSorted);
    }
    


    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<String>();
        
        System.out.println("Size: " + list.size());
        // list.add("3");
        // list.add("1");
        // list.add("4");
        // list.add("5");
        // list.add("7");
        // list.add("6");
        // list.add("2");

        list.add("b");
        list.add("a");
        list.add("d");
        list.add("c");

        //list.add("20");
        //list.add("5");
        //list.add("4");
        //list.add("0");

        // list.add("3");
        // list.add("6");
        // list.add("9");
        // list.add("7");
        
        System.out.println("isSorted: " + list.isSorted());

        

        System.out.println(list);
        //System.out.println(list.remove(1));
        //list.pairSwap();
        System.out.println(list);


        System.out.println("Size: " + list.size());
        System.out.println("isSorted: " + list.isSorted());




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
      
    }
    
}
