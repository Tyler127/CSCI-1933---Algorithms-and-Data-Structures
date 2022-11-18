import java.beans.IndexedPropertyChangeEvent;

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
    public String toString() {//soemthing is still up, works for string
        String mainString = "";
        // TODO Auto-generated method stub
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
    public boolean add(T element) {//checked and works
        //add to end of list, if element is null return false
        //otherwise, add and return true, update isSorted

        if(element == null){//if element is null, returns false
            return false;
        }
        else if(filled == mainArray.length){//array is full, grows array size
            grow();
        }
        int pointer = 0;
        while(mainArray[pointer] != null){//moves pointer up until it finds null index
            pointer ++;
        }
        mainArray[pointer] = element;//assigns element to earliest null point
        filled ++;//updates number of filled spots
        updateSorted();//checks if sorted
        return true;
    }

    @Override
    public boolean add(int index, T element) {//checked and works
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
    public void clear() {//works
        // set array back to an empty length 2 array
        T[] mainArray2 = (T[]) new Comparable[2];
        this.mainArray = mainArray2;
        this.isSorted = true;
        this.filled = 0;
        
    }

    @Override
    public void equalTo(T element) {//works
        // save earliest null, when earliest equal is found move ot earliest null and set that index ot be earliest null
        int eNull = 0;
        int origFilled = filled;
        for(int i = 0; i < origFilled; i++){
            if(mainArray[i] != element){//if elements are not equal, removes element and decreases num of spaced filled
                mainArray[i] = null;
                this.filled --;
            }else{//elements are equal, removes element at index, sets index with earliest null to element, increases index of earliest null
                mainArray[i] = null;
                mainArray[eNull] = element;
                eNull ++;
            }
        }
        
    }

    @Override
    public T get(int index) {//works
        // TODO Auto-generated method stub
        T element;
        if(index > mainArray.length){
            return null;
        }else{
        element = mainArray[index];
        return element;
        }
    }

    @Override
    public int indexOf(T element) {//works, not optimized
        //return index of first instance of requested element
        // if element is not found or if element is null, return -1
        if(element == null){
            return -1;
        }
        for(int i = 0; i < filled; i ++){
            if(mainArray[i] == element){
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {//works
        if(filled == 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean isSorted() {//works
        // TODO Auto-generated method stub
        return isSorted;
    }

    @Override
    public void merge(List<T> otherList) {//do after finished sort
        // TODO Auto-generated method stub
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
                }else{
                
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
            this.filled = newArray.length;
            this.mainArray = newArray;
            updateSorted();
        }
        
    }

    @Override
    public void pairSwap() {//works
        // TODO swap every set of 2 objects in list, IN PLACE
        //if odd number of items last one stays in place
        int k = 0;
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
    }

    @Override
    public T remove(int index) {//works
        // TODO removes item at specified index, shifts items to right over to remove nulls
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
    public void reverse() {//works
        // TODO reversed list IN PLACE
        int loopLength = 0;
        if(filled % 2 == 1){
            loopLength = (filled - 1) / 2;
        }else{
            loopLength = filled / 2;
        }
        for(int i = 0; i < loopLength; i ++){
            int x = (filled - 1) - i;
            T holder = mainArray[i];
            mainArray[i] = mainArray[x];
            mainArray[x] = holder;
        }
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return filled;
    }

    @Override
    //Source: Insert.java on CSCI1933 Canvas
    public void sort() {//based on insertion sort from canvas files//works
        // TODO sort all elements in list, ascending order, using INSERTION SORTING
        //if is already sorted, do nothing
        //use compareTo()
        //update isSorted
        if(isSorted != true){
            int i, j;
            T n;
            int counter = 0;
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
        if(mainArray.length == 1){//If length is 1, is sorted
            this.isSorted = true;
        }
        else{
            for(int i = 0; i < mainArray.length - 1; i++){//compares every index to the next one(except the last index as it is already compared), if the next index is smaller than the previous, is not sorted
                if(this.mainArray[i + 1] == null){
                    break;
                }
                if(this.mainArray[i].compareTo(this.mainArray[i + 1]) > 0){
                    this.isSorted = false;
                }
            }
        }
    }
    
    public static void main(String[] args){
        ArrayList<String> test = new ArrayList<String>();
        ArrayList<String> test2 = new ArrayList<String>();
        test.add("1");
        test.add("2");
        test2.add("3");
        test2.add("4");
        test.merge(test2);
        //System.out.println(test);
        test.add("sus");
        test.reverse();
        //test.pairSwap();
        //String x = test.remove(4);
        System.out.println(test);
        //System.out.println("this be: " + x)
        test.clear();
        System.out.println(test.size());
    }

}
