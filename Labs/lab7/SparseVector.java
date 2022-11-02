public class SparseVector {

	private Lab7Node head;
	private int length;

	public SparseVector(int len){
		head = null;
		length = len;
	}

	// Prints out a sparse vector (including zeros)
	public String toString(){

		String result = "";
		Lab7Node currNode = head;
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

	public void addElement(int index, double value){
		Lab7Node currNode = head;
		if(index > this.length){
			System.out.println("invalid index");
		}
		if(currNode == null){
			head = new Lab7Node(index, value);
			//System.out.println("null head, " + value + " added at index " + index);
		}else{
		while(currNode.getIndex() < index){
			if(currNode.getIndex() != index){
				if (currNode.getNext() == null){
					Lab7Node newNode = new Lab7Node(index, value);
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

	public static double dot( SparseVector x, SparseVector y ){
		double total = 0.0;
		Lab7Node currNodeX = x.head;
		Lab7Node currNodeY = y.head;
		int tracker = 0;

		if(x.length != y.length || x.head == null || y.head == null){
			System.out.println("lengths not equal or list is empty");
			return 0.0;
		}else if(x.length == 1 && currNodeX.getIndex() == currNodeY.getIndex()){
			return (currNodeX.getValue() * currNodeY.getValue());
		}
		System.out.println("1st checks passed, continuing");

		while(tracker < x.length){
			while(currNodeX.getIndex() != currNodeY.getIndex()){
				if(currNodeX.getNext() != null & currNodeX.getIndex() < currNodeY.getIndex()){
					currNodeX = currNodeX.getNext();
					System.out.println("x smaller index");
					tracker++;
					break;
				}
				else if(currNodeY.getNext() != null & currNodeY.getIndex() < currNodeX.getIndex()){
					currNodeY = currNodeY.getNext();
					System.out.println("y is smaller index");
					tracker++;
					break;
				}
				else if(currNodeX.getNext() == null || currNodeY.getNext() == null){
					return total;
				}
			}

			//System.out.println("x value " + currNodeX.getValue());
			//System.out.println("y value" + currNodeY.getValue());
			double scoobert = currNodeX.getValue() * currNodeY.getValue();
			//System.out.println("zoinks " + scoobert);
			total = total + scoobert;
			//System.out.println("total" + total);
			tracker ++;


			if(currNodeX.getNext() == null){
				return total;
			}
			else if(currNodeY.getNext() == null){
				return total;
			}
			currNodeX = currNodeX.getNext();
			currNodeY = currNodeY.getNext();
		}

		return total;
		
	}

	public static void main(String[] args) {
		// SparseVector vec = new SparseVector(4);
		// SparseVector vec2 = new SparseVector(4);
		// vec.addElement(0, 2.0);
		// vec.addElement(1, 0.0);
		// vec.addElement(2, 4.0);
		// vec.addElement(3, 0.0);
		// vec2.addElement(0, 4.0);
		// vec2.addElement(1, 2.0);
		// vec2.addElement(2, 2.0);
		// vec2.addElement(3, 0.0);
		
		//System.out.println(vec);
		
		//double x = SparseVector.dot(vec, vec2);
		//System.out.println(x);

		SparseVector x = new SparseVector(100000000);
		x.addElement(0, 1.0);
		x.addElement(10000000, 3.0);
		x.addElement(10000001, -2.0);
		SparseVector y = new SparseVector(100000000);
		y.addElement(0, 2.0);
		y.addElement(10000001, -4.0);
		double result = dot(x, y);
		System.out.println(result);
	}
}
