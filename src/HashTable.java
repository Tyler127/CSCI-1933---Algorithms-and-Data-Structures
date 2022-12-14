import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HashTable<T>{
    NGen<T>[] hashTable;
    public String type = "GENERAL";

    public HashTable(int length) {
        this.hashTable = new NGen[length];
        for (int i = 0; i < length; i++) hashTable[i] = new NGen<>();
    }

    /*
        This hash starts with 13 then multiplies by the character then divides by 23.
        Then, multiplies and divided by 2 sets of coprime numbers.
        Then, mods by the table length and takes the absolute value.
    */
    public int hash1(T item) {
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

    /*
        Adds the ASCII of each character to the total, 
        then multiplies and mods the total by large primes to result in a series of remainders, for each character in the string.
        prime numbers are best for hashing as the chance of different letters coming out to the same index is lessened
        the restriction of table size does reduce how effective the use of prime numbers is
    */
    public int hash2(T item) {
        int hashReturn = 0;
        if(item instanceof String) {
            //splits String into list of individual string characters
            String[] chars = ((String) item).split("");
            for(String string : chars) {//goes through list
                //adds the char to the total int(char is automatically converted into ASCII value if directly put to an int)
                hashReturn += string.charAt(0);
                hashReturn = hashReturn * 7919;
                hashReturn = hashReturn % 7717;
                hashReturn = hashReturn * 7549;
                hashReturn = hashReturn % 7351;
                hashReturn = hashReturn * 7159;
                //adding next prime INCREASES average length  
                // skill issue + 1 
                //hashReturn = hashReturn % 6967;

            }
            //hashreturn is modded by table length to keep within table bounds
            hashReturn = hashReturn % hashTable.length;

            //If the value goes over the max is automatically becomes negative, in that event the absolute value is taken to keep within table boundaries
            if(hashReturn < 0) hashReturn = Math.abs(hashReturn);
        }
        //after all characters have been added and hashed, total is returned
        return hashReturn;
    }

    /*
        This hash is an implementation of the djb2 hash function created by Dan Bernstein. 
        No output is printed with this function! It was used for comparison to our hashes.
        Works by starting with 5391.
        Then, loops over the string and multiplies by 33 and adds the character.
        Then, multiplies and divided by 2 sets of coprime numbers.
        Then, mods by the table length and takes the absolute value.
    */
    public int djb2Hash(T item) {
        int hashReturn = 5391;
        if (item instanceof String) {
            String[] chars = ((String) item).split("");
            for (String string : chars) {
                hashReturn = (hashReturn * 33) + string.charAt(0);
            }
            // Mod hash return, so it is always within the index range of the hashTable
            hashReturn = hashReturn % hashTable.length;

            // If the hash function went over the int max value, take absolute value of the mod
            if (hashReturn < 0) hashReturn = Math.abs(hashReturn);
        }
        return hashReturn;
    }

    /*  
        The add method which adds an item to the hash table using your best performing hash function
        Does NOT add duplicate items
    */
    public void add(T item) {
        // Case 1: Item is null
        if (item == null) return;

        // Decide which hash function to use based on type
        NGen<T> head = null;
        if (this.type.equals("GENERAL")) head = this.hashTable[this.hash1(item)];
        else if (this.type.equals("SPECIFIC")) head = this.hashTable[this.hash2(item)];
        else if (this.type.equals("DJB2")) head = this.hashTable[this.djb2Hash(item)];

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
            /* Check again for equivalence when pointer.getNext() == null,
             as this case isn't included in the while loop */
            if (pointer.getData().equals(item)) return;

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

    /*  
        The display method which prints the indices of the hash table and the number of words "hashed"
        to each index.
    */
    public void display() {
        int numUniqueWords = 0;
        int longestChain = 0;
        int numEmptyIndices = 0;
        int numNonEmptyIndices;
        String finalString = "";

        for (int i = 0; i < this.hashTable.length; i++) {
            NGen<T> head = this.hashTable[i];

            // Case 1: Head next is null.
            if (head.getNext() == null) {
                numEmptyIndices++;
                finalString += i + " (0 items):\n";
            }

            // Case 2: Count list length and check item uniqueness
            else {
                NGen<T> pointer = head.getNext();
                String nodeString = "";
                int counter = 1;
                numUniqueWords++;

                while (pointer.getNext() != null) {
                    counter++;
                    numUniqueWords++;
                    nodeString += "[" + pointer.getData() + "] -> ";
                    pointer = pointer.getNext();
                }
                nodeString += "[" + pointer.getData() + "] \n";
                finalString = finalString + i + " (" + counter + " items): " + nodeString;

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
        System.out.println("\n" + finalString);
    }

    public NGen<T>[] getHashTable() {return this.hashTable;}

    // Create a hash table, store all words from "canterbury.txt", and display the table
    // Create another hash table, store all words from "keywords.txt", and display the table
    public static void main(String args[]) {
        /*
        ------------------ ANALYSIS: -------------------------
            - Each individual hash function is explained above the method. 

        ATTEMPTS:
            - The first hash function we attempted was simply adding all of the characters
                together and then modding that sum by the table length.

            - The second hash function attempt, for each char in the input string,
                we added the char multiplied by the length of the string.
                Then modded the sum by the table length.

            - After deciding to multiply by big prime numbers and coprime numbers, we added in the abs value
                of the mod to prevent negative results because the sums were going above
                the max value for integers.
        
        PERFORMANCE:
            - For the hash1 and hash2 we used (general and specific respectively), 
                hash1 performed around the same as hash2 for the general gettysburg txt file
                with a max chain length of 4 vs 5. 
                The performance was similar for the specific keywords txt file, with hash1 and hash2
                both having a max chain length of 2. Although the hash2 had a slight but negligible 
                advantage with 1.04 average collision length compared to hash1's 1.08.

            - So either hash function would have been effective for either use case. We chose 
                hash1 for general and hash2 for specific.

            - We used even, odd, and prime numbers for the table lengths while testing.
                We came to the conclusion that there isn't much of an advantage to 
                hand-picking a specific table length, as the advantages for doing so
                was often just a difference of 1 in max chain length

        */

        System.out.println("----------GETTYSBURG TEST (GENERAL)----------");
        HashTable<String> hashTable = new HashTable<>(150);
        hashTable.type = "GENERAL";
        hashTable.addWordsFromFile("src/gettysburg.txt");
        hashTable.display();

        System.out.println("----------KEYWORDS TEST (SPECIFIC)----------");
        HashTable<String> hashTable2 = new HashTable<>(500);
        hashTable2.type = "SPECIFIC";
        hashTable2.addWordsFromFile("src/keywords.txt");
        hashTable2.display();
    }
}
