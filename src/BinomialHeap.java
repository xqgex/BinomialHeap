/**
 * BinomialHeap
 *
 * An implementation of binomial heap over non-negative integers.
 * Based on exercise from previous semester.
 */
public class BinomialHeap
{
	/***************************************************
	 ****			Variables						****
	 ***************************************************/
	public int numberOfLinks;
	/***************************************************
	 ****			ADT functions					****
	 ***************************************************/
	private Node minimum(BinomialHeap H) {
		y = NIL;
		x = head[H];
		min = infinity;
		while (x != NIL) {
			if (key[x] < min) {
				min = key[x];
				y = x;
			}
			x = sibling[x];
		}
		return y;
	}
	private void link(Node y, Node z) {
		p[y] = z;
		sibling[y] = child[z];
		child[z] = y;
		degree[z] += 1;
	}
	private BinomialHeap union(BinomialHeap H1, BinomialHeap H2) {
		BinomialHeap H = new BinomialHeap();
		head[H] = merge(H1,H2);
		// free the objects H1 and H2 but not the lists they point to
		if (head[H] == NIL) {
			return H;
		}
		prev_x = NIL;
		x = head[H];
		next_x = sibling[x];
		while (next_x != NIL) {
			if ( (degree[x] != degree[next_x])||( (sibling[next_x] != NIL)&&(degree[sibling[next_x]] == degree[x]) ) ) { // Case 1+2
				prev_x = x;
				x = next_x;
			} else { // Case 3+4
				if (key[x] <= key[next_x]) { // Case 3
					link(next_x,x);
				} else { // Case 4
					if (prev_x == NIL) {
						head[H] = next_x;
					} else {
						sibling[prev_x] = next_x;
					}
					link(x,next_x);
					x = next_x;
				}
			}
			next_x = sibling[x];
		}
		return H;
	}
	private void insert(BinomialHeap H, int x) {
		BinomialHeap H1 = new BinomialHeap();
		p[x] = NIL;
		child[x] = NIL;
		sibling[x] = NIL;
		degree[x] = 0;
		head[H1] = x;
		H = union(H,H1)
	}
	private int extractMin(BinomialHeap H) {
		// find the root x with the minimum key in the root list of H , and remove x from the root list of H
		BinomialHeap H1 = new BinomialHeap();
		// reverse the order of the linked list of x’s children, setting the p field of each child to NIL , and set head[H ′ ] to point to the head of the resulting list
		H = union(H,H1);
		return x;
	}
	private void decreaseKey(BinomialHeap H, int x, k) {
		if (k > key[x]) {
			System.err.println("New key is greater than current key");
		} else {
			key[x] = k;
			y = x;
			z = p[y];
			while ( (z != NIL)&&(key[y] < key[z]) ) {
				tmpKey = key[z];
				key[z] = key[y];
				key[y] = tmpKey;
				if (If y and z have satellite fields, exchange them, too.) { // If y and z have satellite fields, exchange them, too.
					y = z;
					z = p[y];
				}
			}
		}
	}
	private void delete(BinomialHeap H, int x) {
		decreaseKey(H, x, -infinity);
		extractMin( H);
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