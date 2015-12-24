/**
 * BinomialHeap
 *
 * An implementation of binomial heap over non-negative integers.
 * Based on exercise from previous semester.// test changes
 */
public class BinomialHeap {
	public class BinomialTree {
		public BinomialTree() {
			
		}
	}
	public class BinomialNode {
		// If node x is a root, then parent = NIL.
		// If node x has no children, then child = NIL.
		// if x is the rightmost child of its parent, then sibling = NIL.
		private BinomialNode parent; // Pointer to its parent.
		private BinomialNode child; // Pointer to its leftmost child.
		private BinomialNode sibling; // pointer to the sibling of x immediately to its right.
		private int degree; // The number of children of x.
		private int key;
		public BinomialNode(int key) {
			this.parent = NIL;
			this.child = NIL;
			this.sibling = NIL;
			this.degree = 0;
			this.key = key;
		}
	}
	/** Creates and returns a new heap containing no elements **/
	public BinomialHeap() {
		
	}
	/***************************************************
	 ****			Variables						****
	 ***************************************************/
	private static BinomialHeap NILsupport = new BinomialHeap();
	public static final BinomialNode NIL = NILsupport.new BinomialNode(0);
	public static final int INFINITY = 2147483647;
	private BinomialNode head;
	public int numberOfLinks;
	/***************************************************
	 ****			ADT functions					****
	 ***************************************************/
	/** Returns a pointer to the node in heap H whose key is minimum **/
	private BinomialNode minimum() {
		BinomialNode y = NIL;
		BinomialNode x = this.head;
		int min = INFINITY;
		while (x != NIL) {
			if (x.key < min) {
				min = x.key;
				y = x;
			}
			x = x.sibling;
		}
		return y;
	}
	/** The procedure makes node y the new head of the linked list of node z’s children **/
	private void link(BinomialNode y, BinomialNode z) {
		y.parent = z;
		y.sibling = z.child;
		z.child = y;
		z.degree += 1;
	}
	/** Merges the root lists of binomial heaps H1 and H2 into a single linked list H
	 * that is sorted by degree into monotonically increasing order. **/
	private BinomialNode merge(BinomialHeap H1, BinomialHeap H2) {
		return null; // TODO
	}
	/** creates and returns a new heap that contains all the nodes of heaps H1 and H2.
	 * Heaps H1 and H2 are “destroyed” by this operation. **/
	private BinomialHeap union(BinomialHeap H1, BinomialHeap H2) {
		BinomialHeap H = new BinomialHeap();
		H.head = merge(H1,H2); // Free the objects H1 and H2 but not the lists they point to
		if (H.head == NIL) {
			return H;
		}
		BinomialNode prev_x = NIL;
		BinomialNode x = H.head;
		BinomialNode next_x = x.sibling;
		while (next_x != NIL) {
			if ( (x.degree != next_x.degree)||( (next_x.sibling != NIL)&&(next_x.sibling.degree == x.degree) ) ) { // Case 1+2
				prev_x = x;
				x = next_x;
			} else { // Case 3+4
				if (x.key <= next_x.key) { // Case 3
					link(next_x,x);
				} else { // Case 4
					if (prev_x == NIL) {
						H.head = next_x;
					} else {
						prev_x.sibling = next_x;
					}
					link(x,next_x);
					x = next_x;
				}
			}
			next_x = x.sibling;
		}
		return H;
	}
	/** Inserts node x, whose key field has already been filled in, into heap H. **/
	private void insert(BinomialHeap H, BinomialNode x) {
		BinomialHeap H1 = new BinomialHeap();
		x.parent = NIL;
		x.child = NIL;
		x.sibling = NIL;
		x.degree = 0;
		H1.head = x;
		H = union(H,H1);
	}
	/** Deletes the node from heap H whose key is minimum, returning a pointer to the node. **/
	private int extractMin(BinomialHeap H) {
		// find the root x with the minimum key in the root list of H , and remove x from the root list of H
		BinomialHeap H1 = new BinomialHeap();
		// reverse the order of the linked list of x’s children, setting the p field of each child to NIL , and set head[H ′ ] to point to the head of the resulting list
		H = union(H,H1);
		return x; // TODO
	}
	/** Assigns to node x within heap H the new key value k,
	 * which is assumed to be no greater than its current key value. **/
	private void decreaseKey(BinomialNode x, int k) {
		if (k > x.key) {
			System.err.println("New key is greater than current key");
		} else {
			int tmpKey = 0;
			x.key = k;
			BinomialNode y = x;
			BinomialNode z = y.parent;
			while ( (z != NIL)&&(y.key < z.key) ) {
				tmpKey = z.key;
				z.key = y.key;
				y.key = tmpKey;
				if (If y and z have satellite fields, exchange them, too) { // If y and z have satellite fields, exchange them, too. // TODO
					y = z;
					z = y.parent;
				}
			}
		}
	}
	/** Deletes node x from heap H. **/
	private void delete(BinomialNode x) {
		decreaseKey(x, -INFINITY);
		extractMin(this);
	}
	/***************************************************
	 ****			Public functions				****
	 ***************************************************/
	/**
	* public boolean empty()
	*
	* precondition: none
	* 
	* The method returns true if and only if the heap
	* is empty.
	*	
	*/
	public boolean empty()
	{
		return false; // should be replaced by student code
	}
	/**
	* public void insert(int value)
	*
	* Insert value into the heap 
	*
	*/
	public void insert(int value) 
	{	
		return; // should be replaced by student code
	}
	/**
	* public void deleteMin()
	*
	* Delete the minimum value
	*
	*/
	public void deleteMin()
	{
		return; // should be replaced by student code
		
	}
	/**
	* public int findMin()
	*
	* Return the minimum value
	*
	*/
	public int findMin()
	{
		return 42;// should be replaced by student code
	} 
	/**
	* public void meld (BinomialHeap heap2)
	*
	* Meld the heap with heap2
	*
	*/
	public void meld (BinomialHeap heap2)
	{
		return; // should be replaced by student code			
	}
	/**
	* public int size()
	*
	* Return the number of elements in the heap
	*	
	*/
	public int size()
	{
		return 42; // should be replaced by student code
	}
	/**
	* public int minTreeRank()
	*
	* Return the minimum rank of a tree in the heap.
	* 
	*/
	public int minTreeRank()
	{
		return 0; //	to be replaced by student code
	}
	/**
	* public boolean[] binaryRep()
	*
	* Return an array containing the binary representation of the heap.
	* 
	*/
	public boolean[] binaryRep()
	{
		boolean[] arr = new boolean[42];
		return arr; //	to be replaced by student code
	}
	/**
	* public void arrayToHeap()
	*
	* Insert the array to the heap. Delete previous elemnts in the heap.
	* 
	*/
	public void arrayToHeap(int[] array)
	{
		return; //	to be replaced by student code
	}
	/**
	* public boolean isValid()
	*
	* Returns true if and only if the heap is valid.
	*	
	*/
	public boolean isValid() 
	{
		return false; // should be replaced by student code
	}
	/**
	* public class HeapNode
	* 
	* If you wish to implement classes other than BinomialHeap
	* (for example HeapNode), do it in this file, not in 
	* another file 
	*  
	*/
	public class HeapNode{
	}
}
