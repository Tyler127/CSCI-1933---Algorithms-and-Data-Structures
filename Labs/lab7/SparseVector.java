public class SparseVector {

	private Node head;
	private int length;

	public SparseVector(int len){
		head = null;
		length = len;
	}

	// Prints out a sparse vector (including zeros)
	public String toString(){

		String result = "";
		Node currNode = head;
		int currIndex = 0;
		while( currNode != null ){
			int idx = currNode.getIndex();

			// Pad the space between nodes with zero
			while( currIndex < idx ){
				result += "0, ";
				currIndex++;
			}
			result += currNode;
			currNode = currNode.getNext();
			currIndex++;

			// Only add a comma if this isn't the last element
			if( currNode != null ){ result += ", "; }
		}
		return result;
	}

	// TODO: Implement me for milestone 2
	public void addElement(int index, double value){
		Node currNode = head;
		if(index > this.length){
			System.out.println("invalid index");
		}
		if(currNode == null){
			head = new Node(index, value);
			//System.out.println("null head, " + value + " added at index " + index);
		}else{
		while(currNode.getIndex() < index){
			if(currNode.getIndex() != index){
				if (currNode.getNext() == null){
					Node newNode = new Node(index, value);
					currNode.setNext(newNode);
					//System.out.println("new node set at index " + index);
				}else{
					//System.out.println("wrong index: " + currNode.getIndex() + ", moving to next");
					currNode = currNode.getNext();
				}
			}
		}
		}
	}

	// TODO: Implement me for milestone 4
	public static double dot( SparseVector x, SparseVector y ){
		double total = 0.0;
		Node currNodeX = x.head;
		Node currNodeY = y.head;
		if(x.length != y.length){
			System.out.println("lengths not equal");
			return 0.0;
		}
		if(x.head == null || y.head == null){
			System.out.println("1 or more lists empty");
			return 0.0;
		}
		if (x.length == 1) {
			return (currNodeX.getValue() * currNodeY.getValue());
		}
		
		while(currNodeX.getNext() != null && currNodeY.getNext() != null){
			if(currNodeX.getIndex() < currNodeY.getIndex()){
				currNodeX = currNodeX.getNext();
				System.out.println("x is less than");
			}
			else if(currNodeY.getIndex() < currNodeX.getIndex()){
				currNodeY = currNodeY.getNext();
				System.out.println("y is less than");
			}
			else{
				double scubby = currNodeX.getValue() * currNodeY.getValue();
				System.out.println(scubby);
				total += scubby;
				currNodeX = currNodeX.getNext();
				currNodeY = currNodeY.getNext();
				System.out.println(currNodeX.getValue());
			}
		}
		return total;
	}


	// TODO: Test out your code here!
	public static void main(String[] args) {
		SparseVector vec = new SparseVector(2);
		SparseVector vec2 = new SparseVector(2);
		vec.addElement(0, 10.0);
		vec.addElement(1, 3);
		vec2.addElement(0, 2);
		vec2.addElement(1, 3);
		
		//System.out.println(vec);
		
		double x = SparseVector.dot(vec, vec2);
		System.out.println(x);
	}
}
