
public class ArrayList<T extends Comparable<T>> implements List<T> {
    private boolean isSorted = true;
    private T[] mainArray = (T[]) new Comparable[2];

    public ArrayList(){}

    @Override
    public String toString() {
        String mainString = "";
        // TODO Auto-generated method stub
        for(int i = 0; i < this.mainArray.length; i++){
            mainString += this.mainArray.toString() + "\n";
        }
        return mainString;
    }

    @Override
    public boolean add(T element) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean add(int index, T element) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void equalTo(T element) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public T get(int index) {
        // TODO Auto-generated method stub
        return null;
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
                if(this.mainArray[i].compareTo(this.mainArray[i + 1]) > 0){
                    this.isSorted = false;
                }
            }
        }
    }
    
    public static void main(String[] args){
        ArrayList<String> test = new ArrayList<String>();
        System.out.println(test.toString());
    }

}
