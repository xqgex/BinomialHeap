public class homemadeTester {
	public static void main(String[] args) {
		BinomialHeap myheap = new BinomialHeap();
		for(int i=1;i<10;i++) {
			myheap.insert(i);
			myheap.print(); // TODO DEBUG
		}
		myheap.printList(myheap.getHead()); // TODO DEBUG
		for(int i=0;i<4;i++) {
			myheap.deleteMin();
			myheap.print(); // TODO DEBUG
		}
	}
}