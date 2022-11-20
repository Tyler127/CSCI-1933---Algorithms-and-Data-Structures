package Project3;

// Written by LARS6653 and KANTE060

@SuppressWarnings("unchecked")
public class ArrayList<T extends Comparable<T>> implements List<T> {

    private boolean isSorted = true;
    private int filled = 0;
    private T[] mainArray = (T[]) new Comparable[2];

    public ArrayList(){}

    private void grow(){

        T[] biggerArray = (T[]) new Comparable[(mainArray.length * 2)];
        
        for (int i = 0; i < mainArray.length; i++){
            biggerArray[i] = mainArray[i];
        }

        this.mainArray = biggerArray;
    }

    @Override
    public String toString() {

        String mainString = "";
        for(int i = 0; i < filled; i++){
            if(i == filled - 1){
                mainString += this.mainArray[i].toString();
            }else{
            mainString += this.mainArray[i].toString() + "\n";
            }
        }
        return mainString;
    }

    @Override
    public boolean add(T element) {
        //add to end of list, if element is null return false
        //otherwise, add and return true, update isSorted

        if(element == null){//if element is null, returns false
            return false;
        }
        else if(filled == mainArray.length){//array is full, grows array size
            grow();
        }
        mainArray[filled] = element;//assigns element to earliest null point
        filled ++;//updates number of filled spots
        updateSorted();//checks if sorted
        return true;
    }

    @Override
    public boolean add(int index, T element) {
        // adds element at specifies list
            //if element is null, return false**
            //if index is out of bounds, return false**
            //if array too small, make bigger**
            //if index is filled, move everything up 1 index and place element in index
        if(element == null){//if element is null, returns false
            return false;
        }
        else if(index < 0 || index >= filled){//if index is out of bounds(below 0 or above filled part of list), returns false
            return false;
        }
        else if(filled == mainArray.length){//array is full, grows array size
            grow();
        }
        T holder = element;
        T holder2;
        int pointer = index;
        filled ++;
        while(pointer != filled){
            holder2 = mainArray[pointer];
            mainArray[pointer] = holder;
            holder = holder2;
            pointer ++;
        }
        updateSorted();
        return true;
    }

    @Override
    public void clear() {
        // set array back to an empty length 2 array
        T[] mainArray2 = (T[]) new Comparable[2];
        this.mainArray = mainArray2;
        this.isSorted = true;
        this.filled = 0;
        
    }

    @Override
    public void equalTo(T element) {
        // save earliest null, when earliest equal is found move ot earliest null and set that index ot be earliest null
        int eNull = 0;
        int origFilled = filled;
        int checker = 0;
        for(int i = 0; i < origFilled; i++){//if sorted, and next element is not equal, everything after must be made null anyways
            if(this.isSorted == true && mainArray[i].compareTo(element) > 0){
                checker = i;
                break;
            }
            if(mainArray[i] != element){//if elements are not equal, removes element and decreases num of spaced filled
                mainArray[i] = null;
                this.filled --;
            }else{//elements are equal, removes element at index, sets index with earliest null to element, increases index of earliest null
                mainArray[i] = null;
                mainArray[eNull] = element;
                eNull ++;
            }
        }
        if(checker != 0){//used to optimise if sorted is true by cutting off for loop and then setting everything greater than the element to null, can't cut stuff off like linked list
            while(checker != origFilled){
                mainArray[checker] = null;
                filled --;
                checker ++;
            }
        }
        updateSorted();
        
    }

    @Override
    public T get(int index) {
        T element;
        if(index == this.size()){
            return null;
        }
        if(index > filled || index < 0){
            return null;
        }else{
        element = mainArray[index];
        return element;
        }
    }

