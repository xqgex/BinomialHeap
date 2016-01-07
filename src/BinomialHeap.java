/**
 * BinomialHeap
 *
 * An implementation of binomial heap over non-negative integers.
 * Based on exercise from previous semester.// test changes
 */
public class BinomialHeap {
	public static final boolean DEBUG = false; // TODO DEBUG
	
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
			this.degree = 1;
			this.key = key;
		}
		private void print(int level) {
			BinomialNode curr = this;
			while (curr != NIL) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < level; i++) {
					sb.append(" ");
				}
				sb.append(String.valueOf(curr.key));
				System.out.println(sb.toString());
				if (curr.child != NIL) {
					curr.child.print(level + 1);
				}
				curr = curr.sibling;
			}
		}
	}
	/*public class BinomialTree { // TODO Can we delete this class?
		private BinomialNode root; // Tree root
		public BinomialTree(BinomialNode node) {
			this.root = node;
		}
	}*/
	/** Creates and returns a new heap containing no elements **/
	public BinomialHeap() {
	}
	/***************************************************
	 ****			Variables						****
	 ***************************************************/
	public static final int INFINITY = 2147483647;
	private static BinomialHeap NILsupport = new BinomialHeap();
	public static BinomialNode NIL = NILsupport.new BinomialNode(INFINITY);
	private BinomialNode head = NIL;
	public int numberOfLinks; // TODO delete me
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
	/** The procedure makes node y the new head of the linked list of node z children **/
	private void link(BinomialNode y, BinomialNode z) {
		y.parent = z;
		y.sibling = z.child;
		z.child = y;
		z.degree += y.degree;//1; //TODO is this change correct?
	}
	/** Merges the root lists of binomial heaps H1 and H2 into a single linked list H
	 * that is sorted by degree into monotonically increasing order. **/
	private void merge(BinomialHeap H2) {
		BinomialNode a = this.head;
		BinomialNode b = H2.head;
		if ((a != NIL)&&(b != NIL)) {
			BinomialNode c = NIL;
			if (a.degree < b.degree) {
				this.head = a;
			} else {
				this.head = b;
			}
			if (this.head == NIL) {
				return;
			}
			if (this.head == b) {
				b = a;
			}
			a = this.head;
			while (b != NIL) {
				debug(this); // TODO DEBUG
				if (a.sibling == NIL) {
					a.sibling = b;
					debug(this); // TODO DEBUG
					return;
				} else if (a.sibling.degree < b.degree) {
					a = a.sibling;
				} else {
					c = b.sibling;
					b.sibling = a.sibling;
					a.sibling = b;
					a = a.sibling;
					b = c;
				}
			}
		} else {
			if (a != NIL) {
				this.head = a;
			} else {
				this.head = b;
			}
		}
		debug(this); // TODO DEBUG
	}
	/** creates and returns a new heap that contains all the nodes of heaps H1 and H2.
	 * Heaps H1 and H2 are destroyed by this operation. **/
	private void union(BinomialHeap H2) {
		//System.out.println("aaaa"); // TODO DEBUG
		//this.print(); // TODO DEBUG
		merge(H2); // Free the objects H1 and H2 but not the lists they point to
		debug(this); // TODO DEBUG
		if (this.head != NIL) {
			BinomialNode prev_x = NIL;
			BinomialNode x = this.head;
			BinomialNode next_x = x.sibling;
			while (next_x != NIL) {
				debug(this); // TODO DEBUG
				if ( (x.degree != next_x.degree)||( (next_x.sibling != NIL)&&(next_x.sibling.degree == x.degree) ) ) { // Case 1+2
					prev_x = x;
					x = next_x;
				} else { // Case 3+4
					if (x.key <= next_x.key) { // Case 3
						debug(this); // TODO DEBUG
						x.sibling = next_x.sibling;
						link(next_x,x);
					} else { // Case 4
						if (prev_x == NIL) {
							this.head = next_x;
						} else {
							prev_x.sibling = next_x;
						}
						link(x,next_x);
						x = next_x;
					}
				}
				next_x = x.sibling;
				debug(this); // TODO DEBUG
			}
		}
	}
	/** Inserts node x, whose key field has already been filled in, into heap H. **/
	private void insert(BinomialNode x) {
		BinomialHeap H1 = new BinomialHeap();
		x.parent = NIL;
		x.child = NIL;
		x.sibling = NIL;
		x.degree = 1;
		H1.head = x;
		union(H1);
	}
	/** Deletes the node from heap H whose key is minimum, returning a pointer to the node. **/
	private BinomialNode extractMin(BinomialHeap H) { //TODO check this function
		//ADT//BinomialNode x = NIL;
		// TODO // find the root x with the minimum key in the root list of H,
		if (head == NIL) {
			return NIL;
		}
		debug(this); // TODO DEBUG
		BinomialNode min = this.head;
		BinomialNode minPrev = NIL;
		BinomialNode next = min.sibling;
		BinomialNode nextPrev = min;
		while (next != NIL) {
			if ( (min == NIL)||(next.key < min.key) ) {
				min = next;
				minPrev = nextPrev;
			}
			nextPrev = next;
			next = next.sibling;
		}
		debug(this); // TODO DEBUG
		// TODO //and remove x from the root list of H
		if (min == this.head) {
			this.head = min.sibling;
		} else {
			minPrev.sibling = min.sibling;
		}
		debug(this); // TODO DEBUG
		// TODO // reverse the order of the linked list of x children, setting the p field of each child to NIL,
		BinomialNode newHead = NIL;
		BinomialNode child = min.child;
		while (child != NIL) {
			next = child.sibling;
			child.sibling = newHead;
			child.parent = NIL;
			newHead = child;
			child = next;
		}
		debug(this); // TODO DEBUG
		// TODO // and set head[H] to point to the head of the resulting list
		BinomialHeap H1 = new BinomialHeap();
		H1.head = newHead;
		debug(this); // TODO DEBUG
		debug(H1); // TODO DEBUG
		union(H1);
		debug(this); // TODO DEBUG
		return min; // TODO check this line
	}
	/** Assigns to node x within heap H the new key value k,
	 * which is assumed to be no greater than its current key value. **/
	/*private void decreaseKey(BinomialNode x, int k) {
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
				// If y and z have satellite fields, exchange them, too. // TODO Can we delete this line safely?
				y = z;
				z = y.parent;
				//} // TODO Can we delete this line safely?
			}
		}
	}*/
	/** Deletes node x from heap H. **/
	/*private void delete(BinomialNode x) {
		decreaseKey(x, -INFINITY);
		extractMin(this);
	}*/
	private void verifyNIL() {
		NIL.child = NIL;
		NIL.parent = NIL;
		NIL.sibling = NIL;
		//BinomialHeap.initNIL = BinomialHeap.NIL;
	}
	private int[] recVal(BinomialNode node, int[] ans) {
		int min = node.key;
		if (node.child == NIL) {
			ans[0] = 1;
			return ans;
		}
		if (node.degree < min) {
			ans[0] = 0;
		}
		ans[1]++;
		ans = recVal(node.child,ans);
		if(node.sibling != null) {
			ans = recVal(node.sibling,ans);	
		}
		return ans;
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
	public boolean empty() {
		verifyNIL();
		if (this.head == NIL) {
			return true;
		} else {
			return false;
		}
	}
	/**
	* public void insert(int value)
	*
	* Insert value into the heap 
	*
	*/
	public void insert(int value) {
		verifyNIL();
		BinomialNode x = new BinomialNode(value);
		insert(x); // TODO check this line
	}
	/**
	* public void deleteMin()
	*
	* Delete the minimum value
	*
	*/
	public void deleteMin() {
		verifyNIL();
		extractMin(this);
	}
	/**
	* public int findMin()
	*
	* Return the minimum value
	* the min node is always the head of the tree, sos we return its value.
	*/
	public int findMin() {
		verifyNIL();
		return minimum().key;
	} 
	/**
	* public void meld (BinomialHeap heap2)
	*
	* Meld the heap with heap2
	*
	*/
	public void meld (BinomialHeap heap2) {
		verifyNIL();
		merge(heap2);
	}
	/**
	* public int size()
	*
	* Return the number of elements in the heap
	*	
	*/
	public int size() {
		verifyNIL();
		int size = 0;
		BinomialNode x = this.head;
		while (x != NIL) {
			size += x.degree;
			x = x.sibling;
		}
		return size;
	}
	/**
	* public int minTreeRank()
	*
	* Return the minimum rank of a tree in the heap.
	* 
	*/
	public int minTreeRank() {
		verifyNIL();
		int rank = 0;
		BinomialNode x = this.head;
		while (x != NIL) {
			rank = x.degree;
			x = x.sibling;
		}
		return rank;
	}
	/**
	* public boolean[] binaryRep()
	*
	* Return an array containing the binary representation of the heap.
	* 
	*/
	public boolean[] binaryRep() {
		verifyNIL();
		BinomialNode nod = this.head;
		BinomialNode biggestNod = this.head;
		if (nod != NIL) {
			// Find the biggest tree
			while (biggestNod.sibling != NIL) {
				biggestNod = biggestNod.sibling;
			}
			// Now, do some magic :)
			double t = Math.log(biggestNod.degree) / Math.log(2);
			double r = Math.floor(Math.log(biggestNod.degree) / Math.log(2));
			double j = Math.ceil(Math.log(biggestNod.degree) / Math.log(2));
			int num = (int)Math.floor(Math.log(biggestNod.degree) / Math.log(2))+1;
			boolean[] arr = new boolean[num];
			while (nod != NIL) {
				arr[(int)Math.ceil(Math.log(nod.degree) / Math.log(2))] = true;
				nod = nod.sibling;
			}
			return arr;
		} else {
			return new boolean[0];
		}
	}
	/**
	* public void arrayToHeap()
	*
	* Insert the array to the heap. Delete previous elements in the heap.
	* 
	*/
	public void arrayToHeap(int[] array) {
		verifyNIL();
		BinomialNode start = new BinomialNode(array[0]);
		this.head = start;
		for (int i = 1; i <= array.length; i++) {
			this.insert(i);
		}
	}
	/**
	* public boolean isValid()
	*
	* Returns true if and only if the heap is valid.
	*	
	*/
	public boolean isValid() {
		verifyNIL();
		int lastDegree = -INFINITY;
		BinomialNode x = this.head;
		while (x != NIL) {
			if (recVal(x, new int[2])[0] == 0) { // Tree is invalid
				return false;
			}
			if (x.degree <= lastDegree) { // Heap is invalid
				return false;
			}
			lastDegree = x.degree;
			x = x.sibling;
		}
		return true;
	}
	public BinomialNode getHead() {
		return this.head;
	}
	/**
	 * public void print()
	 * 
	 * Print the tree structure to the user.
	 * 
	 */
	public void print() {
		verifyNIL();
		System.out.println("Binomial heap:");
		if (head != NIL) {
			head.print(0);
		}
		System.out.println("My size is: " + size() + "\r\n"); // TODO DEBUG
	}
	public void printList(BinomialNode root) {
		if (root == this.head) {
			System.out.println("key=" + root.key + " ,degree=" + root.degree + " ,status=root");
		}
		if (root.child != NIL) {
			System.out.println("key=" + root.child.key + " ,degree=" + root.child.degree + " ,status=child");
			printList(root.child);
		}
		if (root.sibling != NIL) {
			System.out.println("key=" + root.sibling.key + " ,degree=" + root.sibling.degree + " ,status=sibling");
			printList(root.sibling);
		}
	}
	public void debug(BinomialHeap hip) {
		if (DEBUG) {
			hip.print(); // TODO DEBUG
			printList(hip.head); // TODO DEBUG
		}
	}
}