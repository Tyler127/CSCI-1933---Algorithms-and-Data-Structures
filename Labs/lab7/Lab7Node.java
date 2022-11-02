public class Lab7Node {
	private Lab7Node next;
	private int index;
	private double value;

	public Lab7Node(int idx, double val){
		index = idx;
		value = val;
		next = null;
	}

	public double getValue(){ return value; }
	public int getIndex(){ return index; }

	public Lab7Node getNext(){ return next; }
	public void setNext( Lab7Node other ){ next = other; }

	public String toString(){ return ""+value; }
}