    @Override
    public int indexOf(T element) {
        //return index of first instance of requested element
        // if element is not found or if element is null, return -1
        if(element == null){
            return -1;
        }

        for(int i = 0; i < filled; i ++){

            if(element.compareTo(mainArray[i]) < 0 && this.isSorted == true){//if index being checked is greater than element and the list is sorted, element not in list
                return -1;
            }
            if(mainArray[i] == element){
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean isEmpty() {

        if(filled == 0){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public boolean isSorted() {
        return isSorted;
    }

    @Override
    public void merge(List<T> otherList) {

        if(otherList != null){

            ArrayList<T> other = (ArrayList<T>) otherList;
            sort();
            other.sort();

            int totalFilled = filled + other.filled;
            T[] newArray = (T[]) new Comparable[totalFilled];//sets up array with space for the variables in each
            int thisIndex = 0, otherIndex = 0;//tracks position in each list

            for(int i = 0; i < totalFilled; i++){
                //System.out.println("i: " + i);
                if(thisIndex < this.filled && otherIndex < other.filled){//changes method if either has all their stuff added while the other has more
                    if(mainArray[thisIndex].compareTo(other.mainArray[otherIndex]) < 0){
                        newArray[i] = mainArray[thisIndex];
                        //System.out.println("this index: " + thisIndex);
                        thisIndex++;
                    }else{
                        newArray[i] = other.mainArray[otherIndex];
                        //System.out.println("other index: " + otherIndex);
                        otherIndex++;
                    }
                }
                else{
                
                    if(thisIndex == this.filled){
                        newArray[i] = other.mainArray[otherIndex];
                        //System.out.println("other index option 2: " + otherIndex);
                        otherIndex++;
                    }else if(otherIndex == other.filled){
                        newArray[i] = mainArray[thisIndex];
                        //System.out.println("this index option 2: " + thisIndex);
                        thisIndex++;
                    }
                }
            }
            this.filled = totalFilled;
            this.mainArray = newArray;
            updateSorted();
        }
    }

    @Override
    public void pairSwap() {
        //swap every set of 2 objects in list, IN PLACE
        //if odd number of items last one stays in place
        int k = 0;//used to determine length of loop
        if(filled % 2 == 0){
            k = filled;
        }else if( filled % 2 == 1){
            k = filled - 1;
        }

        int j = 0;
        for(int i = 1; i < k; i = i + 2){
            T holder = mainArray[j];
            mainArray[j] = mainArray[i];
            mainArray[i] = holder;
            j = j + 2;
        }
        updateSorted();
    }

    @Override
    public T remove(int index) {

        if(index < 0 || index >= filled){//if index is out of bounds, returns null
            return null;
        }
        
        //removes item at specified index, shifts items to right over to remove nulls
        T toReturn = mainArray[index];
        mainArray[index] = null;
        int i = index;

        if(index != filled - 1){
            while(i < filled - 1){
                mainArray[i] = mainArray[i + 1];
                i ++;
            }
            mainArray[i] = null;
        }
        filled --;
        updateSorted();
        return toReturn;
    }

    @Override
    public void reverse() {

        // reverses list IN PLACE
        int loopLength = 0;
        if(filled % 2 == 1){//checks if array is even size or not, and determines length of loop accordingly
            loopLength = (filled - 1) / 2;
        }else{
            loopLength = filled / 2;
        }

        for(int i = 0; i < loopLength; i ++){
            int x = (filled - 1) - i;// same distance from back of filled section of list as 'i' is fromthe beginning of the list
            T holder = mainArray[i];//holds item near beginnning of the list
            mainArray[i] = mainArray[x];
            mainArray[x] = holder;
        }
        updateSorted();
    }

    @Override
    public int size() {
        return filled;
    }

    @Override
    //Source: Insert.java on CSCI1933 Canvas
    public void sort() {//based on insertion sort from canvas files

        if(isSorted != true){
            int i, j;
            T n;//used to store lower item in list to compare
            for (i = 1; i < filled; i++) {
                n = mainArray[i];
                for (j = i-1; j >= 0 && n.compareTo(mainArray[j]) < 0; j--) {
                    mainArray[j+1] = mainArray[j];
                }
                mainArray[j+1] = n;
            }
            isSorted = true;
        }
    }

    private void updateSorted(){

        if(filled == 1){//If length is 1, is sorted
            this.isSorted = true;
        }
        
        else{
            this.isSorted = true;
            for(int i = 0; i < filled - 1; i++){//compares every index to the next one(except the last index as it is already compared), if the next index is smaller than the previous, is not sorted
                if(mainArray[i + 1] != null){
                    if(this.mainArray[i].compareTo(this.mainArray[i + 1]) > 0){
                        this.isSorted = false;
                    }
                }
            }
        }
    }
}
