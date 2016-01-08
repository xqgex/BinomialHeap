/**
 * BinomialHeap
 */
public class BinomialHeap {
	/** 
	 * BinomialNode
	 */
	public class BinomialNode {
		// If node x is a root, then parent = NIL.
		// If node x has no children, then child = NIL.
		// if x is the rightmost child of its parent, then sibling = NIL.
		@SuppressWarnings("unused")
		private BinomialNode parent; // Pointer to its parent.
		private BinomialNode child; // Pointer to its leftmost child.
		private BinomialNode sibling; // pointer to the sibling of x immediately to its right.
		private int degree; // The number of children of x.
		private int key;
		/**
		 * public BinomialNode(int key)
		 * 
		 * Binomial Node constructor
		 * @param key
		 * 
		 */
		public BinomialNode(int key) {
			this.parent = NIL;
			this.child = NIL;
			this.sibling = NIL;
			this.degree = 1;
			this.key = key;
		}
		/**
		 * private void print(int level)
		 * 
		 * Recursive function that print the tree.
		 * @param level
		 * 
		 */
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
	/** Creates and returns a new heap containing no elements **/
	public BinomialHeap() {
	}
	/***************************************************
	 ****			Variables						****
	 ***************************************************/
	public static final int INFINITY = 2147483647; // This number represent infinity in our calculation
	private static BinomialHeap NILsupport = new BinomialHeap();
	public static BinomialNode NIL = NILsupport.new BinomialNode(INFINITY); // NIL node
	private BinomialNode head = NIL; // Point to the head of the heap
	public int numberOfLinks;
	/***************************************************
	 ****			Private functions				****
	 ***************************************************
	 * extractMin									****
	 * insert										****
	 * link											****
	 * merge										****
	 * minimum										****
	 * treeValidationTest							****
	 * union										****
	 * verifyNIL									****
	 ***************************************************/
	/** Delete the node from heap H with the minimum key,
	 * return a pointer to the node. **/
	private BinomialNode extractMin(BinomialHeap H) {
		if (head == NIL) { // The heap is empty
			return NIL;
		}
		BinomialNode min = this.head;
		BinomialNode minPrev = NIL;
		BinomialNode next = min.sibling;
		BinomialNode nextPrev = min;
		while (next != NIL) { // find the root x with the minimum key in the heap,
			if ( (min == NIL)||(next.key < min.key) ) { // We found a new minimum
				min = next;
				minPrev = nextPrev;
			}
			nextPrev = next;
			next = next.sibling;
		}
		// Remove x from the heap
		if (min == this.head) { // The minimum node is the heap head
			this.head = min.sibling;
		} else {
			minPrev.sibling = min.sibling;
		}
		// reverse the order of the linked list of x children, setting the parent field of each child to NIL,
		BinomialNode newHead = NIL;
		BinomialNode child = min.child;
		while (child != NIL) {
			next = child.sibling;
			child.sibling = newHead;
			child.parent = NIL;
			newHead = child;
			child = next;
		}
		// set the heap head to point to the head of the resulting list
		BinomialHeap H1 = new BinomialHeap();
		H1.head = newHead;
		union(H1);
		return min;
	}
	/** Inserts node x into heap H,
	 * Assume that node x key field already been filled in **/
	private void insert(BinomialNode x) {
		BinomialHeap H1 = new BinomialHeap(); // Create a new heap
		x.parent = NIL;
		x.child = NIL;
		x.sibling = NIL;
		x.degree = 1;
		H1.head = x; // Make the new heap point to node x
		union(H1); // Union the heaps
	}
	/** The procedure makes node y the new head of the linked list of node z children **/
	private void link(BinomialNode y, BinomialNode z) {
		y.parent = z;
		y.sibling = z.child;
		z.child = y;
		z.degree += y.degree;
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
				if (a.sibling == NIL) {
					a.sibling = b;
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
	}
	/** Returns a pointer to the node in heap H whose key is minimum **/
	/** Find the minimum node in the heap **/
	private BinomialNode minimum() {
		BinomialNode y = NIL;
		BinomialNode x = this.head;
		int min = INFINITY;
		while (x != NIL) { // Foreach tree in the heap
			if (x.key < min) {
				min = x.key;
				y = x;
			}
			x = x.sibling; // Move to the next tree
		}
		return y;
	}
	/** Recursive function that check that the tree hanging out from the node is valid **/
	private int[] treeValidationTest(BinomialNode node, int[] ans) {
		int min = node.key; // TODO WHY????????????????
		if (node.child == NIL) { // The tree don't have child's
			ans[0] = 1; // Tree is valid
			return ans;
		}
		if (node.degree < min) {
			ans[0] = 0; // Tree is invalid
		}
		ans[1]++; // Add one to the tree degree
		ans = treeValidationTest(node.child,ans); // Check node child
		if(node.sibling != null) { // The node have sibling
			ans = treeValidationTest(node.sibling,ans);	 // Check node sibling
		}
		return ans;
	}
	/** Add all the nodes of H2 the current heap
	 * Heaps H2 are destroyed by this operation. **/
	private void union(BinomialHeap H2) {
		merge(H2); // Merge the current heap with heap H2
		if (this.head != NIL) { // If we aren't dealing with two empty heaps - rearrange the heap
			BinomialNode prev_x = NIL;
			BinomialNode x = this.head;
			BinomialNode next_x = x.sibling;
			while (next_x != NIL) {
				if ( (x.degree != next_x.degree)||( (next_x.sibling != NIL)&&(next_x.sibling.degree == x.degree) ) ) { // Case 1+2
					prev_x = x;
					x = next_x;
				} else { // Case 3+4
					if (x.key <= next_x.key) { // Case 3
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
			}
		}
	}
	/** Make sure that the heap have been initialized correctly */
	/** Verify that the heap initialize correctly **/
	private void verifyNIL() {
		NIL.child = NIL;
		NIL.parent = NIL;
		NIL.sibling = NIL;
	}
	/***************************************************
	 ****			Public functions				****
	 ***************************************************
	 * arrayToHeap									****
	 * binaryRep									****
	 * deleteMin									****
	 * empty										****
	 * findMin										****
	 * getHead										****
	 * insert										****
	 * isValid										****
	 * meld											****
	 * minTreeRank									****
	 * print										****
	 * printList									****
	 * size											****
	 ***************************************************/
	/**
	* public void arrayToHeap(int[] array)
	*
	* precondition: 'array' is a valid int array.
	*
	* Insert the array to the heap, Delete previous elements from the heap.
	*
	*/
	public void arrayToHeap(int[] array) {
		verifyNIL();
		if (array.length > 0) { // The array isn't empty
			BinomialNode start = new BinomialNode(array[0]); // Create a new node from the first element
			this.head = start; // Make the heap point to the new node
			for (int i = 1; i < array.length; i++) {
				this.insert(array[i]); // Insert node to the heap
			}
		} else { // The array is empty
			this.head = NIL;
		}
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
		if (nod != NIL) { // If heap isn't empty
			// Find the biggest tree
			while (biggestNod.sibling != NIL) {
				biggestNod = biggestNod.sibling;
			}
			// Now, do some magic :)
			int num = (int)Math.floor(Math.log(biggestNod.degree) / Math.log(2))+1; // Calculate the array size
			boolean[] arr = new boolean[num]; // Create the array initialized to "false"
			while (nod != NIL) { // Foreach tree in the heap
				arr[(int)Math.ceil(Math.log(nod.degree) / Math.log(2))] = true; // Change the corresponded place in the array to "true"
				nod = nod.sibling;
			}
			return arr;
		} else {
			return new boolean[0]; // Return empty array
		}
	}
	/**
	* public void deleteMin()
	*
	* Delete the minimum value.
	*
	*/
	public void deleteMin() {
		verifyNIL();
		extractMin(this);
	}
	/**
	* public boolean empty()
	*
	* precondition: none
	*
	* The method returns true if and only if the heap is empty.
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
	* public int findMin()
	*
	* Return the minimum value.
	*
	*/
	public int findMin() {
		verifyNIL();
		return this.minimum().key;
	}
	/** 
	 * BinomialNode getHead()
	 *
	 * Return the tree head.
	 *
	 */
	public BinomialNode getHead() {
		return this.head;
	}
	/**
	* public void insert(int value)
	*
	* precondition: value is a valid integer
	*
	* Insert value into the heap.
	*
	*/
	public void insert(int value) {
		verifyNIL();
		BinomialNode x = new BinomialNode(value);
		insert(x);
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
		while (x != NIL) { // While there is more tree in the heap
			if (treeValidationTest(x, new int[2])[0] == 0) { // Check if the tree is valid
				return false;
			}
			if (x.degree <= lastDegree) { // Check if the heap is valid
				return false;
			}
			lastDegree = x.degree;
			x = x.sibling; // Move to the next tree
		}
		return true;
	}
	/**
	* public void meld (BinomialHeap heap2)
	*
	* precondition: the heap received is a valid heap.
	*
	* Meld the to heaps
	*
	*/
	public void meld(BinomialHeap heap2) {
		verifyNIL();
		union(heap2);
	}
	/**
	* public int minTreeRank()
	*
	* Return the minimum rank of a tree in the heap.
	*
	*/
	public int minTreeRank() {
		verifyNIL();
		return (int)Math.floor(Math.log(this.head.degree) / Math.log(2));
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
		System.out.println("My size is: " + size() + "\r\n");
	}
	/**
	 * public void printList(BinomialNode root)
	 *
	 * precondition: 'root' is a valid node.
	 *
	 * A recursive function that print all the heap nodes in a list.
	 *
	 */
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
	/**
	* public int size()
	*
	* Return the number of elements in the heap.
	*
	*/
	public int size() {
		verifyNIL();
		int size = 0;
		BinomialNode x = this.head;
		while (x != NIL) { // Foreach tree in the heap
			size += x.degree;
			x = x.sibling;
		}
		return size;
	}
}