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
        // TODO Auto-generated method stub
        for(int i = 0; i < this.mainArray.length; i++){
            if(this.mainArray[i] == null){
                break;
            }
            mainString += this.mainArray[i].toString() + "\n";
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
        
    }

    @Override
    public void equalTo(T element) {
        // save earliest null, when earliest equal is found move ot earliest null and set that index ot be earliest null
        
    }

    @Override
    public T get(int index) {
        // TODO Auto-generated method stub
        T element;
        element = mainArray[index];
        return element;
    }

    @Override
    public int indexOf(T element) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isSorted() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void merge(List<T> otherList) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void pairSwap() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public T remove(int index) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void reverse() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void sort() {
        // TODO Auto-generated method stub
        
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
        System.out.println(test.toString());
        System.out.println("filled: " + test.filled);
        test.add("scuffed");
        test.add("tyler");
        System.out.println(test.toString());
        System.out.println("filled: " + test.filled);
        test.add("moment");
        System.out.println(test.toString());
        System.out.println("filled: " + test.filled);
        test.add(0, "not a");
        System.out.println(test.toString());
        System.out.println("filled: " + test.filled);
    }

}
