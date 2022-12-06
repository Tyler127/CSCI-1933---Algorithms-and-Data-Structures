import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HashTable<T>{
    NGen<T>[] hashTable;
    public String type = "GENERAL";

    public HashTable(int length) {
        this.hashTable = new NGen[length];
        for (int i = 0; i < length; i++) hashTable[i] = new NGen<T>();
    }

    public int hash1(T item) {
        /*
        Multiplicative hashing: (usually on numbers but used the concept of coprime numbers here)
        Standard multiplicative hashing uses the formula:
        hash(K) = (aK mod W) / (W/table length)
        which produces a hash value in {0,...,table length - 1}.
        The value 'a' is an appropriately chosen value that should be relatively prime to 'W';
        it should be large and its binary representation a random mix of 1's and 0's.
         */

        int hashReturn = 13;
        if (item instanceof String) {
            String[] chars = ((String) item).split("");
            for (String string : chars) {
                hashReturn = (hashReturn * string.charAt(0)) / 23;

                hashReturn = hashReturn * 12;
                hashReturn = hashReturn / 13;

                hashReturn = hashReturn * 23;
                hashReturn = hashReturn / 5;
            }

            // Mod hash return, so it is always within the index range of the hashTable
            hashReturn = hashReturn % hashTable.length;

            // If the hash function went over the int max value, take absolute value of the mod
            if (hashReturn < 0) hashReturn = Math.abs(hashReturn);

            //System.out.println("Hash Return: " + hashReturn + "\n");
        }
        return hashReturn;
    }

    //TODO: Implement a second (different and improved) hash function
    public int hash2(T item) {
        int hashReturn = 0;
        if (item instanceof String) {
            String[] chars = ((String) item).split("");
            for (String string : chars) {
                hashReturn += string.charAt(0);
                hashReturn = hashReturn * 97879;
                hashReturn = hashReturn * 999451;
                //hashReturn = hashReturn ^ string.charAt(0) >>> 3;
            }

            // Mod hash return, so it is always within the index range of the hashTable
            hashReturn = hashReturn % hashTable.length;

            // If the hash function went over the int max value, take absolute value of the mod
            if (hashReturn < 0) hashReturn = Math.abs(hashReturn);
        }
        return hashReturn;
    }

    //TODO: Implement the add method which adds an item to the hash table using your best performing hash function
    // Does NOT add duplicate items
    public void add(T item) {
        // Case 1: Item is null
        if (item == null) return;

        // Decide which hash function to use based on type
        NGen<T> head = null;
        int hashValue = 0;
        if (this.type.equals("GENERAL")) {
            hashValue = this.hash1(item);
            head = this.hashTable[hashValue];
        }
        else if (this.type.equals("SPECIFIC")) {
            hashValue = this.hash2(item);
            head = this.hashTable[hashValue];
        }

        // Case 2: head points to nothing
        if (head.getNext() == null) head.setNext(new NGen<>(item, null));

        // Case 3: Loop until current node next is null and add new node
        else {
            NGen<T> pointer = head.getNext();

            while (pointer.getNext() != null) {
                //System.out.println("    in while loop:");
                //System.out.println("        current node data: " + pointer.getData());
                //System.out.println("        item data: " + item);

                // Return if the pointer's data is equal to item
                if (pointer.getData().equals(item)) return;

                pointer = pointer.getNext();
            }

            // Check again for equivalence when pointer.getNext() == null,
            // as this case isn't included in the while loop
            if (pointer.getData().equals(item)) {
                //System.out.println("    after while - current node == item: " + pointer.getData().equals(item));
                //System.out.println("\n");
                return;
            }
            //System.out.println("            Case 3 - new node added with data: " + item);
            pointer.setNext(new NGen<>(item, null));

        }
    }

    // Adds all words from a given file to the hash table using the add(T item) method above
    @SuppressWarnings("unchecked")
    public void addWordsFromFile(String fileName) {
        Scanner fileScanner = null;
        String word;
        try {
            fileScanner = new Scanner(new File(fileName));
        }
        catch (FileNotFoundException e) {
            System.out.println("File: " + fileName + " not found.");
            System.exit(1);
        }
        while (fileScanner.hasNext()) {
            word = fileScanner.next();
            word = word.replaceAll("\\p{Punct}", ""); // removes punctuation
            this.add((T) word);
        }
    }

    //TODO: Implement the display method which prints the indices of the hash table and the number of words "hashed"
    // to each index. Also prints:
    // - total number of unique words
    // - number of empty indices
    // - number of nonempty indices
    // - average collision length
    // - length of longest chain
    public void display() {
        int numUniqueWords = 0;
        int longestChain = 0;
        int numEmptyIndices = 0;
        int numNonEmptyIndices;

        for (NGen<T> head : this.hashTable) {
            // Case 1: Head next is null.
            if (head.getNext() == null) numEmptyIndices++;

            // Case 2: Count list length and check item uniqueness
            else {
                NGen<T> pointer = head.getNext();
                int counter = 1;
                numUniqueWords++;

                while (pointer.getNext() != null) {
                    counter++;
                    numUniqueWords++;
                    pointer = pointer.getNext();
                }

                // Reassign longestChain if this list had more items
                if (counter > longestChain) longestChain = counter;
            }
        }
        System.out.println("Number of Unique Words: " + numUniqueWords);
        System.out.println("Number of Empty Indices: " + numEmptyIndices);
        numNonEmptyIndices = this.hashTable.length - numEmptyIndices;
        System.out.println("Number of Non-Empty Indices: " + numNonEmptyIndices);
        System.out.println("Average Collision Length: " + (float) numUniqueWords/numNonEmptyIndices);
        System.out.println("Length of Longest Chain: " + longestChain);
    }

    public NGen<T>[] getHashTable() {return this.hashTable;}


    // TODO: Create a hash table, store all words from "canterbury.txt", and display the table
    //  Create another hash table, store all words from "keywords.txt", and display the table
    public static void main(String args[]) {


    }
}
