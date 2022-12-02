public class BinaryTree<V extends Comparable<V>> {
    private Node<V> root;

    public BinaryTree(Node<V> root) {
        this.root = root;
    }

    public Node<V> getRoot() {
        return this.root;
    }

    public void printInorder() {
        printInOrderHelper(root);
    }
    private void printInOrderHelper(Node<V> node) {
        // TODO: Fill in definition
        if(node == null){
        }else{
            //left first
            printInOrderHelper(node.getLeft());
            //then prints node itself
            System.out.print(node.getValue() + " ");
            //then goes right
            printInOrderHelper(node.getRight());
        }
    }

    public void printPreorder(){
        printPreorderHelper(root);
    }
    private void printPreorderHelper(Node<V> node) {
        // TODO: Fill in definition
        if(node == null){
        }else{
            //node itself
            System.out.print(node.getValue() + " ");
            //left
            printPreorderHelper(node.getLeft());
            //right
            printPreorderHelper(node.getRight());
        }
    }

    public void printPostorder() {
        printPostorderHelper(root);
    }
    private void printPostorderHelper(Node<V> node) {
        // TODO: Fill in definition
        if(node == null){
        }else{
            //left
            printPostorderHelper(node.getLeft());
            //right
            printPostorderHelper(node.getRight());
            //node itself
            System.out.print(node.getValue() + " ");
        }
    }

    private int counter = 0;

    public void counterHelper(Node<V> node){
        if(node == null){
        }else{
            //left
            counterHelper(node.getLeft());
            //right
            counterHelper(node.getRight());
            //node itself
            this.counter++;
        }
    }
    private int indexTracker = 0;
    private V[] methodArray = (V[]) new Comparable[0];
    public void arrayHelper(Node<V> node){
        if(node == null){
        }else{
            //left
            arrayHelper(node.getLeft());
            //right
            arrayHelper(node.getRight());
            //node itself
            this.methodArray[indexTracker] = node.getValue();
            this.indexTracker++;
        }
    }

    public V[] flatten() {
        // TODO: Fill in definition
        //changes array to be correct size
        this.counter = 0;
        counterHelper(root);
        methodArray = (V[]) new Comparable[counter];

        arrayHelper(root);

        sort(methodArray);

        return methodArray;
    }

    // bubble sort
    // useful for flatten
    public void sort(Comparable[] a) {
        int i, j;
        Comparable temp;
        boolean swapped = true;
        for (i = 0; i < a.length && swapped == true; i++) {
            swapped = false;
            for (j = 1; j < a.length - i; j++) {
                if (a[j].compareTo(a[j-1]) < 0) {
                    swapped = true;
                    temp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = temp;
                }
            }
        }
    }

    public void invert() {
        invertHelper(root);
    }

    public void invertHelper(Node<V> node) {
        // TODO: Fill in definition
        if(node == null){
        }else{
            //left
            invertHelper(node.getLeft());
            //right
            invertHelper(node.getRight());
            //node itself
            Node<V> holder = node.getLeft();
            node.setLeft(node.getRight());
            node.setRight(holder);
        }
    }

    public boolean containsSubtree(BinaryTree<V> other) {
        // TODO: Fill in definition
        return false;
    }

    public int getMaxDepth(Node<V> node){
        if(node.getLeft() == null && node.getRight() == null){
            return 1;
        }
        if(node.getLeft() == null){
            return 1 + getMaxDepth(node.getRight());
        }
        if(node.getRight() == null){
            return 1 + getMaxDepth(node.getLeft());
        }
        else{
            return 1 + Math.max(getMaxDepth(node.getLeft()), getMaxDepth(node.getRight()));
        }
    }

    // Main contains tests for each milestone.
    // Do not modify existing tests.
    // Un-comment tests by removing '/*' and '*/' as you move through the milestones.
    public static void main (String args[]) {
        // Tree given for testing on
        BinaryTree<Integer> p1Tree = new BinaryTree<Integer>(new Node<Integer>(1,
                new Node<Integer>(2,
                        new Node<Integer>(4, null, null),
                        new Node<Integer>(5, null, null)),
                new Node<Integer>(3, null, null)));

        // // Milestone 1 (Traversing)
        // System.out.println("--- Milestone 1 ---");
        // System.out.print("Expected: 4 2 5 1 3" + System.lineSeparator() + "Actual: ");
        // p1Tree.printInorder();
        // System.out.println(System.lineSeparator());
        // System.out.print("Expected: 1 2 4 5 3" + System.lineSeparator() + "Actual: ");
        // p1Tree.printPreorder();
        // System.out.println(System.lineSeparator());
        // System.out.print("Expected: 4 5 2 3 1" + System.lineSeparator() + "Actual: ");
        // p1Tree.printPostorder();
        // System.out.println();

        // Milestone 2 (flatten) -- expected output: 1 2 3 4 5
        /*
        System.out.println(System.lineSeparator() + "--- Milestone 2 ---");
        System.out.print("Expected: 1 2 3 4 5" + System.lineSeparator() + "Actual: ");

        Comparable[] array_representation = p1Tree.flatten();
        for (int i = 0; i < array_representation.length; i++) {
            System.out.print(array_representation[i] + " ");
        }
        System.out.println();
        */
        // Milestone 3 (invert)
        
        /* 
        System.out.println(System.lineSeparator() + "--- Milestone 3 ---");

        p1Tree.invert();

        System.out.print("Expected: 3 1 5 2 4" + System.lineSeparator() + "Actual: ");
        p1Tree.printInorder();
        System.out.println(System.lineSeparator());
        System.out.print("Expected: 1 3 2 5 4" + System.lineSeparator() + "Actual: ");
        p1Tree.printPreorder();
        System.out.println(System.lineSeparator());
        System.out.print("Expected: 3 5 4 2 1" + System.lineSeparator() + "Actual: ");
        p1Tree.printPostorder();
        System.out.println();
        */
        

        // Milestone 4 (containsSubtree)
        /*
        System.out.println(System.lineSeparator() + "--- Milestone 4 ---");

        p1Tree = new BinaryTree<Integer>(new Node<Integer>(1,
                new Node<Integer>(2,
                        new Node<Integer>(4, null, null),
                        new Node<Integer>(5, null, null)),
                new Node<Integer>(3, null, null)));
        BinaryTree<Integer> p2Tree = new BinaryTree<Integer>(new Node<Integer>(2,
                new Node<Integer>(4, null, null),
                new Node<Integer>(5, null, null)));
        BinaryTree<Integer> p3Tree = new BinaryTree<Integer>(new Node<Integer>(1,
                new Node<Integer>(2, null, null),
                new Node<Integer>(3, null, null)));
        BinaryTree<Integer> p4Tree = null;

        System.out.print("Expected: true" + System.lineSeparator() + "Actual: ");
        System.out.println(p1Tree.containsSubtree(p2Tree));
        System.out.println();

        System.out.print("Expected: false" + System.lineSeparator() + "Actual: ");
        System.out.println(p1Tree.containsSubtree(p3Tree));
        System.out.println();

        System.out.print("Expected: true" + System.lineSeparator() + "Actual: ");
        System.out.println(p1Tree.containsSubtree(p4Tree));
        */

    }
}
