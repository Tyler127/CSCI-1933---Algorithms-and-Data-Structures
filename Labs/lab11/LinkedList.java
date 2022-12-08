package lab11;
public class LinkedList<T extends Comparable<T>> implements Comparable<LinkedList<T>> {

        public Node<T> head;
        public Node<T> tail;
        public int size;

        public LinkedList() {
            head = new Node<T>(null, null);
            tail = head;
            size = 0;
        }

        // add()
        public boolean add(T element) {
            if (element == null) {
                return false;
            } else {
                Node<T> newNode = new Node<T>(element);
                tail.setNext(newNode);
                tail = tail.getNext() ;
                return true;
            }
        }

        // get()
        public T get(int index) {
            if (index < 0) {
                return null;
            }
            Node<T> ptr = this.head.getNext();
            int currentIndex = 0;
            while (ptr != null && currentIndex <= index) {
                if (currentIndex == index) {
                    return ptr.getData();
                } else {
                    ptr = ptr.getNext();
                    currentIndex++;
                }
            }
            return null;
        }

        // isEmpty()
        public boolean isEmpty() {
            if (head.getNext() == null) return true;
            return false;
        }

        // size()
        public int size() {
            Node<T> ptr = this.head.getNext();
            int size=1;
            if (!this.isEmpty()){
                while(ptr.getNext() != null) {
                    ptr = ptr.getNext();
                    size++;
                }
                return size;
            }
            //return 0 if the list was empty
            return 0;
        }

        // clear()
        public void clear(){
            this.head = new Node<T>(null, null);
        }

        @Override
        public int compareTo(LinkedList<T> o) {
            return 0;
        }

        public String toString(){
            Node<T> cur = head;
            StringBuilder s = new StringBuilder();

            for (int i = 0; cur != null; cur = cur.getNext(), i++)
                s.append("Element ".concat(String.valueOf(i)).concat(": ").concat(String.valueOf(cur.getData())).concat("\n"));

            return s.toString();
        }

        public void removeEvery(int n) {
            Node<T> trailer = this.head;
            Node<T> pointer = this.head.getNext();
            System.out.println(trailer.getData());
                System.out.println(pointer.getData());
            int loopTimes = (int) Math.floor(this.size() / n);
            
            System.out.println("LoopTimes: " + loopTimes);

            // while (pointer.getNext() != null) {
            // }

            for (int i = 0; i < loopTimes; i++) {
                //System.out.println(trailer.getData());
                //System.out.println(pointer.getData());
                if (pointer !=  null && pointer.getNext() != null) {
                    for (int j=0; j < n; j++) {
                        pointer = pointer.getNext();
                        trailer = trailer.getNext();
                    }
                    pointer = pointer.getNext();
                    trailer.setNext(pointer);
                }
                    //trailer.setNext(pointer.getNext());
            }
        }

        // TODO: extractGroupsOf()
        public LinkedList<LinkedList<T>> extractGroupsOf(int n) {return null;}

        // Main method for testing purposes.
        public static void main(String[] args) {
            LinkedList<Integer> limst = new LinkedList<Integer>();
            for(int i = 0; i<10;i++){
                limst.add(i);
            }
            System.out.println(limst);
            limst.removeEvery(1);
            System.out.println(limst);

        }
}